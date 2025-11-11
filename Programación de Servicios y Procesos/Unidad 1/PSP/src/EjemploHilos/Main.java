package EjemploHilos;

public class Main {
    public static void main(String[] args) {
        HiloHereda hereda1 = new HiloHereda();
        HiloHereda hereda2 = new HiloHereda();
        HiloHereda hereda3 = new HiloHereda();
        hereda1.setName("Proceso 1");
        hereda2.setName("Proceso 2");
        hereda3.setName("Proceso 3");
        hereda1.start();
        hereda2.start();
        hereda3.start();
    }
}
