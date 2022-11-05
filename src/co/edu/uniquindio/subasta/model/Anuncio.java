package co.edu.uniquindio.subasta.model;

import java.io.Serializable;

import javafx.scene.image.Image;

@SuppressWarnings("serial")
public class Anuncio implements Serializable {

	// Atributos
	private String nombreArticulo;
	private String nombreAnunciante;
	private String estado; // venta, noComprado, comprado
	private String descripcion;
	private String idAnuncio;
	private TipoArticulo tipoArticulo;
	private String fechaPublicacion;
	private String fechaCumlinacion;
	private double valor;
	private Image foto;

	// ____________________________________________________________________

	// Metodos constructor

	// Constructor 1
	public Anuncio(String nombreArticulo, String nombreAnunciante, String estado, String descripcion, String idAnuncio,
			TipoArticulo tipoArticulo, String fechaPublicacion, String fechaCumlinacion, double valor,
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

	// Constructor 2
	public Anuncio(String nombreArticulo, String nombreAnunciante, TipoArticulo tipoArticulo,
			String fechaPublicacion, String fechaCumlinacion, double valor, String idAnuncio,
			String descripcion) {
		super();
		this.nombreArticulo = nombreArticulo;
		this.nombreAnunciante = nombreAnunciante;
		this.tipoArticulo = tipoArticulo;
		this.fechaPublicacion = fechaPublicacion;
		this.fechaCumlinacion = fechaCumlinacion;
		this.valor = valor;
		this.idAnuncio = idAnuncio;
		this.descripcion = descripcion;
	}

	// Constructor 3
	public Anuncio(String nombreArticulo, String nombreAnunciante, String estado, String idAnuncio,
			TipoArticulo tipoArticulo, double valor, Image image) {
		super();
		this.nombreAnunciante = nombreAnunciante;
		this.nombreArticulo = nombreArticulo;
		this.estado = estado;
		this.tipoArticulo = tipoArticulo;
		this.valor = valor;
		this.idAnuncio = idAnuncio;
	}

	public Anuncio() {

	}
//____________________________________________________________________ 

	// Metodos Getters and Setters
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

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getFechaCumlinacion() {
		return fechaCumlinacion;
	}

	public void setFechaCumlinacion(String fechaCumlinacion) {
		this.fechaCumlinacion = fechaCumlinacion;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Image getFoto() {
		return foto;
	}

	public void setFoto(Image foto) {
		this.foto = foto;
	}

//____________________________________________________________________ 

}
