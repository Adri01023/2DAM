package pruebasJson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class LecturaJSON {
	public static void main(String[] args) {
		/* XStream jmapped = new XStream(new JettisonMappedXmlDriver());
		jmapped.addPermission(AnyTypePermission.ANY);						Lectura con JettisonMappedXmlDriver
		Cafe cafesito = (Cafe) jmapped.fromXML(new File("lib/cafes.json"));
		System.out.println(cafesito.toString()); */
		
		Gson gson = new Gson();
		FileReader fr;
		try {
			fr = new FileReader(new File("lib/cafes.json"));
			Cafe cafesito = gson.fromJson(fr, Cafe.class);
			fr.close();
			System.out.println(cafesito);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}