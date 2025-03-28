

public class At2 {

    public static void main(String[] args) {
        

    
        Aluno[] alunos = new Aluno[3];

        
        alunos[0] = new Aluno(8.5, "doug", 20);
        alunos[1] = new Aluno(7.3, "ale", 26);
        alunos[2] = new Aluno(0.0, "leo", 24);

        
       
       adiciona(alunos, 2, 8.0);

      
       System.out.println("Lista de Alunos:");
       for (Aluno aluno : alunos) {
           System.out.println("Nome: " + aluno.getNome() + ", Nota: " + aluno.getNota() + ", Idade: " + aluno.getIdade());
       }

       System.out.println("Número de elementos preenchidos: " + tamanho(alunos));

       System.out.println("O vetor contém X notas? " + contem(alunos, 1));


    }

    public static void adiciona(Aluno[] alunos, int indice, double nota) {
        if (indice >= 0 && indice < alunos.length) {
            alunos[indice].setNota(nota);
        } else {
            System.out.println("Índice inválido.");
        }
    }

    
    public static int tamanho(Aluno[] alunos) {
        int count = 0;
        for (Aluno aluno : alunos) {
            if (aluno != null && aluno.getNota() > 0) {
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

    public Aluno(double nota, String nome, int idade){
        this.nota = nota;
        this.nome = nome;
        this.idade = idade;
    }

    public double getNota() {return nota;}
    public String getNome() {return nome;}
    public int getIdade() {return idade;}

    public void setNota( double nota ) {this.nota = nota;}
    public void setNome( String nome ) {this.nome = nome;}
    public void setIdade( int idade ) {this.idade = idade;}

}
