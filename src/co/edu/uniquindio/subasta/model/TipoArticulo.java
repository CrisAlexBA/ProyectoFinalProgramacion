package co.edu.uniquindio.subasta.model;

import java.io.Serializable;

public enum TipoArticulo implements Serializable {

	//Posibles selecciones
	HOGAR(0), TECNOLOGIA(1), DEPORTES(2), VEHICULOS(3), VIENESRAIZ(4);

	// Atributos
	private int tipoProducto;

//____________________________________________________________________ 

	// Metodo Constructor
	private TipoArticulo(int tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
//____________________________________________________________________ 

	// Metodos Getters and Setters
	public int getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(int tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

//____________________________________________________________________ 

}
