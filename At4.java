public class At4 {
    public static void main(String[] args) {
        ListaEncadeada lista = new ListaEncadeada();

        lista.adiciona(new Aluno(8.5, "doug", 20));
        lista.adiciona(new Aluno(7.3, "ale", 26));
        lista.adiciona(new Aluno(0.0, "leo", 24));
        lista.adiciona(new Aluno(9.5, "maria", 22));
        lista.adiciona(new Aluno(6.7, "joao", 23));

        System.out.println("Lista de Alunos:");
        lista.imprimir();

        System.out.println("Número de elementos preenchidos: " + lista.tamanho());
        System.out.println("O vetor contém X notas? " + lista.contem(1));

        lista.remover(1);

        System.out.println("Lista de Alunos após remoção:");
        lista.imprimir();
    }
}

class Aluno {
    private double nota;
    private String nome;
    private int idade;

    public Aluno(double nota, String nome, int idade) {
        this.nota = nota;
        this.nome = nome;
        this.idade = idade;
    }

    public double getNota() { return nota; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }

    public void setNota(double nota) { this.nota = nota; }
    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", nota=" + nota +
                ", idade=" + idade +
                '}';
    }
}



class Node {
    Aluno aluno;
    Node proximo;

    public Node(Aluno aluno) {
        this.aluno = aluno;
        this.proximo = null;
    }
}


class ListaEncadeada {
    private Node inicio;

    public ListaEncadeada() {
        this.inicio = null;
    }

    public void adiciona(Aluno aluno) {
        Node novo = new Node(aluno);
        if (inicio == null) {
            inicio = novo;
        } else {
            Node atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
    }

    public void remover(int indice) {
        if (indice < 0 || inicio == null) {
            System.out.println("Índice inválido para remoção.");
            return;
        }

        if (indice == 0) {
            inicio = inicio.proximo;
            return;
        }

        Node atual = inicio;
        Node anterior = null;
        int i = 0;
        while (atual != null && i < indice) {
            anterior = atual;
            atual = atual.proximo;
            i++;
        }

        if (atual != null && anterior != null) {
            anterior.proximo = atual.proximo;
        } else {
            System.out.println("Índice inválido para remoção.");
        }
    }

    public int tamanho() {
        int count = 0;
        Node atual = inicio;
        while (atual != null) {
            count++;
            atual = atual.proximo;
        }
        return count;
    }

    public boolean contem(int quantidade) {
        return tamanho() >= quantidade;
    }

    public void imprimir() {
        Node atual = inicio;
        while (atual != null) {
            Aluno aluno = atual.aluno;
            System.out.println("Nome: " + aluno.getNome() + ", Nota: " + aluno.getNota() + ", Idade: " + aluno.getIdade());
            atual = atual.proximo;
        }
    }
}

