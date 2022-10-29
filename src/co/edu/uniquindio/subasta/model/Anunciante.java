package co.edu.uniquindio.subasta.model;

import java.util.ArrayList;

public class Anunciante extends Usuario{

	// Atributo
	private int cantAnuncios;
	private ArrayList<Anuncio> listaAnuncios;
	private String listaAnunciosString;
	// ------------------------

	// Metodos constructor

	public Anunciante(String nombre, String idUsuario, int edad) {
		super(nombre, idUsuario, edad, 0);
		this.cantAnuncios = 0;
		this.listaAnuncios = new ArrayList<Anuncio>();
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
	
	public ArrayList<Anuncio> getListaAnuncios() {
		return listaAnuncios;
	}



	public void setListaAnuncios(String listaAnunciosString) {
		this.listaAnuncios = listaAnuncios;
	}
	public void setListaAnunciosString(String cadena) {
		this.listaAnunciosString = cadena;
	}
	
	public String getListaAnunciosString() {
		return listaAnunciosString;
	}
	
	// ------------------------




	
}
