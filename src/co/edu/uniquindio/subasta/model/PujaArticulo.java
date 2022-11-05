package co.edu.uniquindio.subasta.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PujaArticulo implements Serializable {

	// Atributo
	private float valorPuja;
	// -------------------------------

	// Metodos Constructor
	public PujaArticulo(float valorPuja) {
		super();
		this.valorPuja = valorPuja;
	}

	public PujaArticulo() {
		super();
	}
	// -------------------------------

	// Metodos Get and Set
	public float getValorPuja() {
		return valorPuja;
	}

	public void setValorPuja(float valorPuja) {
		this.valorPuja = valorPuja;
	}
	// -------------------------------

}