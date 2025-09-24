import java.io.*;

public class PruebaLibrosCSV {
    public static void main(String[] args) {
        Libro quijote = new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "Santillana", 593);
        Libro voz = new Libro("La voz a ti debida", "Pedro Salinas", "Signo",96);
        try {
            PrintWriter escribir = new PrintWriter("libros.csv");
            escribir.println(quijote);
            escribir.println(voz);
            escribir.close();
            BufferedReader br = new BufferedReader(new FileReader("libros.csv"));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
