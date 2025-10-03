package Actividad02;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class GuardarLibro {

	public static void main(String[] args) {
		Libro l = new Libro(1234,"El Quijote","Alianza", 1400, "Miguel de Cervantes", 4);
		Libro l2 = new Libro(2345, "Cien años de soledad","Sudamericana", 496, "Gabriel García Márquez", 10);
		Libro l3 = new Libro(3456,"Orgullo y prejuicio", "Penguin Classics", 432, "Jane Austen", 7);
		
		List<Libro> listaLibro = new ArrayList<>();
		listaLibro.add(l);
		listaLibro.add(l2);
		listaLibro.add(l3);
		
		XStream xstream = new XStream();
		String xml = xstream.toXML(listaLibro);
		System.out.println("XML generado con los libros");
		System.out.println(xml);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("libros1.xml"));
			bw.write(xml);
			System.out.println("Guardados los libros");
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
