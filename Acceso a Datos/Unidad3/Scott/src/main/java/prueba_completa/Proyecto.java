package prueba_completa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "proyectos")
public class Proyecto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_proyecto;
	
    private String nombre_proyecto;

    @ManyToMany(mappedBy = "proyectos")
    private List<Empleado> empleados = new ArrayList<>();

    public Proyecto() {}
    public Proyecto(String nombre) {
        nombre_proyecto = nombre;
    }
    
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	@Override
	public String toString() {
		return nombre_proyecto;
	}
}
