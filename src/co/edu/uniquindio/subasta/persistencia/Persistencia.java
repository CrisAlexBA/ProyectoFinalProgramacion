package co.edu.uniquindio.subasta.persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import co.edu.uniquindio.subasta.exceptions.AnuncianteException;
import co.edu.uniquindio.subasta.exceptions.CompradorException;
import co.edu.uniquindio.subasta.model.Anunciante;
import co.edu.uniquindio.subasta.model.Anuncio;
import co.edu.uniquindio.subasta.model.Comprador;
import co.edu.uniquindio.subasta.model.SubastaQuindio;
import co.edu.uniquindio.subasta.model.TipoArticulo;
import javafx.scene.image.Image;

public class Persistencia {

	/*
	 * Las rutas de los archivos
	 */
	public static final String RUTA_ARCHIVO_ANUNCIANTES = "src/co/edu/uniquindio/subasta/persistencia/archivos/ArchivoAnunciantes.txt";
	public static final String RUTA_ARCHIVO_COMPRADORES = "src/co/edu/uniquindio/subasta/persistencia/archivos/ArchivoCompradores.txt";
	public static final String RUTA_ARCHIVO_ANUNCIOS = "src/co/edu/uniquindio/subasta/persistencia/archivos/ArchivoAnuncios.txt";
	public static final String RUTA_ARCHIVO_SUBASTA = "src/co/edu/uniquindio/subasta/persistencia/archivos/SubastaXML.xml";
	public static final String RUTA_ARCHIVO_SUBASTABINARIO = "src/co/edu/uniquindio/subasta/persistencia/archivos/SubastaBinario.dat";
	public static final String RUTA_ARCHIVO_LOG = "src/co/edu/uniquindio/subasta/persistencia/log/SubastaLog.txt";
	public static final String RUTA_ARCHIVO_COPIASEGURIDAD = "src/co/edu/uniquindio/subasta/persistencia/respaldo/CopiaSeguridadSubasta.xml";

//	public static final String RUTA_ARCHIVO_ANUNCIANTES = "C:/td/persistencia/archivos/ArchivoAnunciantes.txt";
//	public static final String RUTA_ARCHIVO_COMPRADORES = "C:/td/persistencia//archivos/ArchivoCompradores.txt";
//	public static final String RUTA_ARCHIVO_ANUNCIOS = "C:/td/persistencia/archivo/ArchivoArticulos.txt";
//	public static final String RUTA_ARCHIVO_SUBASTA = "C:/td/persistencia/Subasta.xml";
//	public static final String RUTA_ARCHIVO_SUBASTABINARIO = "C:/td/persistencia/Subasta.txt";
//	public static final String RUTA_ARCHIVO_LOG = "C:/td/persistencia/log/SubastaLog.txt";
//	public static final String RUTA_ARCHIVO_COPIASEGURIDAD = "C:/td/persistencia/respaldo/CopiaSeguridadSubasta.xml";

	// ---------------------------------- Métodos Comprador
	// ----------------------------------

	/**
	 * Metodo que permite guardar un comprador nuevo en el archivo txt
	 * 
	 * @param listacompradores
	 * @throws IOException
	 */
	public static void guardarComprador(ArrayList<Comprador> listacompradores) throws IOException {

		String contenido = "";

		for (Comprador compradores : listacompradores) {
			contenido += compradores.getNombre() + "@@" + compradores.getIdUsuario() + "@@" + compradores.getEdad()
					+ "@@" + compradores.getDinero() + "@@" + compradores.getCantPujas() + "@@"
					+ compradores.getListaCompras() + "\n";

		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_COMPRADORES, contenido, false);
	}
	// _________________________________________________________________________________

	/*
	 * Metodo que permite cargar un comprador especifico del archivo txt y lo
	 * retorne
	 */
	public static Comprador cargarComprador(String idUsuario) throws IOException {

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_COMPRADORES);
		Comprador comprador = new Comprador();
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Comprador compradorCom = new Comprador();
			compradorCom.setNombre(linea.split("@@")[0]);
			compradorCom.setIdUsuario(linea.split("@@")[1]);
			compradorCom.setEdad(Integer.parseInt(linea.split("@@")[2]));
			compradorCom.setDinero(Double.parseDouble(linea.split("@@")[3]));
			compradorCom.setCantPujas(Integer.parseInt(linea.split("@@")[4]));
			if (compradorCom.getIdUsuario().equalsIgnoreCase(idUsuario)) {
				comprador = compradorCom;
				return comprador;
			}

		}
		return null;
	}
	// _________________________________________________________________________________

	/*
	 * Metodo que permite cargar los compradores del archivo txt
	 */
	public static ArrayList<Comprador> cargarCompradores() throws IOException {

		ArrayList<Comprador> compradors = new ArrayList<Comprador>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_COMPRADORES);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Comprador comprador = new Comprador();
			comprador.setNombre(linea.split("@@")[0]);
			comprador.setIdUsuario(linea.split("@@")[1]);
			comprador.setEdad(Integer.parseInt(linea.split("@@")[2]));
			comprador.setDinero(Double.parseDouble(linea.split("@@")[3]));
			comprador.setCantPujas(Integer.parseInt(linea.split("@@")[4]));
			compradors.add(comprador);

		}
		return compradors;
	}
	// _______________________________________________________________________________

	/*
	 * Metodo que permite iniciar la sesion del comprador
	 */

	public static Comprador iniciarSesionComprador(String comprador, String contrasenia)
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
	private static Comprador validarComprador(String comprador, String contrasenia)
			throws FileNotFoundException, IOException {
		ArrayList<Comprador> compradores = Persistencia.cargarCompradores();

		for (int indicecomprador = 0; indicecomprador < compradores.size(); indicecomprador++) {
			Comprador compradorAux = compradores.get(indicecomprador);
			if (compradorAux.getNombre().equalsIgnoreCase(comprador)
					&& compradorAux.getIdUsuario().equalsIgnoreCase(contrasenia)) {
				return compradorAux;
			}
		}
		return null;
	}
	// _______________________________________________________________________________

	// ---------------------------------- Métodos Anunciante
	// ----------------------------------

	/**
	 * Guarda toda la informacion de los anunciantes
	 */
	public static void guardarAnunciante(ArrayList<Anunciante> listaAnunciantes) throws IOException {

		String contenido = "";

		for (Anunciante anunciantes : listaAnunciantes) {
			contenido += anunciantes.getNombre() + "@@" + anunciantes.getIdUsuario() + "@@" + anunciantes.getEdad()
					+ "@@" + anunciantes.getDinero() + "@@" + anunciantes.getCantAnuncios() + "@@"
					+ anunciantes.getListaAnuncios() + "\n";

		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ANUNCIANTES, contenido, false);
	}
	// _______________________________________________________________________________

	/*
	 * Metodo que permite cargar un anunciante especifico del archivo txt y lo
	 * retorne
	 */
	public static Anunciante cargarAnunciante(String idUsuario) throws IOException {

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ANUNCIANTES);
		Anunciante anunciante = new Anunciante();
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Anunciante anuncianteCom = new Anunciante();
			anuncianteCom.setNombre(linea.split("@@")[0]);
			anuncianteCom.setIdUsuario(linea.split("@@")[1]);
			anuncianteCom.setEdad(Integer.parseInt(linea.split("@@")[2]));
			anuncianteCom.setDinero(Double.parseDouble(linea.split("@@")[3]));
			anuncianteCom.setCantAnuncios(Integer.parseInt(linea.split("@@")[4]));
			if (anuncianteCom.getIdUsuario().equalsIgnoreCase(idUsuario)) {
				anunciante = anuncianteCom;
				return anunciante;
			}

		}
		return null;
	}
	// _______________________________________________________________________________

	/*
	 * Metodo que permite cargar los anunciantes
	 */
	public static ArrayList<Anunciante> cargarAnunciantes() throws IOException {

		ArrayList<Anunciante> anunciantes = new ArrayList<Anunciante>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ANUNCIANTES);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Anunciante anunciante = new Anunciante();
			anunciante.setNombre(linea.split("@@")[0]);
			anunciante.setIdUsuario(linea.split("@@")[1]);
			anunciante.setEdad(Integer.parseInt(linea.split("@@")[2]));
			anunciante.setDinero(Double.parseDouble(linea.split("@@")[3]));
			anunciante.setCantAnuncios(Integer.parseInt(linea.split("@@")[4]));
			anunciantes.add(anunciante);

		}
		return anunciantes;
	}
	// ____________________________________________________________________________________

	/*
	 * Metodo que permite iniciar la sesion del anunciante
	 */
	public static Anunciante iniciarSesionAnunciante(String anunciante, String contrasenia)
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
	private static Anunciante validarAnunciante(String anunciante, String contrasenia)
			throws FileNotFoundException, IOException {
		ArrayList<Anunciante> anunciantes = Persistencia.cargarAnunciantes();

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

	// ---------------------------------- Métodos Anuncio
	// ----------------------------------

	/*
	 * Guarda toda la informacion de un anuncio
	 */
	public static void guardarAnuncio(ArrayList<Anuncio> listaAnuncios) throws IOException {
		String contenido = "";

		for (Anuncio anuncios : listaAnuncios) {
			contenido += anuncios.getNombreArticulo() + "@@" + anuncios.getNombreAnunciante() + "@@"
					+ anuncios.getEstado() + "@@" + anuncios.getDescripcion() + "@@" + anuncios.getIdAnuncio() + "@@"
					+ anuncios.getTipoArticulo() + "@@" + anuncios.getFechaPublicacion() + "@@"
					+ anuncios.getFechaCumlinacion() + "@@" + anuncios.getValor() + "@@" + anuncios.getFoto() + "\n";

		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ANUNCIOS, contenido, false);

	}
	// _______________________________________________________________________________

	/*
	 * Metodo que permite cargar los anuncios del archivo txt
	 */
	private static ArrayList<Anuncio> cargarAnuncios() throws IOException {
		ArrayList<Anuncio> anuncios = new ArrayList<Anuncio>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ANUNCIOS);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Anuncio anuncio = new Anuncio();
			anuncio.setNombreArticulo((linea.split("@@")[0]));
			anuncio.setNombreAnunciante((linea.split("@@")[1]));
			anuncio.setEstado((linea.split("@@")[2]));
			anuncio.setDescripcion((linea.split("@@")[3]));
			anuncio.setIdAnuncio((linea.split("@@")[4]));
			anuncio.setTipoArticulo((TipoArticulo.valueOf(linea.split("@@")[5])));
			anuncio.setFechaPublicacion(((linea.split("@@")[6])));
			anuncio.setFechaCumlinacion((linea.split("@@")[7]));
			anuncio.setValor(Integer.parseInt((linea.split("@@")[8])));
			anuncio.setFoto(null);
			anuncios.add(anuncio);

		}
		return anuncios;
	}
	// _______________________________________________________________________________

	/*
	 * Metodo que permite cargar un comprador especifico del archivo txt y lo
	 * retorne
	 */
	public static Anuncio cargarAnuncio(String idUsuario) throws IOException {

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ANUNCIOS);
		Anuncio anuncio = new Anuncio();
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Anuncio anuncioCom = new Anuncio();
			anuncioCom.setNombreArticulo((linea.split("@@")[0]));
			anuncioCom.setNombreAnunciante((linea.split("@@")[1]));
			anuncioCom.setEstado((linea.split("@@")[2]));
			anuncioCom.setDescripcion((linea.split("@@")[3]));
			anuncioCom.setIdAnuncio((linea.split("@@")[4]));
			anuncioCom.setTipoArticulo((TipoArticulo.valueOf(linea.split("@@")[5])));
			anuncioCom.setFechaPublicacion(linea.split("@@")[6]);
			anuncioCom.setFechaCumlinacion(linea.split("@@")[7]);
			anuncioCom.setValor(Integer.parseInt((linea.split("@@")[8])));
			anuncioCom.setFoto(null);
			if (anuncioCom.getIdAnuncio().equalsIgnoreCase(idUsuario)) {
				anuncio = anuncioCom;
				return anuncio;
			}

		}
		return null;
	}
	// ---------------------------------- Métodos Carga Datos
	// ----------------------------------

	/*
	 * Método que permite cargar toda la información del programa por los txt
	 */
	public static void cargarDatosArchivos(SubastaQuindio subasta) throws IOException {

		// cargar archivo de compradores
		ArrayList<Comprador> compradoresCargados = cargarCompradores();

		if (compradoresCargados.size() > 0)
			subasta.getListaCompradores().addAll(compradoresCargados);

		// cargar archivos anunciantes
		ArrayList<Anunciante> anuncianteCargados = cargarAnunciantes();

		if (anuncianteCargados.size() > 0)
			subasta.getListaAnunciantes().addAll(anuncianteCargados);

		// cargar archivo anuncios
		ArrayList<Anuncio> anunciosCargados = cargarAnuncios();

		if (anunciosCargados.size() > 0)
			subasta.getListaAnuncios().addAll(anunciosCargados);

	}

// _______________________________________________________________________________

	/*
	 * Metodo que permite guardar todo lo que se haga en la aplicacion en un archivo
	 * xml
	 */
	public static void guardarRecursoSubastaQuindioXML(SubastaQuindio subastaQuindio) {

		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_SUBASTA, subastaQuindio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// _______________________________________________________________________________

	/*
	 * Metodo que permite cargar la aplicacion mediante un archivo serializadoXML
	 */
	public static SubastaQuindio cargarRecursoSubastaQuindioXML() {

		SubastaQuindio SubastaQuindio = null;

		try {
			SubastaQuindio = (SubastaQuindio) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_SUBASTA);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SubastaQuindio;

	}

// _______________________________________________________________________________

	/*
	 * Metodo que permite cargar la aplicacion mediante un archivo
	 * serializadoBinario
	 */
	public static SubastaQuindio cargarRecursoSubastaQuindioBinario() {

		SubastaQuindio SubastaQuindio = null;

		try {
			SubastaQuindio = (SubastaQuindio) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_SUBASTABINARIO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SubastaQuindio;
	}
// _______________________________________________________________________________

	/*
	 * Metodo que permite guardar todo lo que se haga en la aplicacion en un archivo
	 * binario
	 */
	public static void guardarRecursoSubastaQuindioBinario(SubastaQuindio subastaQuindio) {

		try {
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_SUBASTABINARIO, subastaQuindio);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Hay un error al guardar el archivo en binario");
		}
	}
// _______________________________________________________________________________

	/*
	 * Metodo que permite hacer un log de lo que hacen los usuarios
	 */
	public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {

		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
	}

// _______________________________________________________________________________

	/**
	 * Metodo que llama a archivo Util CopiadeseguridadArchivo enviandole la ruta
	 * donde se va a almacenar la copia
	 */
	public static void copiaSeguridad() {
		ArchivoUtil.copiaSeguridadArchivo(RUTA_ARCHIVO_SUBASTA, RUTA_ARCHIVO_COPIASEGURIDAD);
	}
// _______________________________________________________________________________

}
