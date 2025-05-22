import java.util.Arrays;
import java.util.Random;

public class At8 {

    public static void bubbleSort(int[] vetor) {
        int n = vetor.length;
        boolean trocou;
        for (int i = 0; i < n - 1; i++) {
            trocou = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                    trocou = true;
                }
            }
            if (!trocou) {
                break;
            }
        }
    }

    public static int[] gerarVetorAleatorio(int tamanho) {
        int[] vetor = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(tamanho * 10);
        }
        return vetor;
    }

    public static void testarOrdenacao(int tamanho) {
        System.out.println("Testando com " + tamanho + " elementos...");
        int[] vetor = gerarVetorAleatorio(tamanho);
        System.out.println("Vetor original (primeiros 20): " + Arrays.toString(Arrays.copyOf(vetor, Math.min(tamanho, 20))));

        long startTime = System.nanoTime();
        bubbleSort(vetor);
        long endTime = System.nanoTime();

        long durationMillis = (endTime - startTime) / 1000000;
        double durationSeconds = (double)durationMillis / 1000.0;

        System.out.println("Vetor ordenado (primeiros 20): " + Arrays.toString(Arrays.copyOf(vetor, Math.min(tamanho, 20))));
        System.out.println("Tempo de execução para " + tamanho + " elementos: " + durationMillis + " ms (" + String.format("%.3f", durationSeconds) + " s)");
        System.out.println("---");
    }

    public static void main(String[] args) {
        int[] tamanhos = {1000, 10000, 100000};

        for (int tamanho : tamanhos) {
            testarOrdenacao(tamanho);
        }
    }
}