package vista;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import control.AppAgenda;
import modelo.Contacto;

public class VistaAgenda extends MouseAdapter implements ActionListener {
    private final String txtBorrar = "Borrar";
    private final String txtAñadir = "Añadir";
    private final String txtEditar = "Editar";

    AppAgenda controlador;

    JFrame ventana;
    JPanel panelPrincipal, panelInferior;
    JScrollPane panelListado;
    JTable tablaListado;
    JButton botónAñadir, botónBorrar, botónEditar;
    JLabel lblNombre, lblTelefono;
    JTextField txtNombre, txtTelefono;
    JDialog diálogoAñadir;
    JPanel panelAñadir;
    JButton botónAñadirEnDiálogo;
    static Vector<String> nombreColumnas;
    Vector<Vector<String>> contenidoTabla;

    public VistaAgenda(AppAgenda controlador) {
        this.controlador = controlador;
        nombreColumnas = new Vector<>();
        nombreColumnas.add("Nombre");
        nombreColumnas.add("Teléfono");
        construyeVentanaAgenda();
    }

    protected void construyeVentanaAgenda() {
        ventana = new JFrame("Agenda");
        panelPrincipal = (JPanel) ventana.getContentPane();
        panelPrincipal.setLayout(new BorderLayout());
        panelInferior = new JPanel();
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        botónAñadir = new JButton(txtAñadir);
        botónBorrar = new JButton(txtBorrar);
        botónEditar = new JButton(txtEditar);

        panelInferior.add(botónAñadir);
        panelInferior.add(botónEditar);
        panelInferior.add(botónBorrar);

        botónAñadir.addActionListener(this);
        botónBorrar.addActionListener(this);
        botónEditar.addActionListener(this);

        lblNombre = new JLabel("Nombre:");
        lblTelefono = new JLabel("Teléfono:");
        txtNombre = new JTextField("", 20);
        txtTelefono = new JTextField("", 20);

        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Diálogo añadir
        diálogoAñadir = new JDialog(ventana, true);
        panelAñadir = new JPanel();
        panelAñadir.setLayout(new BoxLayout(panelAñadir, BoxLayout.Y_AXIS));
        JPanel panelNombre = new JPanel();
        JPanel panelTelefono = new JPanel();
        panelNombre.add(lblNombre);
        panelNombre.add(txtNombre);
        panelTelefono.add(lblTelefono);
        panelTelefono.add(txtTelefono);
        panelAñadir.add(panelNombre);
        panelAñadir.add(panelTelefono);

        botónAñadirEnDiálogo = new JButton("Añadir");
        panelAñadir.add(botónAñadirEnDiálogo);
        diálogoAñadir.setTitle("Añadir contacto");
        diálogoAñadir.getContentPane().add(panelAñadir);
        diálogoAñadir.getRootPane().setDefaultButton(botónAñadirEnDiálogo);
        diálogoAñadir.pack();

        botónAñadirEnDiálogo.addActionListener(evento -> {
            diálogoAñadir.dispose();
            if (!txtNombre.getText().isBlank()) {
                Contacto c = controlador.añadirContacto(txtNombre.getText(), txtTelefono.getText());
                añadeContactoATabla(c.getNombre(), c.getTelefono());
            }
        });

        actualizaListado(controlador.getContactos());
    }

    public Vector<Vector<String>> listaContactosAVector(List<Contacto> lista) {
        return new Vector<>(lista.stream()
                .map(c -> new Vector<>(Arrays.asList(c.getNombre(), c.getTelefono())))
                .collect(Collectors.toList()));
    }

    public void actualizaListado(List<Contacto> listaOrig) {
        Vector<Vector<String>> lista = listaContactosAVector(listaOrig);
        contenidoTabla = lista;
        tablaListado = new JTable(lista, nombreColumnas);
        tablaListado.setDefaultEditor(Object.class, null);
        tablaListado.addMouseListener(this);
        panelListado = new JScrollPane(tablaListado);
        panelPrincipal.add(panelListado, BorderLayout.CENTER);
        ventana.pack();
    }

    private void añadeContactoATabla(String nombre, String telefono) {
        Vector<String> vc = new Vector<>();
        vc.add(nombre);
        vc.add(telefono);
        contenidoTabla.add(vc);
        tablaListado.updateUI();
    }

    private void borraContactoDeTabla(String nombre) {
        contenidoTabla.removeIf(v -> v.get(0).equals(nombre));
        tablaListado.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        int pos;
        switch (arg0.getActionCommand()) {
            case txtBorrar:
                pos = tablaListado.getSelectedRow();
                if (pos >= 0) {
                    String nombre = tablaListado.getValueAt(pos, 0).toString();
                    controlador.borrarContacto(nombre);
                    borraContactoDeTabla(nombre);
                }
                break;
            case txtAñadir:
                diálogoAñadir.setVisible(true);
                break;
            case txtEditar:
                pos = tablaListado.getSelectedRow();
                if (pos >= 0) {
                    String nombre = tablaListado.getValueAt(pos, 0).toString();
                    String telefono = tablaListado.getValueAt(pos, 1).toString();
                    String nuevoTelefono = JOptionPane.showInputDialog(ventana, "Nuevo teléfono", telefono);
                    if (nuevoTelefono != null) {
                        controlador.editarContacto(nombre, nuevoTelefono);
                        contenidoTabla.get(pos).set(1, nuevoTelefono);
                        tablaListado.updateUI();
                    }
                }
                break;
        }
    }
}