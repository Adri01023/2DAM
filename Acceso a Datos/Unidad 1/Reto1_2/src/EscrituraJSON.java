package pruebasJson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class EscrituraJSON {
	public static void main(String[] args) {
		Cafe colombia = new Cafe("Colombia",2.8,82,85);
		XStream jstream = new XStream( new JsonHierarchicalStreamDriver());
		try {
			jstream.toXML(colombia, new FileWriter(new File("lib/cafes.json")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
