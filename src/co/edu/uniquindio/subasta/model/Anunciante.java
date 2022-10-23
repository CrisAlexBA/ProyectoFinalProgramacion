package co.edu.uniquindio.subasta.model;

import java.util.ArrayList;

public class Anunciante extends Usuario{

	// Atributo
	private int cantAnuncios;
	// ------------------------

	// Metodos constructor
	public Anunciante(int cantAnuncios) {
		super();
		this.cantAnuncios = cantAnuncios;
	}

	public Anunciante(String nombre, String idUsuario, int edad) {
		super(nombre, idUsuario, edad, 0);
		this.cantAnuncios = 0;
	}
	
	

	public Anunciante() {
		super();
	}
	// ------------------------
	
	
	// Metodos Get and Set
	public int getCantAnuncios() {
		return cantAnuncios;
	}

	public void setCantAnuncios(int cantAnuncios) {
		this.cantAnuncios = cantAnuncios;
	}
	// ------------------------
	
}
