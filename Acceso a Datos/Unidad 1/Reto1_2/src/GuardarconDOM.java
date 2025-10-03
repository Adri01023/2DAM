package Actividad02;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GuardarconDOM {

	public static void main(String[] args) {
		  try {
	            File xmlFile = new File("libros1.xml"); // generado en el apartado 1 con XStream
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.parse(xmlFile);

	            doc.getDocumentElement().normalize();
	            System.out.println("Elemento raíz: " + doc.getDocumentElement().getNodeName());

	            NodeList listaLibros = doc.getElementsByTagName("Actividad02.Libro");
	            List<Libro> libros = new ArrayList<>();

	            for (int i = 0; i < listaLibros.getLength(); i++) {
	                Node nodo = listaLibros.item(i);

	                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	                    Element e = (Element) nodo;

	                    int isbn = Integer.parseInt(e.getElementsByTagName("ISBN").item(0).getTextContent());
	                    String titulo = e.getElementsByTagName("libro").item(0).getTextContent();
	                    String editorial = e.getElementsByTagName("editorial").item(0).getTextContent();
	                    int paginas = Integer.parseInt(e.getElementsByTagName("paginas").item(0).getTextContent());
	                    String autor = e.getElementsByTagName("autor").item(0).getTextContent();
	                    int copias = Integer.parseInt(e.getElementsByTagName("copias").item(0).getTextContent());

	                    libros.add(new Libro(isbn, titulo, editorial, paginas, autor, copias));
	                }
	            }

	            System.out.println("Libros leídos con DOM:");
	            for (Libro l : libros) {
	                System.out.println(l);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
