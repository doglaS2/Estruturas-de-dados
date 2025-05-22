
interface IFila {
    public boolean add(Object info);
    public boolean remove();       
    public boolean isEmpty();      
    public int size();            

}


class FilaVetor implements IFila {
    private int nElemFila; 
    private int inicio;   
    private Object[] vetFila; 


    public FilaVetor(int tamFila) {
        this.nElemFila = 0;
        this.inicio = 0;
        this.vetFila = new Object[tamFila];
    }

   
    @Override
    public boolean add(Object info) {
        if (this.nElemFila == vetFila.length) {
            System.out.println("Capacidade da fila esgotou. Não foi possível adicionar: " + info);
            return false; 
        }
      
        int fim = (this.inicio + this.nElemFila) % this.vetFila.length;
        this.vetFila[fim] = info;
        this.nElemFila++;
        return true;
    }


    @Override
    public boolean remove() {
        if (this.isEmpty()) {
            System.out.println("Fila está vazia. Não é possível remover.");
            return false;
        }
      
        this.inicio = (this.inicio + 1) % this.vetFila.length; 
        this.nElemFila--;
        return true;
    }

 
    @Override
    public boolean isEmpty() {
        return this.nElemFila == 0;
    }

   
    @Override
    public int size() {
        return this.nElemFila;
    }

 
    public void imprimirFila() {
        if (this.isEmpty()) {
            System.out.println("Fila: [ VAZIA ]");
            return;
        }
        System.out.print("Fila: [ ");
        for (int i = 0; i < this.nElemFila; i++) {
            int indiceAtual = (this.inicio + i) % this.vetFila.length;
            System.out.print(this.vetFila[indiceAtual]);
            if (i < this.nElemFila - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ] (Início na pos. " + this.inicio + ", " + this.nElemFila + " elementos)");
    }
}

public class At10 {
    public static void main(String[] args) {
        System.out.println("Criando uma fila com capacidade para 5 elementos.");
        FilaVetor minhaFila = new FilaVetor(5);

        minhaFila.imprimirFila();
        System.out.println("Está vazia? " + minhaFila.isEmpty()); 
        System.out.println("Tamanho: " + minhaFila.size());     
        System.out.println("---");

        System.out.println("Adicionando elementos:");
        minhaFila.add("A");
        minhaFila.imprimirFila();
        minhaFila.add("B");
        minhaFila.imprimirFila();
        minhaFila.add(30);
        minhaFila.imprimirFila();
        System.out.println("Está vazia? " + minhaFila.isEmpty());
        System.out.println("Tamanho: " + minhaFila.size());     
        System.out.println("---");

        System.out.println("Adicionando mais elementos até encher:");
        minhaFila.add("D");
        minhaFila.imprimirFila();
        minhaFila.add("E");
        minhaFila.imprimirFila();
        System.out.println("Tamanho: " + minhaFila.size());   
        System.out.println("---");

        System.out.println("Tentando adicionar em fila cheia:");
        minhaFila.add("F"); 
        minhaFila.imprimirFila();
        System.out.println("---");

        System.out.println("Removendo elementos:");
        minhaFila.remove();
        minhaFila.imprimirFila();
        minhaFila.remove();
        minhaFila.imprimirFila();
        System.out.println("Tamanho: " + minhaFila.size());     
        System.out.println("---");

        System.out.println("Adicionando mais elementos para testar circularidade:");
        minhaFila.add("G");
        minhaFila.imprimirFila();
        minhaFila.add("H");
        minhaFila.imprimirFila();
        System.out.println("Tamanho: " + minhaFila.size());     
        System.out.println("---");
        
        System.out.println("Tentando adicionar 'I' em fila cheia novamente:");
        minhaFila.add("I");
        minhaFila.imprimirFila();
        System.out.println("---");

        System.out.println("Removendo todos os elementos:");
        while (!minhaFila.isEmpty()) {
            minhaFila.remove();
            minhaFila.imprimirFila();
        }
        System.out.println("Está vazia? " + minhaFila.isEmpty()); 
        System.out.println("Tamanho: " + minhaFila.size());     
        System.out.println("---");

        System.out.println("Tentando remover de fila vazia:");
        minhaFila.remove(); 
        minhaFila.imprimirFila();
    }
}