package psp.procesos.a13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorBusqueda extends JFrame implements ActionListener {

    JComboBox<String> campoOpcion;
    JComboBox<String> campoFichero;

    public GestorBusqueda() {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configurarUI();
    }

    public void configurarUI() {
        this.setTitle("Gestor de alumnos");
        this.setSize(600,500);
        this.setLayout(new GridBagLayout());
        JTextField buscartexto = new JTextField ("BUSCAR TEXTO EN FICHERO");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel titulo = new JLabel("<html><b><i>BUSCAR TEXTO EN FICHERO</i></b></html>");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // ocupara 2 columnas
        gbc.anchor = GridBagConstraints.CENTER;
        add(titulo, gbc);

        // Reset para los siguientes componentes
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // 2. Texto con opciones y fichero de búsqueda
        JLabel opciones = new JLabel("<html><b>Opciones búsqueda:</b> CASA PERRO COCHE<br><b>Ficheros búsqueda:</b> 1 a 5</html>");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(opciones, gbc);

        gbc.gridwidth = 1;

        // 3. Etiqueta y campo para "Introduzca opción"
        JLabel etiquetaOpcion = new JLabel("Introduzca opción:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(etiquetaOpcion, gbc);

        campoOpcion = new JComboBox<>();
        campoOpcion.addItem("CASA");
        campoOpcion.addItem("PERRO");
        campoOpcion.addItem("COCHE");
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(campoOpcion, gbc);

        // 4. Etiqueta y campo para "Introduzca fichero"
        JLabel etiquetaFichero = new JLabel("Introduzca fichero:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(etiquetaFichero, gbc);

        campoFichero = new JComboBox<>();
        campoFichero.addItem("1");
        campoFichero.addItem("2");
        campoFichero.addItem("3");
        campoFichero.addItem("4");
        campoFichero.addItem("5");
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(campoFichero, gbc);

        // 5. Botones Buscar y Cancelar
        JButton botonBuscar = new JButton("Buscar");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        botonBuscar.setActionCommand("buscar");
        botonBuscar.addActionListener(this);
        add(botonBuscar, gbc);

        JButton botonCancelar = new JButton("Cancelar");
        gbc.gridx = 1;
        botonCancelar.setActionCommand("cancelar");
        botonCancelar.addActionListener(this);
        add(botonCancelar, gbc);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        GestorBusqueda gb = new GestorBusqueda();
    }
    public void lanzaBusqueda() {
        ProcessBuilder pb;
        Process process;
        String classname = "psp.procesos.a13.BuscadorTexto";
        String currentpath = System.getProperty("user.dir");
        String classpath = currentpath + "/out/production/A1.3_Adrian_Martin/";
        try {
            pb = new ProcessBuilder("java", "-cp", classpath, classname, campoOpcion.getSelectedItem().toString(), campoFichero.getSelectedItem().toString());
            pb.inheritIO();
            process = pb.start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @Override
        public void actionPerformed(ActionEvent ae) {
            String comando = ae.getActionCommand();
            if (comando.equals("buscar")) {
                lanzaBusqueda();
            }
        }
    }
