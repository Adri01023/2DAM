package com.example.demo;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "id",
    "nombre",
    "domicilio",
    "hobbie",
    "profesion",
    "haSidoPresidente"
})

public class Personaje {
	@Id
	Long id;
	String nombre;
	String domicilio;
	String hobbie;
	String profesion;
	Boolean haSidoPresidente;
	
	public Personaje(String nombre, String domicilio, String hobbie, String profesion, Boolean haSidoPresidente) {
		super();
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.hobbie = hobbie;
		this.profesion = profesion;
		this.haSidoPresidente = haSidoPresidente;
	}
	
	public Personaje() {
		super();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getHobbie() {
		return hobbie;
	}
	public void setHobbie(String hobbie) {
		this.hobbie = hobbie;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public Boolean getHaSidoPresidente() {
		return haSidoPresidente;
	}
	public void setHaSidoPresidente(Boolean haSidoPresidente) {
		this.haSidoPresidente = haSidoPresidente;
	}
}
