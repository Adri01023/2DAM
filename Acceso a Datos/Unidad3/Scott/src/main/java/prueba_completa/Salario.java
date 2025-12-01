package prueba_completa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "sals")
public class Salario {
	@Id
	@Column(name = "grade")
	int clasificación;
	@Column(name = "quantity", unique = true)
	int cantidad;
	public Salario() {}
	public Salario(int clasificación, int cantidad) {
		super();
		this.clasificación = clasificación;
		this.cantidad = cantidad;
	}
	public int getClasificación() {
		return clasificación;
	}
	public void setClasificación(int clasificación) {
		this.clasificación = clasificación;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	@Override
	public String toString() {
		return String.valueOf(getCantidad());
	}
}
