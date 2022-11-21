package co.edu.uniquindio.subasta.controller;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.subasta.exceptions.AnuncianteException;
import co.edu.uniquindio.subasta.exceptions.CompradorException;
import co.edu.uniquindio.subasta.model.Anunciante;
import co.edu.uniquindio.subasta.model.Anuncio;
import co.edu.uniquindio.subasta.model.Comprador;
import co.edu.uniquindio.subasta.model.GeneradorCodigo;
import co.edu.uniquindio.subasta.model.SubastaQuindio;
import co.edu.uniquindio.subasta.persistencia.Persistencia;

public class ModelFactoryController {

	// Atributos
	SubastaQuindio subasta;
	Anunciante anunciante;
	Comprador comprador;
	private GeneradorCodigo generadorCodigo;
	private String ultCodigo;

	// ------------------------------ Singleton ------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aqu� al ser
		// protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// M�todo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	public ModelFactoryController() {

		cargarResourceXML();
		
		
		if (subasta == null) {
			System.out.println("es null");
		}

	}

	/*
	 * Metodos Get and Set
	 */
	public SubastaQuindio getSubasta() {
		return subasta;
	}

	public void setSubasta(SubastaQuindio subasta) {
		this.subasta = subasta;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}

//----------------------------------     Métodos Comprador     ----------------------------------

	/*
	 * Metodo que permite agregar un comprador a la lista
	 */
	public void agregarComprador(Comprador comprador) throws IOException {

		subasta.agregarComprador(comprador);

	}
//____________________________________________________________________ 

	/*
	 * Metodo que permite iniciar la sesion del comprador
	 */
	public boolean inicioSesionComprador(String nombre, String contrasenia) {
		try {
			this.comprador = subasta.iniciarSesionComprador(nombre, contrasenia);
			return comprador != null;
		} catch (IOException | CompradorException e) {
			e.printStackTrace();
		}
		return false;
	}
//____________________________________________________________________ 

	/*
	 * Método que permite enviar los datos del controlador al modelo para modificar
	 * un comprador
	 */

	public void actualizarComprador(String nombre, String idUsuario, int edad, double dinero, int canPujas,
									ArrayList<Anuncio> listaCompras) throws IOException {
		boolean bandera = getSubasta().actualizarComprador(nombre, idUsuario, edad, dinero, canPujas, listaCompras);
		if (bandera == true) {
			this.comprador = new Comprador(nombre, idUsuario, edad, dinero, canPujas, listaCompras);
		}
	}

	public void actualizarComprador(String nombre, String idUsuario, int edad, double dinero, int canPujas,
									ArrayList<Anuncio> listaCompras, double contadorpuja) throws IOException {
		boolean bandera = getSubasta().actualizarComprador(nombre, idUsuario, edad, dinero, canPujas, listaCompras,contadorpuja);
		if (bandera == true) {
			this.comprador = new Comprador(nombre, idUsuario, edad, dinero, canPujas, listaCompras, contadorpuja);
		}
	}

//____________________________________________________________________ 

	/*
	 * Método que trae un comprador en especifico de persistencia con el id
	 */
	public Comprador traerComprador(String idUsuario) {

		try {
			return subasta.cargarComprador(idUsuario);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

//  _________________________________________________________________________

//----------------------------------     Métodos Anunciante     ----------------------------------

	/*
	 * Metodo que permite agregar un anunciante a la lista
	 */
	public void agregarAnunciante(Anunciante anunciante) throws IOException {
		subasta.agregarAnunciante(anunciante);
	}
//____________________________________________________________________ 

	/*
	 * Metodo que permite hacer el inicio de sesion de un anunciante
	 */
	public boolean inicioSesionAnunciante(String nombre, String idUsuario) throws AnuncianteException {
		try {
			this.anunciante = subasta.iniciarSesionAnunciante(nombre, idUsuario);

			return anunciante != null;

		} catch (IOException | AnuncianteException e) {
			e.printStackTrace();
		}
		return false;
	}
//____________________________________________________________________ 

	/*
	 * Método que permite enviar los datos del controlador al modelo para modificar
	 * un anunciante
	 */
	public void actualizarAnunciante(String nombre, String idUsuario, int edad, double dinero, int canAnuncios,
			ArrayList<Anuncio> listaAnuncios) throws IOException {
		boolean bandera = getSubasta().actualizarAnunciante(nombre, idUsuario, edad, dinero, canAnuncios,
				listaAnuncios);
		if (bandera == true) {
			this.anunciante = new Anunciante(nombre, idUsuario, edad, dinero, canAnuncios, listaAnuncios);
		}
	}

//____________________________________________________________________ 

	/*
	 * Método que trae un anunciante en especifico de persistencia con el id
	 */
	public Anunciante traerAnunciante(String idUsuario) {
		try {
			return subasta.cargarAnunciante(idUsuario);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}
//____________________________________________________________________ 

	/*
	 * Metodo que permite traer la lista de los anunciantes
	 */
	public ArrayList<Anunciante> traerListaAnunciantes() {
		return subasta.getListaAnunciantes();
	}

//____________________________________________________________________ 

//-----------------------------------     Métodos Anuncio     -----------------------------------

	/*
	 * Método que conecta al contructor con el modelo para agregar un anuncio
	 */
	public void agregarAnuncio(Anuncio anuncioNuevo) throws IOException {
		subasta.agregarAnuncio(anuncioNuevo);

	}

//____________________________________________________________________ 

	/*
	 * Método que permite traer la lista de los anuncios para ser manejada
	 */
	public ArrayList<Anuncio> traerListaAnuncios() {
		return subasta.getListaAnuncios();
	}

//____________________________________________________________________ 

	/*
	 * Metodo que llama la clase generador de codigo para generar un codigo unico
	 */

	public void GenerarCodigo() {
		generadorCodigo = new GeneradorCodigo();
		int lastIdx = 0;
		if(subasta.getListaAnuncios().size() == 0) {
			this.ultCodigo = subasta.getListaAnuncios().get(lastIdx).getIdAnuncio();
		}else {
			lastIdx = subasta.getListaAnuncios().size() - 1;
			this.ultCodigo = subasta.getListaAnuncios().get(lastIdx).getIdAnuncio();
		}
		
		if (ultCodigo == null) {
			ultCodigo = "CP0001";
		} else {
			char r1 = ultCodigo.charAt(2);
			char r2 = ultCodigo.charAt(3);
			char r3 = ultCodigo.charAt(4);
			char r4 = ultCodigo.charAt(5);
			String juntar = "" + r1 + r2 + r3 + r4;
			int var = Integer.parseInt(juntar);
			generadorCodigo.generar(var);
		}
	}

	public String EnviarCodigo() {
		GenerarCodigo();
		this.ultCodigo = generadorCodigo.serie();
		return ultCodigo;
	}

	// ____________________________________________________________________

	/*
	 * 
	 */

	public Anuncio traerAnuncio(String idAnuncio) {

		try {
			return subasta.cargarAnuncio(idAnuncio);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}
//-------------------------------     Métodos Guardado Datos     -------------------------------
//____________________________________________________________________ 

	/*
	 * Método que permite cargar el programa de los archivos txt
	 */
	private void cargarDatosDesdeArchivos() {

		subasta = new SubastaQuindio();

		try {

			Persistencia.cargarDatosArchivos(getSubasta());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//____________________________________________________________________ 	

	/*
	 * Método que permite generar un registro de todo lo que hacen los usuarios en
	 * el programa y lo manda a persistencia para que lo guarde
	 */
	public void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
		Persistencia.guardaRegistroLog(mensajeLog, nivel, accion);
	}
//____________________________________________________________________ 

	/*
	 * Metodo que permite cargar la app del archivo dat
	 */
	public void cargarResourceBinario() {
		subasta = new SubastaQuindio();
		subasta = Persistencia.cargarRecursoSubastaQuindioBinario();
	}
//____________________________________________________________________ 

	/*
	 * Metodo que permite guardar lo que pase en la app en un archivo binario
	 */
	public void guardarResourceBinario() {

		Persistencia.guardarRecursoSubastaQuindioBinario(subasta);
	}
//____________________________________________________________________ 

	/*
	 * Metodo que permite cargar la app del archivo xml
	 */
	public void cargarResourceXML() {
		subasta = new SubastaQuindio();
		subasta = Persistencia.cargarRecursoSubastaQuindioXML();
	}
//____________________________________________________________________ 

	/*
	 * Metodo que permite guardar lo que pase en la app en un archivo xml
	 */
	public void guardarResourceXML() {

		Persistencia.guardarRecursoSubastaQuindioXML(subasta);
	}
//____________________________________________________________________ 

	/*
	 * Método que permite mandar a guardar a persistencia una copia del programa en
	 * archivo xml
	 */
	public static void copiaSeguridad() {
		Persistencia.copiaSeguridad();
	}
//____________________________________________________________________ 

	/*
	 * Método utilizado para hacer pruebas de escritorio del programa
	 */
	public void inicializarDatos() {

//		SubastaQuindio subasta = new SubastaQuindio();

//		Comprador anuncianteNuevo = new Comprador();
//		anuncianteNuevo.setNombre("as");
//		anuncianteNuevo.setIdUsuario("12345");
//		anuncianteNuevo.setEdad(24);
//		anuncianteNuevo.setDinero(240000);
//		subasta.getListaCompradores().add(anuncianteNuevo);

//		try {
//			Persistencia.guardarEstudiantes(main.getListaEstudiantes());
//			Persistencia.guardarProgramas(main.getListaProgramas());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	// ______________________________________________________________

	public void elimarAnuncio(Anuncio anuncio) throws IOException {

		subasta.eliminarAnuncio(anuncio);

	}

	// -----------------------------------------------------------------------------------------------------

}
