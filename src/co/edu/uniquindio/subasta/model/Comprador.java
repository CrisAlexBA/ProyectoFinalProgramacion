package co.edu.uniquindio.subasta.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
public class Comprador extends Usuario implements Serializable {

	// Atributos
	private int cantPujas;
	private ArrayList<Anuncio> listaCompras;

//____________________________________________________________________ 

	// Metodos Constructor

	// Constructor 1
	public Comprador(String nombre, String idUsuario, int edad, double dinero, int canPujas,
			ArrayList<Anuncio> listaCompras) {
		super(nombre, idUsuario, edad, dinero);
		this.cantPujas = canPujas;
		this.listaCompras = listaCompras;
	}

	// Constructor 2
	public Comprador(String nombre, String idUsuario, int edad) {
		super(nombre, idUsuario, edad, 0);
		this.cantPujas = 0;
		this.listaCompras = new ArrayList<Anuncio>();
	}

	// Constructor 3
	public Comprador() {
	}

//____________________________________________________________________ 

	// Metodos Getters and Setters
	public int getCantPujas() {
		return cantPujas;
	}

	public void setCantPujas(int cantPujas) {
		this.cantPujas = cantPujas;
	}

	public ArrayList<Anuncio> getListaCompras() {
		return listaCompras;
	}

	public void setListaCompras(ArrayList<Anuncio> listaCompras) {
		this.listaCompras = listaCompras;
	}

//____________________________________________________________________ 

}
