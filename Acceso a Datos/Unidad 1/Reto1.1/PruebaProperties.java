import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

public class PruebaProperties {
    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.setProperty("1", "Juan Cuesta");
        prop.setProperty("2", "Paloma Cuesta");
        prop.setProperty("3", "Nieves Cuesta");
        prop.setProperty("4", "Andrés Guerra");
        prop.setProperty("5", "Pablo Guerra");
        prop.setProperty("6", "Emilio Delgado");
        try {
            File file = new File("lista.xml");
            FileOutputStream fos = new FileOutputStream(file);
            prop.storeToXML(fos, "Prueba");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
