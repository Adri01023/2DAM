package pruebasXStream;

import java.io.Serializable;

public class Libro implements Serializable {
    private String titulo, autor, editorial;
    private int paginas;
    public Libro(String titulo, String autor, String editorial, int paginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.paginas = paginas;
    }
    
    @Override
    public String toString() {
        return titulo + ";" + autor + ";" + editorial + ";" + paginas;
    }
}
