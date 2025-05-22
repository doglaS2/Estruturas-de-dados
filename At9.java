interface IPilha {
    public boolean push(Object info);
    public Object pop();
    public Object top();
    public boolean isEmpty();
    public int size();
}

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
        return "Aluno[Nome: " + nome + ", Matrícula: " + matricula + "]";
    }
}

class PilhaLista implements IPilha {

    private static class No {
        private Object dado;
        private No proximo;

        public No(Object dado) {
            this.dado = dado;
            this.proximo = null;
        }

        public Object getDado() {
            return dado;
        }

        public No getProximo() {
            return proximo;
        }

        public void setProximo(No proximo) {
            this.proximo = proximo;
        }
    }

    private No topo;
    private int tamanho;

    public PilhaLista() {
        this.topo = null;
        this.tamanho = 0;
    }

    @Override
    public boolean push(Object info) {
        No novoNo = new No(info);
        novoNo.setProximo(topo);
        topo = novoNo;
        tamanho++;
        return true;
    }

    @Override
    public Object pop() {
        if (isEmpty()) {
            System.out.println("Pilha vazia");
            return null;
        }
        Object dadoRemovido = topo.getDado();
        topo = topo.getProximo();
        tamanho--;
        return dadoRemovido;
    }

    @Override
    public Object top() {
        if (isEmpty()) {
            System.out.println("Pilha vazia");
            return null;
        }
        return topo.getDado();
    }

    @Override
    public boolean isEmpty() {
        return tamanho == 0;
    }

    @Override
    public int size() {
        return tamanho;
    }

    public void imprimirPilha() {
        if (isEmpty()) {
            System.out.println("Pilha Vazia.");
            return;
        }
        System.out.println("Estado da Pilha (Topo para Base):");
        No atual = topo;
        int posicao = 1;
        while (atual != null) {
            System.out.println(posicao + ". " + atual.getDado());
            atual = atual.getProximo();
            posicao++;
        }
        System.out.println("--- Fim da Pilha ---");
    }
}

public class At9 {
    public static void main(String[] args) {
        PilhaLista pilhaDeAlunos = new PilhaLista();

        System.out.println("Pilha inicialmente vazia? " + pilhaDeAlunos.isEmpty());
        pilhaDeAlunos.imprimirPilha();
        System.out.println("Tamanho inicial: " + pilhaDeAlunos.size());

        Aluno aluno1 = new Aluno("Zeca Silva", "SI001");
        Aluno aluno2 = new Aluno("Bia Alves", "CC002");
        Aluno aluno3 = new Aluno("Téo Lima", "ES003");

        pilhaDeAlunos.push(aluno1);
        pilhaDeAlunos.push(aluno2);
        pilhaDeAlunos.push(aluno3);

        System.out.println("\nApos empilhar 3 alunos:");
        pilhaDeAlunos.imprimirPilha();
        System.out.println("Tamanho atual: " + pilhaDeAlunos.size());
        System.out.println("Elemento no topo: " + pilhaDeAlunos.top());

        Aluno alunoRemovido = (Aluno) pilhaDeAlunos.pop();
        System.out.println("\nAluno removido: " + alunoRemovido);
        System.out.println("Pilha apos remover um aluno:");
        pilhaDeAlunos.imprimirPilha();
        System.out.println("Novo topo: " + pilhaDeAlunos.top());
        System.out.println("Tamanho final: " + pilhaDeAlunos.size());

        pilhaDeAlunos.pop();
        pilhaDeAlunos.pop();
        System.out.println("\nApos remover todos:");
        pilhaDeAlunos.imprimirPilha();
        System.out.println("Pilha vazia? " + pilhaDeAlunos.isEmpty());
        pilhaDeAlunos.pop();
    }
}