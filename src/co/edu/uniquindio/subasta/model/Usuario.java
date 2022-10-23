package co.edu.uniquindio.subasta.model;

import java.util.ArrayList;

public class Usuario {

	// Atributos
	private String nombre;
	private String idUsuario;
	private int edad;
	private float dinero;
	// ------------------------------------
	
	
	// Metodo constructor
	public Usuario(String nombre, String idUsuario, int edad, float dinero) {
		super();
		this.nombre = nombre;
		this.idUsuario = idUsuario;
		this.edad = edad;
		this.dinero = dinero;
	}
	public Usuario() {
		super();
	}
	// ----------------------------------------
	
	// Metodos Get and Set
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public float getDinero() {
		return dinero;
	}
	public void setDinero(float dinero) {
		this.dinero = dinero;
	}
	// ---------------------------------------------------
	
	
	
	// ----------------------------------------
	
}
