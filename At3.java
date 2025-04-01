import java.util.Arrays;

public class At3 {

    public static void main(String[] args) {
        Aluno[] alunos = new Aluno[3];
        
        alunos[0] = new Aluno(8.5, "doug", 20);
        alunos[1] = new Aluno(7.3, "ale", 26);
        alunos[2] = new Aluno(0.0, "leo", 24);

        alunos = adiciona(alunos, new Aluno(9.5, "maria", 22));
        alunos = adiciona(alunos, new Aluno(6.7, "joao", 23));
        
        System.out.println("Lista de Alunos:");
        for (Aluno aluno : alunos) {
            if (aluno != null) {
                System.out.println("Nome: " + aluno.getNome() + ", Nota: " + aluno.getNota() + ", Idade: " + aluno.getIdade());
            }
        }
        
        System.out.println("Número de elementos preenchidos: " + tamanho(alunos));
        System.out.println("O vetor contém X notas? " + contem(alunos, 1));
        
        alunos = remover(alunos, 1);
        
        System.out.println("Lista de Alunos após remoção:");
        for (Aluno aluno : alunos) {
            if (aluno != null) {
                System.out.println("Nome: " + aluno.getNome() + ", Nota: " + aluno.getNota() + ", Idade: " + aluno.getIdade());
            }
        }
    }

    public static Aluno[] adiciona(Aluno[] alunos, Aluno novoAluno) {
        int tamanhoAtual = tamanho(alunos);
        
        if (tamanhoAtual == alunos.length) {
            alunos = Arrays.copyOf(alunos, alunos.length * 2); 
        }
        
        alunos[tamanhoAtual] = novoAluno;
        return alunos;
    }

    public static Aluno[] remover(Aluno[] alunos, int indice) {
        if (indice < 0 || indice >= tamanho(alunos)) {
            System.out.println("Índice inválido para remoção.");
            return alunos;
        }
        
        for (int i = indice; i < alunos.length - 1; i++) {
            alunos[i] = alunos[i + 1];
        }
        
        alunos[alunos.length - 1] = null;
        return alunos;
    }

    public static int tamanho(Aluno[] alunos) {
        int count = 0;
        for (Aluno aluno : alunos) {
            if (aluno != null) {
                count++;
            }
        }
        return count;
    }

    public static boolean contem(Aluno[] alunos, int quantidade) {
        return tamanho(alunos) >= quantidade;
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
}
