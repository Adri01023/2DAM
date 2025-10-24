package gestionAlumnos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gestionAlumnos.Model.Alumno;
import gestionAlumnos.Model.IModeloAlumnos;
import gestionAlumnos.UI.VentanaAlumnos;

public class ControladorGestionAlumnos implements ActionListener, ListSelectionListener {

    private IModeloAlumnos model;
    private VentanaAlumnos view;

    public ControladorGestionAlumnos(IModeloAlumnos model, VentanaAlumnos view) {
        this.model = model;
        this.view = view;
        anadirListeners(this);

        this.view.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.view.pack();
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
    }

    private void anadirListeners(ControladorGestionAlumnos controlador) {
        view.btnCargarTodos.addActionListener(controlador);
        view.btnCrear.addActionListener(controlador);
        view.btnModificar.addActionListener(controlador);
        view.btnEliminar.addActionListener(controlador);
        view.jListaAlumnos.addListSelectionListener(controlador);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "Cargar Todos":
                cargarTodos();
                break;
            case "Crear Nuevo":
                crearAlumno();
                break;
            case "Modificar":
                modificarAlumno();
                break;
            case "Eliminar":
                eliminarAlumno();
                break;
        }
    }

    private void cargarTodos() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        List<String> listaAlumnos = model.getAll();
        for (String a : listaAlumnos) {
            modelo.addElement(a);
        }
        view.jListaAlumnos.setModel(modelo);
    }

    private void crearAlumno() {
        String dni = view.textFieldDNI.getText().trim();
        String nombre = view.textFieldNombre.getText().trim();
        String apellidos = view.textFieldApellidos.getText().trim();
        String cp = view.textFieldCP.getText().trim();

        if (dni.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(view, "DNI y Nombre son obligatorios");
            return;
        }

        Alumno a = new Alumno();
        a.setDNI(dni);
        a.setNombre(nombre);
        a.setApellidos(apellidos);
        a.setCP(cp);

        if (model.crear(a)) {
            JOptionPane.showMessageDialog(view, "Alumno creado correctamente");
            cargarTodos();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(view, "Error al crear el alumno");
        }
    }

    private void modificarAlumno() {
        String dni = view.textFieldDNI.getText().trim();
        Alumno a = model.getAlumnoPorDNI(dni);
        if (a == null) {
            JOptionPane.showMessageDialog(view, "Alumno no existe");
            return;
        }

        a.setNombre(view.textFieldNombre.getText().trim());
        a.setApellidos(view.textFieldApellidos.getText().trim());
        a.setCP(view.textFieldCP.getText().trim());

        if (model.modificarAlumno(a)) {
            JOptionPane.showMessageDialog(view, "Alumno modificado correctamente");
            cargarTodos();
        } else {
            JOptionPane.showMessageDialog(view, "Error al modificar el alumno");
        }
    }

    private void eliminarAlumno() {
        String dni = view.textFieldDNI.getText().trim();
        if (model.eliminarAlumno(dni)) {
            JOptionPane.showMessageDialog(view, "Alumno eliminado correctamente");
            cargarTodos();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(view, "Error al eliminar el alumno");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String seleccionado = view.jListaAlumnos.getSelectedValue();
            if (seleccionado != null) {
                String dni = seleccionado.split(" -- ")[0];
                Alumno a = model.getAlumnoPorDNI(dni);
                cargarAlumno(a);
            } else {
                limpiarCampos();
            }
        }
    }

    private void limpiarCampos() {
        view.textFieldDNI.setText("");
        view.textFieldNombre.setText("");
        view.textFieldApellidos.setText("");
        view.textFieldCP.setText("");
    }

    private void cargarAlumno(Alumno a) {
        if (a == null) {
            limpiarCampos();
            return;
        }
        view.textFieldDNI.setText(a.getDNI());
        view.textFieldNombre.setText(a.getNombre());
        view.textFieldApellidos.setText(a.getApellidos());
        view.textFieldCP.setText(a.getCP());
    }
}