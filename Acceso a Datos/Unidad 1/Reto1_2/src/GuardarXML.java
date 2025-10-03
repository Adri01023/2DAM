package Actividad02;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GuardarXML {

	public static void main(String[] args) {
		Libro l = new Libro(1234,"El Quijote","Alianza", 1400, "Miguel de Cervantes", 4);
		Libro l2 = new Libro(2345, "Cien años de soledad","Sudamericana", 496, "Gabriel García Márquez", 10);
		Libro l3 = new Libro(3456,"Orgullo y prejuicio", "Penguin Classics", 432, "Jane Austen", 7);
		
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
	             "<Biblioteca>\n" +
	             "   <Libro>\n" +
	             "       <ISBN>" + l.getISBN() + "</ISBN>\n" +
	             "       <Titulo>" + l.getLibro() + "</Titulo>\n" +
	             "       <Editorial>" + l.getEditorial() + "</Editorial>\n" +
	             "       <Paginas>" + l.getPaginas() + "</Paginas>\n" +
	             "       <Autor>" + l.getAutor() + "</Autor>\n" +
	             "       <Copias>" + l.getCopias() + "</Copias>\n" +
	             "   </Libro>\n" +
	             "   <Libro>\n" +
	             "       <ISBN>" + l2.getISBN() + "</ISBN>\n" +
	             "       <Titulo>" + l2.getLibro() + "</Titulo>\n" +
	             "       <Editorial>" + l2.getEditorial() + "</Editorial>\n" +
	             "       <Paginas>" + l2.getPaginas() + "</Paginas>\n" +
	             "       <Autor>" + l2.getAutor() + "</Autor>\n" +
	             "       <Copias>" + l2.getCopias() + "</Copias>\n" +
	             "   </Libro>\n" +
	             "   <Libro>\n" +
	             "       <ISBN>" + l3.getISBN() + "</ISBN>\n" +
	             "       <Titulo>" + l3.getLibro() + "</Titulo>\n" +
	             "       <Editorial>" + l3.getEditorial() + "</Editorial>\n" +
	             "       <Paginas>" + l3.getPaginas() + "</Paginas>\n" +
	             "       <Autor>" + l3.getAutor() + "</Autor>\n" +
	             "       <Copias>" + l3.getCopias() + "</Copias>\n" +
	             "   </Libro>\n" +
	             "</Biblioteca>";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("libro.xml"));
			bw.write(xml);
			System.out.println("XML generado y guardado");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
