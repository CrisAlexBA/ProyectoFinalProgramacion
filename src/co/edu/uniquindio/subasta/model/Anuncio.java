package co.edu.uniquindio.subasta.model;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;

public class Anuncio{

	// Atributos
//	private String nombreAnuncio;
//	private String descripcion;
////	private image foto;
//	private DatePicker  fechaPublicacion;
// 	private DatePicker  fechaCumlinacion;
//	private float valor;
	
	private String nombreArticulo;
	private String nombreAnunciante;	
	private String estado; // venta, noComprado, comprado
	private String descripcion;
	private String idAnuncio;
	private TipoArticulo tipoArticulo;	
	private LocalDate  fechaPublicacion;
 	private LocalDate fechaCumlinacion;
	private float valor;
	private Image foto;

	// ---------------------------------- 

	public Anuncio(String nombreArticulo, String nombreAnunciante, String estado, String descripcion, String idAnuncio,
			TipoArticulo tipoArticulo, LocalDate fechaPublicacion, LocalDate fechaCumlinacion, float valor,
			Image foto) {
		super();
		this.nombreArticulo = nombreArticulo;
		this.nombreAnunciante = nombreAnunciante;
		this.estado = estado;
		this.descripcion = descripcion;
		this.idAnuncio = idAnuncio;
		this.tipoArticulo = tipoArticulo;
		this.fechaPublicacion = fechaPublicacion;
		this.fechaCumlinacion = fechaCumlinacion;
		this.valor = valor;
		this.foto = foto;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public String getNombreAnunciante() {
		return nombreAnunciante;
	}

	public void setNombreAnunciante(String nombreAnunciante) {
		this.nombreAnunciante = nombreAnunciante;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(String idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public TipoArticulo getTipoArticulo() {
		return tipoArticulo;
	}

	public void setTipoArticulo(TipoArticulo tipoArticulo) {
		this.tipoArticulo = tipoArticulo;
	}

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public LocalDate getFechaCumlinacion() {
		return fechaCumlinacion;
	}

	public void setFechaCumlinacion(LocalDate fechaCumlinacion) {
		this.fechaCumlinacion = fechaCumlinacion;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Image getFoto() {
		return foto;
	}

	public void setFoto(Image foto) {
		this.foto = foto;
	}



	
	// ---------------------------------- 
	
	

	// ---------------------------------- 
	
	
	// Metodos Constructor
	
	
//	public Anuncio(Articulo articulo, String nombreAnuncio, DatePicker fechaInicial, DatePicker fechaFin, String descripcion, float precio) {
//		super(articulo.getUsuario(), articulo.getNombreArticulo(), articulo.getIdArticulo(), articulo.getTipoArticulo());
//		this.nombreAnuncio = nombreAnuncio;
//		this.fechaPublicacion = fechaInicial;
//		this.fechaCumlinacion = fechaFin;
//		this.descripcion = descripcion;
//		this.valor = precio;
//	}
//
//	public String getNombreAnuncio() {
//		return nombreAnuncio;
//	}
//	
//	public void setNombreAnuncio(String nombreAnuncio) {
//		this.nombreAnuncio = nombreAnuncio;
//	}
//
//
//	public String getDescripcion() {
//		return descripcion;
//	}
//
//
//	public void setDescripcion(String descripcion) {
//		this.descripcion = descripcion;
//	}
//
//
//	public DatePicker getFechaPublicacion() {
//		return fechaPublicacion;
//	}
//
//
//	public void setFechaPublicacion(DatePicker fechaPublicacion) {
//		this.fechaPublicacion = fechaPublicacion;
//	}
//
//
//	public DatePicker getFechaCumlinacion() {
//		return fechaCumlinacion;
//	}
//
//
//	public void setFechaCumlinacion(DatePicker fechaCumlinacion) {
//		this.fechaCumlinacion = fechaCumlinacion;
//	}
//
//
//	public float getValor() {
//		return valor;
//	}
//
//
//	public void setValor(float valor) {
//		this.valor = valor;
//	}
//
//
//	public Anuncio() {
//		super();
//	}
	
}
