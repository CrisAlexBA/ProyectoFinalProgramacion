package co.edu.uniquindio.subasta.model;

import java.util.ArrayList;

public class Comprador extends Usuario{

	// Atributos
	private int cantPujas;
	// ------------------------

	
	// Metodos Constructor
	public Comprador() {
		super();
	}
	
	// ------------------------
	
	
	public Comprador(String nombre, String idUsuario, int edad) {
		super(nombre, idUsuario, edad, 0);
		this.cantPujas = 0;
	}
	// Metodos Get and Set
	public int getCantPujas() {
		return cantPujas;
	}
	public void setCantPujas(int cantPujas) {
		this.cantPujas = cantPujas;
	}
	// ------------------------
	
}
