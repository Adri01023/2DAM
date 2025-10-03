package Actividad02;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class LeerConDOM {
	public static void main(String[] args) {
	 try {
         // Crear documento vacío
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document doc = builder.newDocument();

         // Nodo raíz <list>
         Element root = doc.createElement("list");
         doc.appendChild(root);

         // Añadir libros
         root.appendChild(crearLibro(doc, 1234, "El Quijote", "Alianza", 1400, "Miguel de Cervantes", 4));
         root.appendChild(crearLibro(doc, 2345, "Cien años de soledad", "Sudamericana", 496, "Gabriel García Márquez", 10));
         root.appendChild(crearLibro(doc, 3456, "Orgullo y prejuicio", "Penguin Classics", 432, "Jane Austen", 7));

         // Guardar el XML en fichero
         TransformerFactory tf = TransformerFactory.newInstance();
         Transformer transformer = tf.newTransformer();
         transformer.setOutputProperty(OutputKeys.INDENT, "yes");

         DOMSource source = new DOMSource(doc);
         StreamResult result = new StreamResult(new File("librosDOM.xml"));
         transformer.transform(source, result);

         System.out.println("Archivo librosDOM.xml generado con DOM.");

     } catch (Exception e) {
         e.printStackTrace();
     }
}

	private static Element crearLibro(Document doc, int isbn, String titulo, String editorial,
            int paginas, String autor, int copias) {
Element libro = doc.createElement("Actividad02.Libro");

Element eISBN = doc.createElement("ISBN");
eISBN.appendChild(doc.createTextNode(String.valueOf(isbn)));
libro.appendChild(eISBN);

Element eTitulo = doc.createElement("libro");
eTitulo.appendChild(doc.createTextNode(titulo));
libro.appendChild(eTitulo);

Element eEditorial = doc.createElement("editorial");
eEditorial.appendChild(doc.createTextNode(editorial));
libro.appendChild(eEditorial);

Element ePaginas = doc.createElement("paginas");
ePaginas.appendChild(doc.createTextNode(String.valueOf(paginas)));
libro.appendChild(ePaginas);

Element eAutor = doc.createElement("autor");
eAutor.appendChild(doc.createTextNode(autor));
libro.appendChild(eAutor);

Element eCopias = doc.createElement("copias");
eCopias.appendChild(doc.createTextNode(String.valueOf(copias)));
libro.appendChild(eCopias);

return libro;
}
}
