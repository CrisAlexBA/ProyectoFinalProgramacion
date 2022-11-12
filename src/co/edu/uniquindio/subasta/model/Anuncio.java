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
	private int valor;
	private String foto;
	
	//Posiblemente necesario
	private Comprador pujante;

	// ____________________________________________________________________

	// Metodos constructor

	// Constructor 1 (Crud de un anuncio)
	public Anuncio(String nombreArticulo, String nombreAnunciante, String estado, String descripcion, String idAnuncio,
			TipoArticulo tipoArticulo, String fechaPublicacion, String fechaCumlinacion, int valor,
			String foto) {
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
		this.pujante = null;
	}

	// Constructor 2 (Carga de gestion de anuncios)
	public Anuncio(String nombreArticulo, String nombreAnunciante, TipoArticulo tipoArticulo,
			String fechaPublicacion, String fechaCumlinacion, int valor, String idAnuncio,
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
		this.pujante = null;
	}

	// Constructor 3 (carga de gestion de anuncios de un anunciante)
	public Anuncio(String nombreArticulo, String nombreAnunciante, String estado, String idAnuncio,
			TipoArticulo tipoArticulo, int valor, String image) {
		super();
		this.nombreAnunciante = nombreAnunciante;
		this.nombreArticulo = nombreArticulo;
		this.estado = estado;
		this.tipoArticulo = tipoArticulo;
		this.valor = valor;
		this.idAnuncio = idAnuncio;
		this.pujante = null;
	}
	// Constructor 4 (base)
	public Anuncio() {

	}
//____________________________________________________________________ 

	// Metodos Getters and Setters
	
	public Comprador getPujante() {
		return pujante;
	}

	public void setPujante(Comprador pujante) {
		this.pujante = pujante;
	}
	
	//___________________________________________________________________
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

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

//____________________________________________________________________ 

}
