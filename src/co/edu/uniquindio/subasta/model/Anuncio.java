package co.edu.uniquindio.subasta.model;

import javafx.scene.control.DatePicker;

public class Anuncio extends Articulo{

	// Atributos
	private String nombreAnuncio;
	private String descripcion;
//	private image foto;
	private DatePicker  fechaPublicacion;
 	private DatePicker  fechaCumlinacion;
	private float valor;
	// ---------------------------------- 
	
	
	// Metodos Constructor
	
	
	public Anuncio(Articulo articulo, String nombreAnuncio, DatePicker fechaInicial, DatePicker fechaFin, String descripcion, float precio) {
		super(articulo.getUsuario(), articulo.getNombreArticulo(), articulo.getIdArticulo(), articulo.getTipoArticulo());
		this.nombreAnuncio = nombreAnuncio;
		this.fechaPublicacion = fechaInicial;
		this.fechaCumlinacion = fechaFin;
		this.descripcion = descripcion;
		this.valor = precio;
	}


	public String getNombreAnuncio() {
		return nombreAnuncio;
	}


	public void setNombreAnuncio(String nombreAnuncio) {
		this.nombreAnuncio = nombreAnuncio;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public DatePicker getFechaPublicacion() {
		return fechaPublicacion;
	}


	public void setFechaPublicacion(DatePicker fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}


	public DatePicker getFechaCumlinacion() {
		return fechaCumlinacion;
	}


	public void setFechaCumlinacion(DatePicker fechaCumlinacion) {
		this.fechaCumlinacion = fechaCumlinacion;
	}


	public float getValor() {
		return valor;
	}


	public void setValor(float valor) {
		this.valor = valor;
	}


	public Anuncio() {
		super();
	}
	// ---------------------------------- 
	
	

	// ---------------------------------- 
	 
	
}
