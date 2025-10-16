package ud1.examen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class ControlUD1 {
	static final String FICHERO_RUTAS = "rutas.xml";
	static final String RUTA_FICHERO_HSV = "pokemon.hsv";
	static final String RUTA_FICHERO_XML = "pokemon.xml";
	static final String RUTA_FICHERO_JSON = "pokemon.json";
	static final String RUTA_FICHERO_CSV = "pokemon.csv";
	static final String RUTA_FICHERO_XML2 = "pokemon2.xml";
	static final String RUTA_FICHERO_JSON2 = "pokemon2.json";

	public static void main(String[] args) {
		List<Pokemon> lista1 = leeHsv(RUTA_FICHERO_HSV);
		guardaXml(RUTA_FICHERO_XML,lista1);
		guardaXml2(RUTA_FICHERO_XML2,lista1);
		guardaJson(RUTA_FICHERO_JSON,lista1);
		guardaJson2(RUTA_FICHERO_JSON2,lista1);
		List<Pokemon> lista2 = leeXml(RUTA_FICHERO_XML);
		List<Pokemon> lista3 = leeXml2(RUTA_FICHERO_XML2);
		List<Pokemon> lista4 = leeJson(RUTA_FICHERO_JSON);
		List<Pokemon> lista5 = leeJson2(RUTA_FICHERO_JSON2);
		
		// Comprobaciones (opcional)
		//System.exit(0);  // borrar esta línea para hacer las comprobaciones:
		if (lista1.size()==800) {
			System.out.println("Parece que el apartado 1 puede estar bien");
		}
		if ((lista1.size() == lista2.size()) && lista2.containsAll(lista1)) {
			System.out.println("Apartados 2 y 6 probablemente bien");
		}
		if ((lista1.size() == lista3.size()) && lista3.containsAll(lista1)) {
			System.out.println("Apartados 3 y 7 probablemente bien");
		}
		if ((lista1.size() == lista4.size()) && lista4.containsAll(lista1)) {
			System.out.println("Apartados 4 y 8 probablemente bien");
		}
		if ((lista1.size() == lista5.size()) && lista5.containsAll(lista1)) {
			System.out.println("Apartados 5 y 9 probablemente bien");
		}
		
		// Apartado properties:
		Map<String,String> mapaRutas = new HashMap<String,String>();
		mapaRutas.put("RUTA_FICHERO_HSV",RUTA_FICHERO_HSV);
		mapaRutas.put("RUTA_FICHERO_XML",RUTA_FICHERO_XML);
		mapaRutas.put( "RUTA_FICHERO_XML2",RUTA_FICHERO_XML2);
		mapaRutas.put("RUTA_FICHERO_JSON",RUTA_FICHERO_JSON);
		mapaRutas.put("RUTA_FICHERO_JSON2",RUTA_FICHERO_JSON2);
		
		guardaPropertiesXml(FICHERO_RUTAS,mapaRutas);
		Map<String,String> mapaRutas2 = cargaPropertiesXml(FICHERO_RUTAS);
		
	}

	static List<Pokemon> leeHsv(final String RUTA) {
		try {
			List<Pokemon> lista = new ArrayList<>();
			String[] cadenas;
			Pokemon pokemon;
			BufferedReader br = new BufferedReader(new FileReader(new File(RUTA)));
			String linea;
			while ((linea = br.readLine()) != null) {
				cadenas = linea.split("###");
				pokemon = new Pokemon(Integer.parseInt(cadenas[0]),cadenas[1],cadenas[2],cadenas[3],
						Integer.parseInt(cadenas[4]),Integer.parseInt(cadenas[5]),Integer.parseInt(cadenas[6])
						,Integer.parseInt(cadenas[7]),Integer.parseInt(cadenas[8]),Integer.parseInt(cadenas[9])
						,Integer.parseInt(cadenas[10]),Integer.parseInt(cadenas[11]),Boolean.parseBoolean(cadenas[12]));
				lista.add(pokemon);
			}
			return lista;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	static List<Pokemon> leeXml(final String RUTA) {
		List<Pokemon> lista;
		XStream xstream = new XStream(new DomDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		try {
			FileInputStream fis = new FileInputStream(new File(RUTA));
			lista = (List<Pokemon>) xstream.fromXML(fis);
			fis.close();
			return lista;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	static List<Pokemon> leeXml2(final String RUTA) {
		List<Pokemon> lista;
		XStream xstream = new XStream(new DomDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		xstream.alias("Pokedex", List.class);
		xstream.alias("Pokemon", Pokemon.class);
		xstream.aliasAttribute("Number", "numero");
		xstream.aliasField("Name", Pokemon.class, "nombre");
		xstream.aliasField("Type1", Pokemon.class, "tipo1");
		xstream.aliasField("Type2", Pokemon.class, "tipo2");
		xstream.aliasField("Total", Pokemon.class, "total");
		xstream.aliasField("HP", Pokemon.class, "hitPoints");
		xstream.aliasField("Attack", Pokemon.class, "ataque");
		xstream.aliasField("Defense", Pokemon.class, "defensa");
		xstream.aliasField("SpecialAttack", Pokemon.class, "ataqueEspecial");
		xstream.aliasField("SpecialDefense", Pokemon.class, "defensaEspecial");
		xstream.aliasField("Speed", Pokemon.class, "velocidad");
		xstream.aliasField("Generation", Pokemon.class, "generación");
		xstream.aliasField("Legendary", Pokemon.class, "legendario");
		try {
			FileInputStream fis = new FileInputStream(new File(RUTA));
			lista = (List<Pokemon>) xstream.fromXML(fis);
			fis.close();
			return lista;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	static List<Pokemon> leeJson(final String RUTA) {
		List<Pokemon> lista;
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		try {
			FileInputStream fis = new FileInputStream(new File(RUTA));
			lista = (List<Pokemon>) xstream.fromXML(fis);
			fis.close();
			return lista;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	static List<Pokemon> leeJson2(final String RUTA) {
		List<Pokemon> lista;
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		xstream.alias("Pokedex", List.class);
		xstream.alias("Pokemon", Pokemon.class);
		xstream.aliasField("Number",Pokemon.class, "número");
		xstream.aliasField("Name", Pokemon.class, "nombre");
		xstream.aliasField("Type1", Pokemon.class, "tipo1");
		xstream.aliasField("Type2", Pokemon.class, "tipo2");
		xstream.aliasField("Total", Pokemon.class, "total");
		xstream.aliasField("HP", Pokemon.class, "hitPoints");
		xstream.aliasField("Attack", Pokemon.class, "ataque");
		xstream.aliasField("Defense", Pokemon.class, "defensa");
		xstream.aliasField("SpecialAttack", Pokemon.class, "ataqueEspecial");
		xstream.aliasField("SpecialDefense", Pokemon.class, "defensaEspecial");
		xstream.aliasField("Speed", Pokemon.class, "velocidad");
		xstream.aliasField("Generation", Pokemon.class, "generación");
		xstream.aliasField("Legendary", Pokemon.class, "legendario");
		try {
			FileInputStream fis = new FileInputStream(new File(RUTA));
			lista = (List<Pokemon>) xstream.fromXML(fis);
			fis.close();
			return lista;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	static void guardaXml(final String RUTA, List<Pokemon> lista) {
		XStream xstream = new XStream(new DomDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File(RUTA));
			xstream.toXML(lista, fos);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	static void guardaXml2(final String RUTA, List<Pokemon> lista) {
		XStream xstream = new XStream(new DomDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		xstream.alias("Pokedex", List.class);
		xstream.alias("Pokemon", Pokemon.class);
		xstream.aliasAttribute(Pokemon.class,"número","Number");
		xstream.aliasField("Name", Pokemon.class, "nombre");
		xstream.aliasField("Type1", Pokemon.class, "tipo1");
		xstream.aliasField("Type2", Pokemon.class, "tipo2");
		xstream.aliasField("Total", Pokemon.class, "total");
		xstream.aliasField("HP", Pokemon.class, "hitPoints");
		xstream.aliasField("Attack", Pokemon.class, "ataque");
		xstream.aliasField("Defense", Pokemon.class, "defensa");
		xstream.aliasField("SpecialAttack", Pokemon.class, "ataqueEspecial");
		xstream.aliasField("SpecialDefense", Pokemon.class, "defensaEspecial");
		xstream.aliasField("Speed", Pokemon.class, "velocidad");
		xstream.aliasField("Generation", Pokemon.class, "generación");
		xstream.aliasField("Legendary", Pokemon.class, "legendario");
		try {
			FileOutputStream fos = new FileOutputStream(new File(RUTA));
			xstream.toXML(lista, fos);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	static void guardaJson(final String RUTA, List<Pokemon> lista) {
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		try {
			FileOutputStream fos = new FileOutputStream(new File(RUTA));
			xstream.toXML(lista, fos);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void guardaJson2(final String RUTA, List<Pokemon> lista) {
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.addPermission(AnyTypePermission.ANY);
		xstream.alias("Pokedex", List.class);
		xstream.alias("Pokemon", Pokemon.class);
		xstream.aliasField("Number",Pokemon.class, "número");
		xstream.aliasField("Name", Pokemon.class, "nombre");
		xstream.aliasField("Type1", Pokemon.class, "tipo1");
		xstream.aliasField("Type2", Pokemon.class, "tipo2");
		xstream.aliasField("Total", Pokemon.class, "total");
		xstream.aliasField("HP", Pokemon.class, "hitPoints");
		xstream.aliasField("Attack", Pokemon.class, "ataque");
		xstream.aliasField("Defense", Pokemon.class, "defensa");
		xstream.aliasField("SpecialAttack", Pokemon.class, "ataqueEspecial");
		xstream.aliasField("SpecialDefense", Pokemon.class, "defensaEspecial");
		xstream.aliasField("Speed", Pokemon.class, "velocidad");
		xstream.aliasField("Generation", Pokemon.class, "generación");
		xstream.aliasField("Legendary", Pokemon.class, "legendario");
		try {
			FileOutputStream fos = new FileOutputStream(new File(RUTA));
			xstream.toXML(lista, fos);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void guardaPropertiesXml(String RUTA,Map<String,String> rutas) 
	{
	
	}

	static Map<String,String> cargaPropertiesXml(String RUTA) {
		return null;
	}

}
