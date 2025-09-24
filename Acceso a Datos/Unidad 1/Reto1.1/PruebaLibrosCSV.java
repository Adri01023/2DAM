import java.io.*;

public class PruebaLibrosCSV {
    public static void main(String[] args) {
        Libro quijote = new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "Santillana", 593);
        try {
            PrintWriter escribir = new PrintWriter("libros.csv");
            escribir.print(quijote);
            escribir.close();
            BufferedReader br = new BufferedReader(new Reader(new File("libros.csv")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
