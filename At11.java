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
}

class FilaLista {

    private static class No {
        Aluno aluno;
        No proximo;

        public No(Aluno aluno) {
            this.aluno = aluno;
            this.proximo = null;
        }
    }

    private No inicio;
    private No fim;
    private int tamanho;

    public FilaLista() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public void enfileirar(Aluno aluno) {
        No novoNo = new No(aluno);
        if (isEmpty()) {
            this.inicio = novoNo;
            this.fim = novoNo;
        } else {
            this.fim.proximo = novoNo;
            this.fim = novoNo;
        }
        this.tamanho++;
    }

    public Aluno desenfileirar() {
        if (isEmpty()) {
            System.out.println("Fila vazia. Nao eh possivel remover.");
            return null;
        }
        Aluno alunoRemovido = this.inicio.aluno;
        this.inicio = this.inicio.proximo;
        if (this.inicio == null) {
            this.fim = null;
        }
        this.tamanho--;
        return alunoRemovido;
    }

    public Aluno getInicio() {
        if (isEmpty()) {
            System.out.println("Fila vazia.");
            return null;
        }
        return this.inicio.aluno;
    }

    public int size() {
        return this.tamanho;
    }

    public boolean isEmpty() {
        return this.tamanho == 0;
    }

    public void imprimirFila() {
        if (isEmpty()) {
            System.out.println("Fila: [ VAZIA ]");
            return;
        }
        System.out.print("Fila: [ INICIO -> ");
        No atual = this.inicio;
        while (atual != null) {
            System.out.print(atual.aluno);
            if (atual.proximo != null) {
                System.out.print(" | ");
            }
            atual = atual.proximo;
        }
        System.out.println(" <- FIM ] (Tamanho: " + this.tamanho + ")");
    }
}

public class At11{
    public static void main(String[] args) {
        FilaLista filaDeAlunos = new FilaLista();

        System.out.println("Fila esta vazia? " + filaDeAlunos.isEmpty());
        System.out.println("Tamanho da fila: " + filaDeAlunos.size());
        filaDeAlunos.imprimirFila();

        Aluno aluno1 = new Aluno("Carlos Drummond", "CDA001");
        Aluno aluno2 = new Aluno("Cecilia Meireles", "CME002");
        Aluno aluno3 = new Aluno("Machado de Assis", "MAS003");

        System.out.println("\nEnfileirando alunos...");
        filaDeAlunos.enfileirar(aluno1);
        filaDeAlunos.imprimirFila();
        filaDeAlunos.enfileirar(aluno2);
        filaDeAlunos.imprimirFila();
        filaDeAlunos.enfileirar(aluno3);
        filaDeAlunos.imprimirFila();

        System.out.println("\nFila esta vazia? " + filaDeAlunos.isEmpty());
        System.out.println("Tamanho da fila: " + filaDeAlunos.size());
        System.out.println("Primeiro aluno da fila: " + filaDeAlunos.getInicio());

        System.out.println("\nDesenfileirando alunos...");
        Aluno removido1 = filaDeAlunos.desenfileirar();
        System.out.println("Aluno removido: " + removido1);
        filaDeAlunos.imprimirFila();

        Aluno removido2 = filaDeAlunos.desenfileirar();
        System.out.println("Aluno removido: " + removido2);
        filaDeAlunos.imprimirFila();
        System.out.println("Tamanho da fila: " + filaDeAlunos.size());
        System.out.println("Primeiro aluno da fila agora: " + filaDeAlunos.getInicio());

        filaDeAlunos.enfileirar(new Aluno("Clarice Lispector", "CLI004"));
        System.out.println("\nEnfileirado novo aluno:");
        filaDeAlunos.imprimirFila();


        Aluno removido3 = filaDeAlunos.desenfileirar();
        System.out.println("\nAluno removido: " + removido3);
        filaDeAlunos.imprimirFila();

        Aluno removido4 = filaDeAlunos.desenfileirar();
        System.out.println("\nAluno removido: " + removido4);
        filaDeAlunos.imprimirFila();

        System.out.println("\nTentando desenfileirar de fila vazia:");
        filaDeAlunos.desenfileirar();
        System.out.println("Primeiro aluno da fila (vazia): " + filaDeAlunos.getInicio());
        System.out.println("Fila esta vazia? " + filaDeAlunos.isEmpty());
        System.out.println("Tamanho da fila: " + filaDeAlunos.size());
    }
}