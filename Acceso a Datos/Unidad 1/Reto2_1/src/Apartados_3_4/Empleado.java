package Apartados_3_4;

public class Empleado {
	    private int id;
	    private String nombre;
	    private String trabajo;
	    private Integer manager;
	    private String fechaContratacion;
	    private int salario;
	    private Integer comision;
	    private int ndepartamento;
	    
		public Empleado(int id, String nombre, String trabajo, Integer manager, String fechaContratacion, int salario,
				Integer comision, int ndepartamento) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.trabajo = trabajo;
			this.manager = manager;
			this.fechaContratacion = fechaContratacion;
			this.salario = salario;
			this.comision = comision;
			this.ndepartamento = ndepartamento;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getTrabajo() {
			return trabajo;
		}

		public void setTrabajo(String trabajo) {
			this.trabajo = trabajo;
		}

		public Integer getManager() {
			return manager;
		}

		public void setManager(Integer manager) {
			this.manager = manager;
		}

		public String getFechaContratacion() {
			return fechaContratacion;
		}

		public void setFechaContratacion(String fechaContratacion) {
			this.fechaContratacion = fechaContratacion;
		}

		public int getSalario() {
			return salario;
		}

		public void setSalario(int salario) {
			this.salario = salario;
		}

		public Integer getComision() {
			return comision;
		}

		public void setComision(Integer comision) {
			this.comision = comision;
		}

		public int getNDepartamento() {
			return ndepartamento;
		}

		public void setDepartamento(int departamento) {
			this.ndepartamento = departamento;
		}

		@Override
		public String toString() {
			return "Empleado [id=" + id + ", nombre=" + nombre + ", trabajo=" + trabajo + ", manager=" + manager
					+ ", fechaContratacion=" + fechaContratacion + ", salario=" + salario + ", comision=" + comision
					+ ", departamento=" + ndepartamento + "]";
		}
	    
	    
	}