import java.util.Arrays;

// Classe Aluno (fornecida originalmente)
class Aluno {
    private String nome;
    private String matricula;

    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {
        return "Aluno{nome='" + nome + "', matricula='" + matricula + "'}";
    }

    // Seria bom adicionar equals() e hashCode() se Aluno fosse usado como chave diretamente
    // ou para comparações mais complexas, mas para este exemplo (chave é a matrícula String),
    // não é absurdamente necessário para a lógica do mapa.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return matricula.equals(aluno.matricula) && nome.equals(aluno.nome);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(nome, matricula);
    }
}

class MapaAlunosComEnderecamentoAberto {
    private Aluno[] tabela;
    private int tamanho; // Número de elementos realmente na tabela
    private static final double FATOR_DE_CARGA_MAXIMO = 0.7;
    // Objeto sentinela para marcar slots de onde elementos foram removidos
    private static final Aluno REMOVIDO = new Aluno("---REMOVIDO---", "---SLOT REMOVIDO---");

    public MapaAlunosComEnderecamentoAberto(int capacidadeInicial) {
        if (capacidadeInicial <= 0) {
            throw new IllegalArgumentException("Capacidade inicial deve ser positiva.");
        }
        this.tabela = new Aluno[capacidadeInicial];
        this.tamanho = 0;
    }

    private int funcaoHash(String matricula) {
        if (matricula == null) {
            throw new IllegalArgumentException("Matrícula não pode ser nula para hashing.");
        }
        // Usa o tamanho atual da tabela para o cálculo do módulo
        return Math.abs(matricula.hashCode()) % tabela.length;
    }

    /**
     * Insere um aluno no mapa. Se já existir um aluno com a mesma matrícula,
     * o aluno existente é atualizado.
     * @param aluno O aluno a ser inserido.
     */
    public void put(Aluno aluno) {
        if (aluno == null || aluno.getMatricula() == null) {
            System.out.println("Não é possível inserir aluno nulo ou com matrícula nula.");
            return;
        }
        if (aluno == REMOVIDO) { // Não permitir inserir o marcador especial
            System.out.println("Não é possível inserir o marcador de remoção.");
            return;
        }

        if ((double) (tamanho + 1) / tabela.length > FATOR_DE_CARGA_MAXIMO) {
            rehash();
        }

        String matricula = aluno.getMatricula();
        int indiceHash = funcaoHash(matricula);
        int indiceAtual = indiceHash;
        int tentativas = 0; // Para evitar loop infinito em cenários inesperados

        do {
            // Se o slot está vazio ou marcado como removido, podemos inserir
            if (tabela[indiceAtual] == null || tabela[indiceAtual] == REMOVIDO) {
                tabela[indiceAtual] = aluno;
                tamanho++;
                return;
            }
            // Se a matrícula já existe, atualiza o aluno
            else if (tabela[indiceAtual].getMatricula().equals(matricula)) {
                tabela[indiceAtual] = aluno; // Atualiza
                return; // Tamanho não muda, pois é uma atualização
            }

            // Sondagem linear: próximo slot
            indiceAtual = (indiceAtual + 1) % tabela.length;
            tentativas++;
            // Se voltamos ao início e a tabela está cheia (ou cheia de colisões sem espaço)
            // Isso teoricamente não deve acontecer se o rehash funcionar e o fator de carga for respeitado.
            if (tentativas >= tabela.length) {
                 //  Isso pode acontecer se a tabela estiver completamente cheia (incluindo marcadores REMOVIDO)
                 //  e o rehash não foi chamado (por exemplo, se 'tamanho' é baixo mas não há slots null).
                 //  Uma estratégia de rehash mais agressiva ou limpar os REMOVIDO durante o rehash poderia ajudar.
                 System.err.println("Erro: Tabela cheia ou loop infinito na inserção para matrícula: " + matricula);
                 rehash(); // Forçar rehash se chegou aqui
                 put(aluno); // Tentar inserir novamente após rehash
                 return;
            }
        } while (indiceAtual != indiceHash || tentativas < tabela.length) ; // Loop continua enquanto não voltar ao início E houver tentativas
                                                                           // ou melhor, loop enquanto o slot não for encontrado e não tiver percorrido tudo
    }

    /**
     * Busca um aluno pela matrícula.
     * @param matricula A matrícula do aluno a ser buscado.
     * @return O objeto Aluno se encontrado, caso contrário null.
     */
    public Aluno get(String matricula) {
        if (matricula == null) {
            return null;
        }
        int indiceHash = funcaoHash(matricula);
        int indiceAtual = indiceHash;
        int tentativas = 0;

        do {
            // Se o slot está vazio (nunca usado), o aluno não está na tabela
            if (tabela[indiceAtual] == null) {
                return null;
            }
            // Se o slot contém o aluno desejado (e não é um marcador de removido)
            if (tabela[indiceAtual] != REMOVIDO && tabela[indiceAtual].getMatricula().equals(matricula)) {
                return tabela[indiceAtual];
            }

            // Sondagem linear: próximo slot
            indiceAtual = (indiceAtual + 1) % tabela.length;
            tentativas++;
            if (tentativas >= tabela.length) { // Percorreu toda a tabela
                return null;
            }
        } while (indiceAtual != indiceHash || tentativas < tabela.length); // Continua se não voltou ao início ou ainda há slots para checar
                                                                           // Se tabela[indiceAtual] == null, já teria retornado
        return null; // Não encontrado após percorrer os possíveis slots
    }

    /**
     * Remove um aluno do mapa pela matrícula.
     * @param matricula A matrícula do aluno a ser removido.
     * @return O objeto Aluno removido, ou null se não encontrado.
     */
    public Aluno remove(String matricula) {
        if (matricula == null) {
            return null;
        }
        int indiceHash = funcaoHash(matricula);
        int indiceAtual = indiceHash;
        int tentativas = 0;

        do {
            // Se o slot está vazio, o aluno não está na tabela
            if (tabela[indiceAtual] == null) {
                return null;
            }
            // Se encontramos o aluno
            if (tabela[indiceAtual] != REMOVIDO && tabela[indiceAtual].getMatricula().equals(matricula)) {
                Aluno alunoRemovido = tabela[indiceAtual];
                tabela[indiceAtual] = REMOVIDO; // Marca como removido
                tamanho--;
                return alunoRemovido;
            }

            // Sondagem linear: próximo slot
            indiceAtual = (indiceAtual + 1) % tabela.length;
            tentativas++;
            if (tentativas >= tabela.length) { // Percorreu toda a tabela
                return null;
            }
        } while (indiceAtual != indiceHash || tentativas < tabela.length);
        return null; // Não encontrado
    }

    private void rehash() {
        Aluno[] tabelaAntiga = this.tabela;
        int capacidadeAntiga = tabelaAntiga.length;
        int novaCapacidade = capacidadeAntiga * 2; // Dobra a capacidade
        if (novaCapacidade == 0) novaCapacidade = 1; // Caso a capacidade antiga fosse 0 por algum motivo

        System.out.println("Rehashing: Capacidade antiga = " + capacidadeAntiga + ", Nova capacidade = " + novaCapacidade);

        this.tabela = new Aluno[novaCapacidade];
        this.tamanho = 0; // Resetado pois `put` irá incrementá-lo

        for (Aluno alunoExistente : tabelaAntiga) {
            if (alunoExistente != null && alunoExistente != REMOVIDO) {
                put(alunoExistente); // Reinsere na nova tabela (usará a nova tabela.length para hash)
            }
        }
    }

    public int size() {
        return this.tamanho;
    }

    public boolean isEmpty() {
        return this.tamanho == 0;
    }

    public void imprimirMapa() {
        System.out.println("--- Mapa de Alunos (Capacidade: " + tabela.length + ", Tamanho: " + tamanho + ") ---");
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] == null) {
                System.out.println("Índice " + i + ": [ VAZIO ]");
            } else if (tabela[i] == REMOVIDO) {
                System.out.println("Índice " + i + ": [ REMOVIDO ]");
            } else {
                System.out.println("Índice " + i + ": " + tabela[i].toString());
            }
        }
        System.out.println("------------------------------------------");
    }

    public static void main(String[] args) {
        MapaAlunosComEnderecamentoAberto mapa = new MapaAlunosComEnderecamentoAberto(5); // Capacidade inicial pequena para forçar rehash

        System.out.println("Mapa está vazio? " + mapa.isEmpty());
        mapa.imprimirMapa();

        Aluno aluno1 = new Aluno("Carlos Drummond", "CDA001");
        Aluno aluno2 = new Aluno("Cecilia Meireles", "CME002");
        Aluno aluno3 = new Aluno("Machado de Assis", "MAS003");
        Aluno aluno4 = new Aluno("Clarice Lispector", "CLI004"); // Para forçar rehash
        Aluno aluno5 = new Aluno("Graciliano Ramos", "GRA005"); // Para forçar rehash

        System.out.println("\nInserindo alunos...");
        mapa.put(aluno1);
        mapa.imprimirMapa();
        mapa.put(aluno2);
        mapa.imprimirMapa();
        mapa.put(aluno3); // tamanho = 3, capacidade = 5. (3/5 = 0.6) < 0.7
        mapa.imprimirMapa();
        
        System.out.println("Tamanho do mapa: " + mapa.size());
        System.out.println("Mapa está vazio? " + mapa.isEmpty());

        System.out.println("\nInserindo mais alunos para testar rehash...");
        // Próxima inserção (aluno4) fará (3+1)/5 = 0.8 > 0.7, deve acionar rehash
        mapa.put(aluno4);
        mapa.imprimirMapa(); // Deve mostrar capacidade 10 agora
        System.out.println("Tamanho do mapa: " + mapa.size());
        
        mapa.put(aluno5);
        mapa.imprimirMapa();
        System.out.println("Tamanho do mapa: " + mapa.size());

        System.out.println("\nBuscando alunos...");
        System.out.println("Buscando CDA001: " + mapa.get("CDA001"));
        System.out.println("Buscando MAS003: " + mapa.get("MAS003"));
        System.out.println("Buscando XXX999 (não existe): " + mapa.get("XXX999"));

        System.out.println("\nRemovendo aluno MAS003...");
        Aluno removido = mapa.remove("MAS003");
        System.out.println("Aluno removido: " + removido);
        mapa.imprimirMapa();
        System.out.println("Tamanho do mapa: " + mapa.size());
        System.out.println("Buscando MAS003 novamente: " + mapa.get("MAS003")); // Deve ser null

        System.out.println("\nTentando remover aluno inexistente YYY000...");
        System.out.println("Aluno removido: " + mapa.remove("YYY000"));
        mapa.imprimirMapa();

        System.out.println("\nInserindo aluno com matrícula já existente (atualização)...");
        Aluno aluno1Atualizado = new Aluno("Carlos D. Andrade", "CDA001");
        mapa.put(aluno1Atualizado);
        mapa.imprimirMapa(); // Deve mostrar Carlos D. Andrade para CDA001
        System.out.println("Tamanho do mapa (não deve mudar): " + mapa.size());

        System.out.println("\nInserindo um novo aluno após remoção (para testar slot REMOVIDO)...");
        Aluno aluno6 = new Aluno("Vinicius de Moraes", "VMO006");
        mapa.put(aluno6); // Deve tentar usar o slot de MAS003 se a lógica de hash permitir
        mapa.imprimirMapa();
        System.out.println("Tamanho do mapa: " + mapa.size());

    }
}