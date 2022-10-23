package co.edu.uniquindio.subasta.persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import co.edu.uniquindio.subasta.exceptions.AnuncianteException;
import co.edu.uniquindio.subasta.exceptions.CompradorException;
import co.edu.uniquindio.subasta.model.Anunciante;
import co.edu.uniquindio.subasta.model.Comprador;
import co.edu.uniquindio.subasta.model.SubastaQuindio;


public class Persistencia {


	/*
	 * Las rutas de los archivos
	 */
	public static final String RUTA_ARCHIVO_ANUNCIANTES = "src/resources/ArchivoAnunciantes.txt";
	public static final String RUTA_ARCHIVO_COMPRADORES = "src/resources/ArchivoCompradores.txt";
	public static final String RUTA_ARCHIVO_ARTICULOS = "src/resources/ArchivoAnuncios.txt";
	public static final String RUTA_ARCHIVO_ANUNCIOS = "src/resources/ArchivoArticulos.txt";
	public static final String RUTA_ARCHIVO_SUBASTA = "src/resources/Subasta.xml";
	public static final String RUTA_ARCHIVO_SUBASTABINARIO = "src/resources/Subasta.txt";
	public static final String RUTA_ARCHIVO_LOG = "src/resources/SubastaLog.txt";
	
//	public static final String RUTA_ARCHIVO_ANUNCIANTES = "C:/td/persistencia/archivos/ArchivoAnunciantes.txt";
//	public static final String RUTA_ARCHIVO_COMPRADORES = "C:/td/persistencia//archivos/ArchivoCompradores.txt";
//	public static final String RUTA_ARCHIVO_ARTICULOS = "C:/td/persistencia/archivos/ArchivoAnuncios.txt";
//	public static final String RUTA_ARCHIVO_ANUNCIOS = "C:/td/persistencia/archivo/ArchivoArticulos.txt";
//	public static final String RUTA_ARCHIVO_SUBASTA = "C:/td/persistencia/Subasta.xml";
//	public static final String RUTA_ARCHIVO_SUBASTABINARIO = "C:/td/persistencia/Subasta.txt";
//	public static final String RUTA_ARCHIVO_LOG = "C:/td/persistencia/log/SubastaLog.txt";
	/**
	 * Guarda toda la informacion de los anunciantes
	 * @param listaEmpleados
	 * @throws IOException
	 */
	public static void guardarAnunciante(ArrayList<Anunciante> listaAnunciantes) throws IOException {


		String contenido = "";

		for (Anunciante anunciantes : listaAnunciantes) {
			contenido += anunciantes.getNombre() + "@@" + anunciantes.getIdUsuario() + "@@" + anunciantes.getEdad() + "@@" +
					anunciantes.getDinero() + "@@" + anunciantes.getCantAnuncios() +"\n";

		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ANUNCIANTES, contenido, true);
	}
	// _______________________________________________________________________________
	
	
	/**
	 * Metodo que permite guardar un comprador nuevo en el archivo txt
	 * @param listacompradores
	 * @throws IOException
	 */
	public static void guardarComprador(ArrayList<Comprador> listacompradores) throws IOException {


		String contenido = "";

		for (Comprador compradores : listacompradores) {
			contenido += compradores.getNombre() + "@@" + compradores.getIdUsuario() + "@@" + compradores.getEdad() + "@@" +
					compradores.getDinero() + "@@" + compradores.getCantPujas() +"\n";

		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_COMPRADORES, contenido, true);
	}
	//	 _________________________________________________________________________________	


	/*
	 * Metodo que permite cargar los anunciantes
	 */
	public static ArrayList<Anunciante> cargarAnunciantes(String ruta) throws IOException {

		ArrayList<Anunciante> anunciantes = new ArrayList<Anunciante>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(ruta);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Anunciante anunciante = new Anunciante();
			anunciante.setNombre(linea.split("@@")[0]);
			anunciante.setIdUsuario(linea.split("@@")[1]);
			anunciante.setEdad(Integer.parseInt(linea.split("@@")[2]));
			anunciante.setDinero(Float.parseFloat(linea.split("@@")[3]));
			anunciante.setCantAnuncios(Integer.parseInt(linea.split("@@")[4]));
			//Dudas de como maneja el array de articulos
			anunciante.setListaArticulos(null);
			anunciantes.add(anunciante);

		}
		return anunciantes;
	}
	//____________________________________________________________________________________
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
			compradorCom.setDinero(Float.parseFloat(linea.split("@@")[3]));
			compradorCom.setCantPujas(Integer.parseInt(linea.split("@@")[4]));
			if(compradorCom.getIdUsuario().equalsIgnoreCase(idUsuario)) {
				comprador = compradorCom;
				return comprador;
			}

		}
		return null;
	}
	//	 _________________________________________________________________________________
	
	
	/*
	 * Metodo que permite cargar los compradores del archivo txt
	 */
	public static ArrayList<Comprador> cargarCompradores(String ruta) throws IOException {

		ArrayList<Comprador> compradors = new ArrayList<Comprador>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(ruta);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Comprador comprador = new Comprador();
			comprador.setNombre(linea.split("@@")[0]);
			comprador.setIdUsuario(linea.split("@@")[1]);
			comprador.setEdad(Integer.parseInt(linea.split("@@")[2]));
			comprador.setDinero(Float.parseFloat(linea.split("@@")[3]));
			comprador.setCantPujas(Integer.parseInt(linea.split("@@")[4]));
			//Dudas de como maneja el array de articulos
			comprador.setListaArticulos(null);
			compradors.add(comprador);

		}
		return compradors;
	}
	// _______________________________________________________________________________
	
	
	/*
	 * Metodo que permite iniciar sesion a los anunciantes
	 */
	public static boolean iniciarSesionAnunciante(String anunciante, String contrasenia) throws FileNotFoundException, IOException, AnuncianteException {

		if(validarAnunciante(anunciante,contrasenia)) {
			return true;
		}else {
			throw new AnuncianteException("Usuario no existe");
		}

	}
	// _______________________________________________________________________________
	

	/*
	 * Metodo que permite validar si los datos que ingresa el anunciante en el login son validos
	 * de lo contrario manda una excepcion
	 */
	private static boolean validarAnunciante(String anunciante, String contrasenia) throws FileNotFoundException, IOException 
	{
		ArrayList<Anunciante> anunciantes = Persistencia.cargarAnunciantes(RUTA_ARCHIVO_ANUNCIANTES);

		for (int indiceAnunciante = 0; indiceAnunciante < anunciantes.size(); indiceAnunciante++) 
		{
			Anunciante anuncianteAux = anunciantes.get(indiceAnunciante);
			if(anuncianteAux.getNombre().equalsIgnoreCase(anunciante) && anuncianteAux.getIdUsuario().equalsIgnoreCase(contrasenia)) {
				return true;
			}
		}
		return false;
	}
	// _______________________________________________________________________________
	
	
	/*
	 * Metodo que permite iniciar la sesion del comprador
	 */
	public static boolean iniciarSesionComprador(String comprador, String contrasenia) throws FileNotFoundException, IOException, CompradorException {

		if(validarComprador(comprador,contrasenia)) {
			return true;
		}else {
			throw new CompradorException("comprador no existe");
		}

	}
	// _______________________________________________________________________________
	
	
	/*
	 * Metodo que permite validar que los datos que ingreso el comprador en el login,
	 * si sean verdaderos
	 */
	private static boolean validarComprador(String comprador, String contrasenia) throws FileNotFoundException, IOException 
	{
		ArrayList<Comprador> compradores = Persistencia.cargarCompradores(RUTA_ARCHIVO_COMPRADORES);

		for (int indicecomprador = 0; indicecomprador < compradores.size(); indicecomprador++) 
		{
			Comprador compradorAux = compradores.get(indicecomprador);
			if(compradorAux.getNombre().equalsIgnoreCase(comprador) && compradorAux.getIdUsuario().equalsIgnoreCase(contrasenia)) {
				return true;
			}
		}
		return false;
	}
	// _______________________________________________________________________________
	
	
	/*
	 * Metodo que permite guardar todo lo que se haga en la aplicacion en un archivo xml
	 */
	public static void guardarXML(SubastaQuindio subasta) throws IOException {

		ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_SUBASTA, subasta);

	}
	// _______________________________________________________________________________
	
	
	/*
	 * Metodo que permite guardar todo loq ue se haga en la aplicacion en un archivo binario
	 */
	public static void guardarBinario(SubastaQuindio subasta) throws Exception {

		ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_SUBASTABINARIO, subasta);

	}
	// _______________________________________________________________________________
	
	
	/*
	 * Metodo que permite cargar la aplicacion mediante un archivo serializadoXML
	 */
	public static Object cargarXML() throws IOException {

		return ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_SUBASTA);

	}
	// _______________________________________________________________________________
	
	/*
	 * Metodo que permite hacer un log de lo que hacen los usuarios
	 */
	public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
	{
		
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
	}
	
}
