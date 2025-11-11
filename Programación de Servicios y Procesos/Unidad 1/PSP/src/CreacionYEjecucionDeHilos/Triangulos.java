package CreacionYEjecucionDeHilos;

public class Triangulos extends Thread {
    float altura, base;
    public Triangulos(float a, float b) {
        altura = a;
        base = b;
    }
    @Override
    public void run() {
        float resultado = 0;
        int i;
        for (i = 1; i <= base; i++) {
            resultado += altura;
        }
        resultado += ((base-(i-1)) * altura);
        resultado /= 2;
        System.out.println("El área del " + getName() + " es: " + resultado);
    }
}