import java.util.Scanner;

public class At1 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        double x, y;

        System.out.println("Digite as coordenadas do ponto 1: ");
        x = input.nextDouble();
        y = input.nextDouble();
        PontoCartesiano ponto1 = new PontoCartesiano(x, y);   
        
        System.out.println("Digite as coordenadas do ponto 2: ");
        x = input.nextDouble();
        y = input.nextDouble();
        PontoCartesiano ponto2 = new PontoCartesiano(x, y);

        System.out.println("Ponto 1: {" + ponto1.getX() + ", " + ponto1.getY() + "} " );
        System.out.println("Ponto 2: {" + ponto2.getX() + ", " + ponto2.getY() + "} " );

        System.out.println("Distância entre os pontos: " + ponto1.calcularDistanciaEuclidiana(ponto2));

        input.close();   
    }
}

class PontoCartesiano {
    private double x;
    private double y;

    public PontoCartesiano(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public double calcularDistanciaEuclidiana(PontoCartesiano ponto) {
        double diferencaX = ponto.x - this.x;
        double diferencaY = ponto.y - this.y;
        return Math.sqrt(Math.pow(diferencaX, 2) + Math.pow(diferencaY, 2));
    }
}
