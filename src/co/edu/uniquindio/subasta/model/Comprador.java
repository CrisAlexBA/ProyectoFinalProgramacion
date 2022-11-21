package co.edu.uniquindio.subasta.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
public class Comprador extends Usuario implements Serializable {

	// Atributos
	private int cantPujas;

	private double contadorPuja;
	private ArrayList <Anuncio> listaCompras = new ArrayList<>();

//____________________________________________________________________ 

	// Metodos Constructor

	// Constructor 1 (Actualizar un comprador)
	public Comprador(String nombre, String idUsuario, int edad, double dinero, int canPujas,
			ArrayList<Anuncio> listaCompras) {
		super(nombre, idUsuario, edad, dinero);
		this.cantPujas = canPujas;
		this.listaCompras = listaCompras;
	}

	public Comprador(String nombre, String idUsuario, int edad, double dinero, int canPujas,
					 ArrayList<Anuncio> listaCompras, Double sumPujas) {
		super(nombre, idUsuario, edad, dinero);
		this.cantPujas = canPujas;
		this.listaCompras = listaCompras;
		this.contadorPuja = sumPujas;
	}

	// Constructor 2 (Registro comprador)
	public Comprador(String nombre, String idUsuario, int edad) {
		super(nombre, idUsuario, edad, 0);
		this.cantPujas = 0;
		this.listaCompras = new ArrayList<Anuncio>();
	}

	// Constructor 3 (base)
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

	public void setAununcio (Anuncio a){
		listaCompras.add(a);
	}

	public void delete (Anuncio a){
		try {
			listaCompras.remove(a);
		}catch (Exception e){

		}

	}

	public double getContadorPuja() {
		return contadorPuja;
	}

	public void setContadorPuja(double contadorPuja) {
		this.contadorPuja = contadorPuja;
	}
//____________________________________________________________________

}
