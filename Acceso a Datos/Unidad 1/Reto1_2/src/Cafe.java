package pruebasJson;

public class Cafe {
	private String nombre;
	private double precio;
	private int ventas;
	private int total;

	public Cafe(String nombre, double precio, int ventas, int total) {
		this.nombre = nombre;
		this.precio = precio;
		this.ventas = ventas;
		this.total = total;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getVentas() {
		return ventas;
	}

	public void setVentas(int ventas) {
		this.ventas = ventas;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	 @Override
	    public String toString() {
	        return "Cafe{" + "nombre=" + nombre + "precio=" + precio+ "ventas=" + ventas + "total=" + total +'}';
	    }

}
