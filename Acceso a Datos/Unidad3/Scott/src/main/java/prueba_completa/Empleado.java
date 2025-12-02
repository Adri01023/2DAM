package prueba_completa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "emp")
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empno")
	int id_emp;
	@Column(name = "ename")
	String ename;
	@Column(name = "job")
	String job;
	@JoinColumn(name = "empno_manager")
	Empleado manager;
	@Column(name = "hiredate")
	LocalDate fecha_contratacion;
	@ManyToOne
	@JoinColumn(name = "grade")
	Salario salario;
	@ManyToOne
	@JoinColumn(name = "deptno")
	Departamento numdept;
	@ManyToMany
	@JoinTable(
	    name = "empleado_proyecto",
	    joinColumns = @JoinColumn(name = "empleado_id"), // joinColumns para parte poseedora
	    inverseJoinColumns = @JoinColumn(name = "proyecto_id") // inverseJoinColumns para parte poseída
	)
	private List<Proyecto> proyectos = new ArrayList<>();

	
	public Empleado() {}
	public Empleado(String ename, String job, Salario salario, Departamento numdept) {
		super();
		this.ename = ename;
		this.job = job;
		this.fecha_contratacion = LocalDate.now();
		this.salario = salario;
		this.numdept = numdept;
	}

	public Empleado(String ename, String job, Empleado manager,
			Salario salario, Departamento numdept) {
		super();
		this.ename = ename;
		this.job = job;
		this.manager = manager;
		this.fecha_contratacion = LocalDate.now();
		this.salario = salario;
		this.numdept = numdept;
	}

	public int getId_emp() {
		return id_emp;
	}

	public void setId_emp(int id_emp) {
		this.id_emp = id_emp;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Empleado getManager() {
		return manager;
	}

	public void setManager(Empleado manager) {
		this.manager = manager;
	}

	public LocalDate getFecha_contratacion() {
		return fecha_contratacion;
	}

	public void setFecha_contratacion(LocalDate fecha_contratacion) {
		this.fecha_contratacion = fecha_contratacion;
	}

	public Salario getSalario() {
		return salario;
	}

	public void setSalario(Salario salario) {
		this.salario = salario;
	}

	public Departamento getNumdept() {
		return numdept;
	}

	public void setNumdept(Departamento numdept) {
		this.numdept = numdept;
	}
	
	public List<Proyecto> getProyectos() {
		return proyectos;
	}
	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
	public void addProyecto(Proyecto proyecto) {
	    if (!proyectos.contains(proyecto)) {
	        proyectos.add(proyecto);
	        proyecto.getEmpleados().add(this); // mantener sincronía bidireccional
	    }
	}
	
	public void removeProyecto(Proyecto proyecto) {
	    if (proyectos.contains(proyecto)) {
	        proyectos.remove(proyecto);
	        proyecto.getEmpleados().remove(this); // mantener sincronía bidireccional
	    }
	}

	
	@Override
	public String toString() {
		if (this.getManager() != null) {
			return "Empleado: " + getId_emp() + " Nombre: " + getEname() + " Puesto: " + getJob() + " Manager: " + getManager().getEname() + 
					" Salario: " +  getSalario() + " Departamento: " + getNumdept() + " Proyectos: " + getProyectos();
		} else {
			return "Empleado: " + getId_emp() + " Nombre: " + getEname() + " Puesto: " + getJob() + " Manager: No tiene" + 
					" Salario: " +  getSalario() + " Departamento: " + getNumdept() + " Proyectos: " + getProyectos();
		}
		
	}
}
