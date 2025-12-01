package prueba_completa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "dept")
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "deptno")
	int deptno;
	@Column(name = "deptname")
	String nombredept;
	@Column(name = "loc")
	String localizacion;
	
	public Departamento() {}
	
	public Departamento(String nombredept, String localizacion) {
		super();
		this.nombredept = nombredept;
		this.localizacion = localizacion;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getNombredept() {
		return nombredept;
	}

	public void setNombredept(String nombredept) {
		this.nombredept = nombredept;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	@Override
	public String toString() {
		return getNombredept() + ", " + getLocalizacion();
	}
	
}
