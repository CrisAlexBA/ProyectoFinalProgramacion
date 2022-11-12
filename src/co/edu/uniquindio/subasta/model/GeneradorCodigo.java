package co.edu.uniquindio.subasta.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GeneradorCodigo implements Serializable {
	
	/*
	 * Atributos
	 */
	private int dato;
	private int cont = 1;
	private String num = "";

	//_______________________________________________________________________________________
	
	/*
	 * Método que genera un codigo unico dentro del programa
	 */
	public void generar(int dato) {
		this.dato = dato;

		if ((this.dato >= 1000) || (this.dato < 10000)) {
			int can = cont + this.dato;
			num = "CP" + can;
		}
		if ((this.dato >= 100) || (this.dato < 1000)) {
			int can = cont + this.dato;
			num = "CP0" + can;
		}
		if ((this.dato >= 9) && (this.dato < 100)) {
			int can = cont + this.dato;
			num = "CP00" + can;
		}
		if ((this.dato >= 1) && (this.dato < 9)) {
			int can = cont + this.dato;
			num = "CP000" + can;
		}
		if (this.dato == 0) {
			int can = cont + this.dato;
			num = "CP000" + can;
		}
	}
	
	//_______________________________________________________________________________________
	
	/*
	 * Método que retorna dicho codigo
	 */

	public String serie() {
		return this.num;
	}
	
	//_______________________________________________________________________________________

}
