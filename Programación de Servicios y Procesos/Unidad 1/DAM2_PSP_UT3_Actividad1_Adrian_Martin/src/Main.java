import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static final int NUMPUERTOS = 1000;

    public static void main(String[] args) {
        descargaWeb();
        System.out.println(detectarServicio());

    }
    private static void escanearSubred() throws InterruptedException {
        String[] ips = new String[255]; // Array donde se almacenarán las ips
        Process[] procesos = new Process[255]; // Array donde se almacenarán dichos procesos de las ips
        System.out.println("Introduce la subred /24 a analizar: ");
        String ip = scanner.nextLine(); // Recogemos la subred
        if (validarSubred(ip)) { // Validamos que la subred es valida
            if (System.getProperty("os.name").equals("Linux")) { // Discernimos entre sistemas operativos
                System.out.println("Sistema Linux detectado.");
                for (int i = 1; i < 255; i++) {
                    String ipaux = ip + "." + i; // Crea una String de cada ip de la subred
                    try {
                        ProcessBuilder pb = new ProcessBuilder("bash","-c","ping -c 5 " + ipaux); // Crea la sintaxis del proceso con un límite de 5 repeticiones
                        procesos[i] = pb.start(); // Lanza el proceso y lo alamcena en un array
                        ips[i] = ipaux; // Almacenamos el nombre de la ip asignada al proceso
                        System.out.println("Ping lanzado a la ip: " + ipaux);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 1; i < 255; i++) {
                    if (procesos[i].waitFor() == 0) { // Recogemos el retorno del proceso y si es 0 estará arriba y de lo contrario abajo
                        System.out.println(ips[i] + " ARRIBA");
                    } else {
                        System.out.println(ips[i] + " ABAJO");
                    }
                }
            } else {
                System.out.println("Sistema Windows detectado."); // Hace lo mismo que en Linux
                for (int i = 1; i < 255; i++) {
                    String ipaux = ip + "." + i;
                    try {
                        ProcessBuilder pb = new ProcessBuilder("cmd","/c","ping -n 5 " + ipaux); // Salvo aquí que cambia la sintaxis del comando
                        procesos[i] = pb.start();
                        ips[i] = ipaux;
                        System.out.println("Ping lanzado a la ip: " + ipaux);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 1; i < 255; i++) {
                    if (procesos[i].waitFor() == 0) {
                        System.out.println(ips[i] + " ARRIBA");
                    } else {
                        System.out.println(ips[i] + " ABAJO");
                    }
                }
            }

        } else {
            System.out.println("No se ha introducido una subred /24 válida"); // Printeamos mensaje de error en caso de subred incorrecta
        }

    }

    public static boolean validarSubred(String ip) {
        // Step 1: Separate the given string into an array of strings using the dot as delimiter
        String[] parts = ip.split("\\.");

        // Step 2: Check if there are exactly 3 parts
        if (parts.length != 3) {
            return false;
        }

        // Step 3: Check each part for valid number
        for (String part : parts) {
            try {
                // Step 4: Convert each part into a number
                int num = Integer.parseInt(part);

                // Step 5: Check whether the number lies in between 0 to 255
                if (num < 0 || num > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                // If parsing fails, it's not a valid number
                return false;
            }
        }

        // If all checks passed, return true
        return true;
    }

    public static void descargaWeb() {
        System.out.println("Descargando: http://ftp.sun.ac.za/ftp/pub/documentation/security/port-numbers.txt");

        InputStream is = null;
        BufferedReader bReader = null;
        FileWriter escritorFichero = null;

        try {
            URL url = new URL("http://ftp.sun.ac.za/ftp/pub/documentation/security/port-numbers.txt");

            is = url.openStream();
            bReader = new BufferedReader(new InputStreamReader(is));
            File fichero = new File("ficheroWKP.txt");
            if (!fichero.exists()) {
                escritorFichero = new FileWriter("ficheroWKP.txt");
                String linea;
                while ((linea = bReader.readLine()) != null) {
                    escritorFichero.write(linea+"\n");
                }
                escritorFichero.close();
                bReader.close();
                is.close();
                System.out.println("Descarga realizada con éxito");
            } else {
                System.out.println("Archivo ya instalado, cancelando la descarga...");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<Integer,String> detectarServicio() {
        Integer i = 1;
        Map<Integer,String> mapa = new LinkedHashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("ficheroWKP.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String cadenapuerto = i + "/tcp";
                if (linea.contains(cadenapuerto)) {
                    String[] separadolinea = linea.split(" ");
                    mapa.put(i, separadolinea[0]);
                    i++;
                } else if (linea.contains((i + 1) + "/tcp")) {
                    String[] separadolinea = linea.split(" ");
                    mapa.put(i, separadolinea[0]);
                    i++;
                } else if (i == 225) {
                    i = 242;
                } else if (i == 249) {
                    i = 256;
                } else if (i == 269) {
                    i = 280;
                } else if (i == 287) {
                    i = 308;
                } else if (i == 323) {
                    i = 333;
                } else if (i == 334) {
                    i = 344;
                } else if (i == 702) {
                    i = 704;  // MUCHOS SALTOS ENTRE PUERTOS
                } else if (i == 712) {
                    i = 729;
                } else if (i == 732) {
                    i = 741;
                } else if (i == 744) {
                    i = 747;
                } else if (i == 755) {
                    i = 758;
                } else if (i == 767) {
                    i = 769;
                } else if (i == 778) {
                    i = 800;
                }
            }
            return mapa;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}