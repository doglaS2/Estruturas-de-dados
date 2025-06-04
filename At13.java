
class No {
    int valor;
    No esquerda;
    No direita;

    public No(int item) {
        valor = item;
        esquerda = direita = null;
    }
}


class ABB {
    No raiz;


    ABB() {
        raiz = null;
    }

   
    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

   
    private No inserirRecursivo(No noAtual, int valor) {
        
        if (noAtual == null) {
            noAtual = new No(valor);
            return noAtual;
        }

       
        if (valor < noAtual.valor) {
            noAtual.esquerda = inserirRecursivo(noAtual.esquerda, valor);
        } else if (valor > noAtual.valor) { 
            noAtual.direita = inserirRecursivo(noAtual.direita, valor);
        }
   

        return noAtual;
    }

   
    public No buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    
    private No buscarRecursivo(No noAtual, int valor) {
      
        if (noAtual == null || noAtual.valor == valor) {
            return noAtual;
        }

        // Valor é maior que o valor da raiz
        if (valor < noAtual.valor) { 
            return buscarRecursivo(noAtual.esquerda, valor);
        } else { 
            return buscarRecursivo(noAtual.direita, valor);
        }
    }

    
    public void imprimirEmOrdem() {
        System.out.print("Valores da árvore (em ordem): ");
        imprimirEmOrdemRecursivo(raiz);
        System.out.println(); 
    }

    private void imprimirEmOrdemRecursivo(No noAtual) {
        if (noAtual != null) {
            imprimirEmOrdemRecursivo(noAtual.esquerda);
            System.out.print(noAtual.valor + " ");
            imprimirEmOrdemRecursivo(noAtual.direita);
        }
    }

   

    public void imprimirPreOrdem() {
        System.out.print("Valores da árvore (pré-ordem): ");
        imprimirPreOrdemRecursivo(raiz);
        System.out.println();
    }

    
    private void imprimirPreOrdemRecursivo(No noAtual) {
        if (noAtual != null) {
            System.out.print(noAtual.valor + " ");
            imprimirPreOrdemRecursivo(noAtual.esquerda);
            imprimirPreOrdemRecursivo(noAtual.direita);
        }
    }

    public void imprimirPosOrdem() {
        System.out.print("Valores da árvore (pós-ordem): ");
        imprimirPosOrdemRecursivo(raiz);
        System.out.println();
    }

    
    private void imprimirPosOrdemRecursivo(No noAtual) {
        if (noAtual != null) {
            imprimirPosOrdemRecursivo(noAtual.esquerda);
            imprimirPosOrdemRecursivo(noAtual.direita);
            System.out.print(noAtual.valor + " ");
        }
    }
}


public class At13 {
    public static void main(String[] args) {
        ABB minhaArvore = new ABB();


        int[] elementos = {50, 30, 70, 20, 40, 60, 80};
        for (int elem : elementos) {
            minhaArvore.inserir(elem);
        }


        minhaArvore.imprimirEmOrdem();


        minhaArvore.imprimirPreOrdem();
      
        minhaArvore.imprimirPosOrdem();

      
        int valorABuscar = 40;
        No noEncontrado = minhaArvore.buscar(valorABuscar);
        if (noEncontrado != null) {
            System.out.println("Valor " + valorABuscar + " encontrado na árvore! (Nó: " + noEncontrado.valor + ")");
        } else {
            System.out.println("Valor " + valorABuscar + " NÃO encontrado na árvore.");
        }

        valorABuscar = 45; 
        noEncontrado = minhaArvore.buscar(valorABuscar);
        if (noEncontrado != null) {
            System.out.println("Valor " + valorABuscar + " encontrado na árvore! (Nó: " + noEncontrado.valor + ")");
        } else {
            System.out.println("Valor " + valorABuscar + " NÃO encontrado na árvore.");
        }

        valorABuscar = 80; 
        noEncontrado = minhaArvore.buscar(valorABuscar);
        if (noEncontrado != null) {
            System.out.println("Valor " + valorABuscar + " encontrado na árvore! (Nó: " + noEncontrado.valor + ")");
        } else {
            System.out.println("Valor " + valorABuscar + " NÃO encontrado na árvore.");
        }
    }
}