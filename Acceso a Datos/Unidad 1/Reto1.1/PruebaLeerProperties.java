import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PruebaLeerProperties {
    public static void main(String[] args) {
        Properties prop = new Properties();
        try {
            File file = new File("lista.xml");
            FileInputStream fis = new FileInputStream(file);
            prop.loadFromXML(fis);
            prop.list(System.out);
        } catch (Exception x) {
            x.printStackTrace();
        }

    }
}
