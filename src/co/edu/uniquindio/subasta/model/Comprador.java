package co.edu.uniquindio.subasta.model;

import java.util.ArrayList;

public class Comprador extends Usuario{

	// Atributos
	private int cantPujas;
	private ArrayList<Anuncio> listaCompras;
	// ------------------------

	
	// Metodos Constructor
	public Comprador() {
		super();
	}
	
	public Comprador(String nombre, String idUsuario, int edad) {
		super(nombre, idUsuario, edad, 0);
		this.cantPujas = 0;
		this.listaCompras = new ArrayList<Anuncio>();
	}
	// ------------------------
	
	

	// Metodos Get and Set
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

	// ------------------------
	
}
