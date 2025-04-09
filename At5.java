public class At5 {
    public static void main(String[] args) {
        LDE<Aluno> lista = new LDE<>();

        lista.adiciona(new Aluno(8.5, "doug", 20));
        lista.adiciona(new Aluno(7.3, "ale", 26));
        lista.adiciona(new Aluno(0.0, "leo", 24));
        lista.adiciona(new Aluno(9.5, "maria", 22));
        lista.adiciona(new Aluno(6.7, "joao", 23));

        System.out.println("Lista em ordem direta:");
        lista.imprimirDireto();

        System.out.println("\nLista em ordem reversa:");
        lista.imprimirReverso();

        System.out.println("\nRemovendo 'leo' da lista...");
        No<Aluno> no = lista.buscar("leo");
        lista.remover(no);

        System.out.println("\nLista após remoção:");
        lista.imprimirDireto();
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



class No<T> {
    private T info;
    private No<T> prox;
    private No<T> ant;

    public No(T info) {
        this.info = info;
    }

    public T getInfo() { return info; }
    public void setInfo(T info) { this.info = info; }

    public No<T> getProx() { return prox; }
    public void setProx(No<T> prox) { this.prox = prox; }

    public No<T> getAnt() { return ant; }
    public void setAnt(No<T> ant) { this.ant = ant; }
}





class LDE<T> {
    private No<T> inicio;
    private No<T> fim;

    public void adiciona(T valor) {
        No<T> novo = new No<>(valor);
        if (inicio == null) {
            inicio = fim = novo;
        } else {
            fim.setProx(novo);
            novo.setAnt(fim);
            fim = novo;
        }
    }

    public No<T> buscar(String nome) {
        No<T> atual = inicio;
        while (atual != null) {
            if (atual.getInfo() instanceof Aluno) {
                Aluno aluno = (Aluno) atual.getInfo();
                if (aluno.getNome().equalsIgnoreCase(nome)) {
                    return atual;
                }
            }
            atual = atual.getProx();
        }
        return null;
    }

    public void remover(No<T> no) {
        if (no == null) return;

        if (no == inicio && no == fim) {
            inicio = fim = null;
        } else if (no == inicio) {
            inicio = no.getProx();
            if (inicio != null) inicio.setAnt(null);
        } else if (no == fim) {
            fim = no.getAnt();
            if (fim != null) fim.setProx(null);
        } else {
            no.getAnt().setProx(no.getProx());
            no.getProx().setAnt(no.getAnt());
        }
    }

    public void imprimirDireto() {
        No<T> atual = inicio;
        while (atual != null) {
            System.out.println(atual.getInfo());
            atual = atual.getProx();
        }
    }

    public void imprimirReverso() {
        No<T> atual = fim;
        while (atual != null) {
            System.out.println(atual.getInfo());
            atual = atual.getAnt();
        }
    }
}

