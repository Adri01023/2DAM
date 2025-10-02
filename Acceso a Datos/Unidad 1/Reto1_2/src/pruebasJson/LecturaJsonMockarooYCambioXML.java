package pruebasJson;

import com.thoughtworks.xstream.XStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class LecturaJsonMockarooYCambioXML {
	public static void main(String[] args) {
		XStream xstream = new XStream();
		Type tipolista = new TypeToken <List<Usuario>>(){}.getType();
		Gson gson = new Gson();
		FileReader fr;
		try {
			fr = new FileReader("lib/datos_mockaroo.json");
		List<Usuario> lista_usuarios = gson.fromJson(fr, tipolista);
		fr.close();
		System.out.println(lista_usuarios);
		
		FileWriter fw = new FileWriter("lib/datos_mockaroo.xml");
		xstream.toXML(lista_usuarios, fw);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException io) {
		io.printStackTrace();
	}
		
	}
}
