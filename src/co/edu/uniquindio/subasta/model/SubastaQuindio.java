package co.edu.uniquindio.subasta.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import co.edu.uniquindio.subasta.exceptions.AnuncianteException;
import co.edu.uniquindio.subasta.exceptions.CompradorException;
import co.edu.uniquindio.subasta.persistencia.Persistencia;

@SuppressWarnings("serial")
public class SubastaQuindio implements Serializable {

	// Atributos
	private String nombre;
	private ArrayList<Anunciante> listaAnunciantes = new ArrayList<Anunciante>();
	private ArrayList<Comprador> listaCompradores = new ArrayList<Comprador>();
	private ArrayList<Anuncio> listaAnuncios = new ArrayList<Anuncio>();
//____________________________________________________________________ 

	// Metodos constructor

	// Constructor 1
	public SubastaQuindio(String nombre) {
		super();
		this.nombre = nombre;
	}

	// Constructor 2
	public SubastaQuindio() {
		super();
	}
//____________________________________________________________________ 

	// Metodos Getters and Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Anuncio> getListaAnuncios() {
		return listaAnuncios;
	}

	public void setListaAnuncios(ArrayList<Anuncio> listaAnuncios) {
		this.listaAnuncios = listaAnuncios;
	}

	public ArrayList<Anunciante> getListaAnunciantes() {
		return listaAnunciantes;
	}

	public void setListaAnunciantes(ArrayList<Anunciante> listaAnunciantes) {
		this.listaAnunciantes = listaAnunciantes;
	}

	public ArrayList<Comprador> getListaCompradores() {
		return listaCompradores;
	}

	public void setListaCompradores(ArrayList<Comprador> listaCompradores) {
		this.listaCompradores = listaCompradores;
	}
//____________________________________________________________________ 

	/*
	 * Método que permite agregar un anunciante a la lista y mandarlo a persistencia
	 */
	public void agregarAnunciante(Anunciante anunciante) throws IOException {
		int bandera = 0;
		for (int i = 0; i < listaAnunciantes.size() && bandera == 0; i++) {
			if (listaAnunciantes.get(i).getIdUsuario().equals(anunciante.getIdUsuario())) {
				bandera = 1;
			}
		}
		if (bandera == 0) {
			listaAnunciantes.add(anunciante);
			Persistencia.guardarAnunciante(listaAnunciantes);
			System.out.println("Se agrego un nuevo anunciante .");
		} else {
			System.out.println("Este usuario ya existe");
		}

	}

	// ____________________________________________________________________________________

	/*
	 * Metodo que permite iniciar la sesion del anunciante
	 */
	public Anunciante iniciarSesionAnunciante(String anunciante, String contrasenia)
			throws FileNotFoundException, IOException, AnuncianteException {
		Anunciante anuncianteIS = validarAnunciante(anunciante, contrasenia);
		if (anuncianteIS != null) {
			return anuncianteIS;
		} else {
			throw new AnuncianteException("Usuario no existe");
		}

	}
	// _______________________________________________________________________________

	/*
	 * Metodo que permite validar que los datos que ingreso el anunciante en el
	 * login, si sean verdaderos
	 */
	private Anunciante validarAnunciante(String anunciante, String contrasenia)
			throws FileNotFoundException, IOException {
		ArrayList<Anunciante> anunciantes = listaAnunciantes;

		for (int indiceAnunciante = 0; indiceAnunciante < anunciantes.size(); indiceAnunciante++) {
			Anunciante anuncianteAux = anunciantes.get(indiceAnunciante);
			if (anuncianteAux.getNombre().equalsIgnoreCase(anunciante)
					&& anuncianteAux.getIdUsuario().equalsIgnoreCase(contrasenia)) {
				return anuncianteAux;
			}
		}
		return null;
	}
	// _______________________________________________________________________________

	public Anunciante cargarAnunciante(String idUsuario) throws IOException {

		ArrayList<Anunciante> contenido = listaAnunciantes;
		Anunciante anunciante = new Anunciante();
		for (int i = 0; i < contenido.size(); i++) {
			if (listaAnunciantes.get(i).getIdUsuario().equals(idUsuario)) {
				anunciante = listaAnunciantes.get(i);
				return anunciante;
			}
		}
		return null;
	}
	// ____________________________________________________________________

//____________________________________________________________________ 

	/*
	 * Método que permite agregar un comprador a la lista y mandarlo a persistencia
	 */
	public void agregarComprador(Comprador comprador) throws IOException {

		int bandera = 0;
		for (int i = 0; i < listaCompradores.size() && bandera == 0; i++) {
			if (listaCompradores.get(i).getIdUsuario().equals(comprador.getIdUsuario())) {
				bandera = 1;
			}
		}
		if (bandera == 0) {
			listaCompradores.add(comprador);

			// Guarda en los txt
			Persistencia.guardarComprador(listaCompradores);
			System.out.println("Se agrego un nuevo comprador.");
		} else {
			System.out.println("Este usuario ya existe");
		}

	}

	// _______________________________________________________________________________

	/*
	 * Metodo que permite iniciar la sesion del comprador
	 */

	public Comprador iniciarSesionComprador(String comprador, String contrasenia)
			throws FileNotFoundException, IOException, CompradorException {
		Comprador compradorIS = validarComprador(comprador, contrasenia);
		if (compradorIS != null) {
			return compradorIS;
		} else {
			throw new CompradorException("comprador no existe");
		}

	}
	// _______________________________________________________________________________

	/*
	 * Metodo que permite validar que los datos que ingreso el comprador en el
	 * login, si sean verdaderos
	 */
	private Comprador validarComprador(String comprador, String contrasenia) throws FileNotFoundException, IOException {
		ArrayList<Comprador> compradores = listaCompradores;

		for (int indicecomprador = 0; indicecomprador < compradores.size(); indicecomprador++) {
			Comprador compradorAux = compradores.get(indicecomprador);
			if (compradorAux.getNombre().equalsIgnoreCase(comprador)
					&& compradorAux.getIdUsuario().equalsIgnoreCase(contrasenia)) {
				return compradorAux;
			}
		}
		return null;
	}

//____________________________________________________________________ 

	/*
	 * Método que permite agregar un anuncio a la lista y mandarlo a persistencia
	 */
	public void agregarAnuncio(Anuncio anuncioNuevo) throws IOException {

			listaAnuncios.add(anuncioNuevo);
			Persistencia.guardarAnuncio(listaAnuncios);
			System.out.println("Se agrego un nuevo anuncio.");
	}
	// ____________________________________________________________________

	/*
	 * Método que permite actualizar un anunciante de la lista y mandarlo a
	 * persistencia
	 */
	public boolean actualizarAnunciante(String nombre, String idUsuario, int edad, double dinero, int canAnuncios,
			ArrayList<Anuncio> listaAnuncios) throws IOException {
		Anunciante anuncianteNu = new Anunciante(nombre, idUsuario, edad, dinero, canAnuncios, listaAnuncios);
		if (idUsuario != null) {
			for (int i = 0; i < listaAnunciantes.size(); i++) {
				if (listaAnunciantes.get(i).getIdUsuario().equals(idUsuario)) {
					listaAnunciantes.set(i, anuncianteNu);
					Persistencia.guardarAnunciante(listaAnunciantes);
					return true;
				}
			}
		}
		return false;
	}

	// ____________________________________________________________________

	/*
	 * Método que permite actualizar un comprador de la lista y mandarlo a
	 * persistencia
	 */
	public boolean actualizarComprador(String nombre, String idUsuario, int edad, double dinero, int canPujas,
			ArrayList<Anuncio> listaCompras) throws IOException {
		Comprador compradorNu = new Comprador(nombre, idUsuario, edad, dinero, canPujas, listaCompras);
		if (idUsuario != null) {
			for (int i = 0; i < listaCompradores.size(); i++) {
				if (listaCompradores.get(i).getIdUsuario().equals(idUsuario)) {
					listaCompradores.set(i, compradorNu);

					// Guarda en el txt
					Persistencia.guardarComprador(listaCompradores);
					return true;
				}
			}
		}
		return false;
	}

//____________________________________________________________________ 

	public Comprador cargarComprador(String idUsuario) throws IOException {

		ArrayList<Comprador> contenido = listaCompradores;
		Comprador comprador = new Comprador();
		for (int i = 0; i < contenido.size(); i++) {
			if (listaCompradores.get(i).getIdUsuario().equals(idUsuario)) {
				comprador = listaCompradores.get(i);
				return comprador;
			}
		}
		return null;
	}
//____________________________________________________________________ 

	/*
	 * Método que permite buscar un comprador en especifico y retornarlo.
	 */
	public Comprador leerComprador(String id) {
		if (id != null) {
			for (Comprador c : listaCompradores) {
				if (c.getIdUsuario().equals(id))
					return c;
			}
		}
		return null;
	}
//____________________________________________________________________ 

	public Anuncio cargarAnuncio(String idAnuncio) throws IOException {

		ArrayList<Anuncio> contenido = listaAnuncios;
		Anuncio anuncio = new Anuncio();
		for (int i = 0; i < contenido.size(); i++) {
			if (listaAnuncios.get(i).getIdAnuncio().equals(idAnuncio)) {
				anuncio = listaAnuncios.get(i);
				return anuncio;
			}
		}
		return null;
	}
//____________________________________________________________________ 

	public void eliminarAnuncio(Anuncio anuncio) throws IOException {

		String codigoAnuncio = anuncio.getIdAnuncio();

		for (int i = 0; i < listaAnuncios.size(); i++) {
			if (listaAnuncios.get(i).getIdAnuncio().equals(codigoAnuncio)) {
				listaAnuncios.remove(i);

				System.out.println("Se elimino el anuncio.");
				Persistencia.guardarAnuncio(listaAnuncios);
				break;
			}

		}

	}

}