package Actividad02;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class LeerXML {

	public static void main(String[] args) {
		File archivo = new File("libro.xml");
		
		try {
			
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//Convierte el XML en un objeto para que se pueda leer
         DocumentBuilder builder = factory.newDocumentBuilder();//Lee el XML
         
         Document doc;
		 doc = builder.parse(archivo);
		 doc.getDocumentElement().normalize();//Quita los espacios del XML
		 
		 
		 Element biblioteca = doc.getDocumentElement();
		 int totalLibros = biblioteca.getElementsByTagName("Libro").getLength();

		 for (int i = 0; i < totalLibros; i++) {
		     Element libroElem = (Element) biblioteca.getElementsByTagName("Libro").item(i);

		     int ISBN = Integer.parseInt(libroElem.getElementsByTagName("ISBN").item(0).getTextContent());//Lee el documento y escoge donde pone ISBN y empieza desde la posicion 0
		     String titulo = libroElem.getElementsByTagName("Titulo").item(0).getTextContent();
		     String editorial = libroElem.getElementsByTagName("Editorial").item(0).getTextContent();
		     int paginas = Integer.parseInt(libroElem.getElementsByTagName("Paginas").item(0).getTextContent());
		     String autor = libroElem.getElementsByTagName("Autor").item(0).getTextContent();
		     int copias = Integer.parseInt(libroElem.getElementsByTagName("Copias").item(0).getTextContent());

		     Libro libroRestaurado = new Libro(ISBN, titulo, editorial, paginas, autor, copias);
		     System.out.println(libroRestaurado);
		 }
         
		} catch (SAXException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		}     
	}

}
