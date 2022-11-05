package co.edu.uniquindio.subasta.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
public class Anunciante extends Usuario implements Serializable {

	// Atributo
	private int cantAnuncios;
	private ArrayList<Anuncio> listaAnuncios;
	// ____________________________________________________________________

	// Metodos constructor

	// Constructor 1
	public Anunciante(String nombre, String idUsuario, int edad) {
		super(nombre, idUsuario, edad, 0);
		this.cantAnuncios = 0;
		this.listaAnuncios = new ArrayList<Anuncio>();
	}

	// Constructor 2
	public Anunciante(String nombre, String idUsuario, int edad, double dinero, int cantAnuncios,
			ArrayList<Anuncio> listaAnuncios) {
		super(nombre, idUsuario, edad, dinero);
		this.cantAnuncios = cantAnuncios;
		this.listaAnuncios = listaAnuncios;
	}

	// Constructor 3
	public Anunciante() {
		super();
	}
//____________________________________________________________________ 

	// Metodos Getters and Setters
	public int getCantAnuncios() {
		return cantAnuncios;
	}

	public void setCantAnuncios(int cantAnuncios) {
		this.cantAnuncios = cantAnuncios;
	}

	public ArrayList<Anuncio> getListaAnuncios() {
		return listaAnuncios;
	}

	public void setListaAnuncios(ArrayList<Anuncio> listaAnuncios) {
		this.listaAnuncios = listaAnuncios;
	}

//____________________________________________________________________ 

}
