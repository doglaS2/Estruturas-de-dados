import java.util.Random;

class Vetor {
    private int[] elementos;
    private int tamanho;

    public Vetor(int capacidade) {
        this.elementos = new int[capacidade];
        this.tamanho = 0;
    }

    public void adiciona(int elemento) {
        if (tamanho < elementos.length) {
            elementos[tamanho++] = elemento;
        }
    }

    public int[] getArray() {
        return elementos;
    }

    public void preencherComNumerosAleatorios() {
        Random rand = new Random();
        for (int i = 0; i < elementos.length; i++) {
            elementos[i] = rand.nextInt(100000); // uns numero aleatorio entre 0 e 99999
        }
    }
}

public class At6 {
    public static void main(String[] args) {
        int[] tamanhos = {1000, 10000, 100000};

        for (int tamanho : tamanhos) {
            Vetor vetor = new Vetor(tamanho);
            vetor.preencherComNumerosAleatorios();

            int[] array = vetor.getArray().clone(); // clono pra nao modif o original

            long inicio = System.nanoTime();
            quickSort(array, 0, array.length - 1);
            long fim = System.nanoTime();

            double tempoMs = (fim - inicio) / 1_000_000.0;
            System.out.println("Tempo para ordenar vetor com " + tamanho + " elementos: " + tempoMs + " ms");
        }
    }

    public static void quickSort(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int p = particiona(array, inicio, fim);
            quickSort(array, inicio, p - 1);
            quickSort(array, p + 1, fim);
        }
    }

    private static int particiona(int[] array, int inicio, int fim) {
        int pivo = array[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (array[j] <= pivo) {
                i++;
                troca(array, i, j);
            }
        }
        troca(array, i + 1, fim);
        return i + 1;
    }

    private static void troca(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
