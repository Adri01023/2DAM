package CreacionYEjecucionProcesos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int opc = 0;
        System.out.println("\nComandos adaptados para su sistema " + System.getProperty("os.name") + "\n");
        Comandos comando;
        Scanner sc = new Scanner(System.in);
        if (System.getProperty("os.name").equals("Linux")) {
            comando = new ComandosLinux();
        } else {
            comando = new ComandosWindows();
        }
        while (opc != 5) {
            System.out.println("\nMenú Procesos: \n");
            System.out.println("1. Ejecutar ping a destino solicitado: ");
            System.out.println("2. Listar archivos en el directorio actual y volcar a fichero a elección: ");
            System.out.println("3. Listar procesos y matar uno a elección: ");
            System.out.println("4. Ejecutar navegador con URL deseada: ");
            System.out.println("5. Salir\n");
            System.out.println("Elija una opción: ");
            opc = sc.nextInt();
            switch (opc) {
                case 1:
                    System.out.println("Introduce una dirección IP: ");
                    String ip = sc.next();
                    comando.lanzarping(ip);
                    break;
                case 2:
                    System.out.println("Introduce el fichero a volcar: ");
                    String fichero = sc.next();
                    comando.listar(fichero);
                    break;
                case 3:
                    comando.listarprocesos();
                    break;
                case 4:
                    System.out.println("Introduce la URL que quieres visitar: ");
                    String web = sc.next();
                    comando.visitarweb(web);
                    break;
                case 5:
                    System.out.println("Adios!");
                    break;
                default:
                    System.out.println("Elige una opción válida");
                    break;
            }
        }
    }
}
