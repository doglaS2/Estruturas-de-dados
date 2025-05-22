
public class At7 {

   
    public static int pesquisaBinariaIterativa(int[] vetor, int elementoAlvo) {
        int inicio = 0;
        int fim = vetor.length - 1;

        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2; 

            if (vetor[meio] == elementoAlvo) {
                return meio; 
            } else if (vetor[meio] < elementoAlvo) {
                inicio = meio + 1; 
            } else {
                fim = meio - 1;
            }
        }
        return -1; 
    }

    
    public static int pesquisaBinariaRecursiva(int[] vetor, int elementoAlvo, int inicio, int fim) {
        if (fim >= inicio) {
            int meio = inicio + (fim - inicio) / 2; 

            if (vetor[meio] == elementoAlvo) {
                return meio; 
            } else if (vetor[meio] < elementoAlvo) {
               
                return pesquisaBinariaRecursiva(vetor, elementoAlvo, meio + 1, fim);
            } else {
                
                return pesquisaBinariaRecursiva(vetor, elementoAlvo, inicio, meio - 1);
            }
        }
        return -1; 
    }

    public static void main(String[] args) {
     
        int tamanhoVetor = 1000;
        int[] vetorDeTeste = new int[tamanhoVetor];
        for (int i = 0; i < tamanhoVetor; i++) {
            vetorDeTeste[i] = i + 1; 
        }

        int elementoProcurado = 1000; 

        System.out.println("Vetor: [1, 2, ..., " + tamanhoVetor + "]");

        System.out.println("Elemento a ser pesquisado: " + elementoProcurado + "\n");


        long inicioTempoIterativo = System.nanoTime();
        int indiceIterativo = pesquisaBinariaIterativa(vetorDeTeste, elementoProcurado);
        long fimTempoIterativo = System.nanoTime();
        long tempoGastoIterativoNano = fimTempoIterativo - inicioTempoIterativo;



        System.out.println("--- Pesquisa Binária Iterativa ---");
        if (indiceIterativo != -1) {
            System.out.println("Elemento " + elementoProcurado + " encontrado no índice " + indiceIterativo + ".");
        } else {
            System.out.println("Elemento " + elementoProcurado + " não encontrado.");
        }
        System.out.println("Tempo de execução: " + tempoGastoIterativoNano + " nanossegundos.");
 



        long inicioTempoRecursivo = System.nanoTime();

        int indiceRecursivo = pesquisaBinariaRecursiva(vetorDeTeste, elementoProcurado, 0, vetorDeTeste.length - 1);
        long fimTempoRecursivo = System.nanoTime();
        long tempoGastoRecursivoNano = fimTempoRecursivo - inicioTempoRecursivo;

        System.out.println("\n--- Pesquisa Binária Recursiva ---");
        if (indiceRecursivo != -1) {
            System.out.println("Elemento " + elementoProcurado + " encontrado no índice " + indiceRecursivo + ".");
        } else {
            System.out.println("Elemento " + elementoProcurado + " não encontrado.");
        }
        System.out.println("Tempo de execução: " + tempoGastoRecursivoNano + " nanossegundos.");
       
    }
}