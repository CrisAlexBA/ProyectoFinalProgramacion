package co.edu.uniquindio.subasta.controller;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.subasta.exceptions.AnuncianteException;
import co.edu.uniquindio.subasta.exceptions.ArticuloException;
import co.edu.uniquindio.subasta.exceptions.CompradorException;
import co.edu.uniquindio.subasta.model.Anunciante;
import co.edu.uniquindio.subasta.model.Articulo;
import co.edu.uniquindio.subasta.model.Comprador;
import co.edu.uniquindio.subasta.model.SubastaQuindio;
import co.edu.uniquindio.subasta.persistencia.Persistencia;

public class ModelFactoryController {

	SubastaQuindio subasta;
	// ------------------------------ Singleton
	// ------------------------------------------------
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

		// 1. inicializar datos y luego guardarlo en archivos
		// iniciarSalvarDatosPrueba();

		// 2. Cargar los datos de los archivos
		//cargarDatosDesdeArchivos();

		// 3. Guardar y Cargar el recurso serializable binario
		//guardarResourceBinario();
		//cargarResourceBinario();

		// 4. Guardar y Cargar el recurso serializable XML
		cargarResourceXML();
		//guardarResourceXML();
		// Siempre se debe verificar si la raiz del recurso es null
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
	//____________________________________________________________________ 

	
	/*
	 * Metodo que permite agregar un anunciante a la lista
	 */
	public void agregarAnunciante(Anunciante anunciante) throws IOException {
		subasta.agregarAnunciante(anunciante);
	}
	//____________________________________________________________________ 

	
	/*
	 * Metodo que permite agregar un comprador a la lista
	 */
	public void agregarComprador(Comprador comprador) throws IOException {
		
		subasta.agregarComprador(comprador);
		
	}
	//____________________________________________________________________ 

	
	/*
	 * Metodo que permite cargar los datos de la aplicacion recien se ejecuta
	 */
//	public void cargarDatos() throws IOException{
//		
//		subasta = (SubastaQuindio) Persistencia.cargarXML();
//	}
	//____________________________________________________________________ 

	
	/*
	 * Metodo que permite traer la lista de los anunciantes
	 */
	ArrayList<Anunciante> traerListaAnunciantes() {
		return subasta.getListaAnunciantes();
	}
	//____________________________________________________________________ 
	
	public void inicializarDatos(){
		
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
//	public boolean ingresarAnunciante(String nombre, String idUsuario, int edad) {
//
//		
//		return false;
//	}
	
	
	// El obejto anunciante (que contiene el nombre, la id y la edad) se va para el modelo

	// ______________________________________________________________
	
	
	/*
	 * Metodo que permite iniciar la sesion del comprador
	 */
	public boolean inicioSesionComprador(String nombre, String contrasenia) {
		try {
			return Persistencia.iniciarSesionComprador(nombre, contrasenia);
		} catch (IOException | CompradorException e) {
			e.printStackTrace();
		}
		return false;
	}
	//____________________________________________________________________ 
	
	
	/*
	 * Metodo que permite guardar lo que pase en la app en un archivo xml
	 */
//	public void guardarXML() throws IOException {
//
//		Persistencia.guardarXML(subasta);
//		
//	}
//	//____________________________________________________________________ 
//	
//	
//	/*
//	 * Metodo que permite guardar lo que pase en la app en un archivo binario
//	 */
//	public void guardarBinario() throws Exception {
//
//		Persistencia.guardarBinario(subasta);
//		
//	}
	//____________________________________________________________________ 

	
	/*
	 * Metodo que permite hacer el inicio de sesion de un anunciante
	 */
	public boolean inicioSesionAnunciante(String nombre, String idUsuario) throws AnuncianteException {
		try {
			return Persistencia.iniciarSesionAnunciante(nombre, idUsuario);
		} catch (IOException | AnuncianteException e) {
			e.printStackTrace();
		}
		return false;
	}
	//____________________________________________________________________ 
	
	public void guardaRegistroLog(String mensajeLog, int nivel, String accion)
	{
		Persistencia.guardaRegistroLog(mensajeLog, nivel, accion);
	}
	public Comprador traerComprador(String idUsuario) {
		
		try {
			return Persistencia.cargarComprador(idUsuario);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}
	//  _________________________________________________________________________
	
	//Nueva parte de la carga de datos
	
	
	private void cargarDatosDesdeArchivos() {
		
		subasta = new SubastaQuindio();
		
		try {

			Persistencia.cargarDatosArchivos(getSubasta());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarResourceBinario() {
		subasta = new SubastaQuindio();
		subasta = Persistencia.cargarRecursoSubastaQuindioBinario();
	}


	public void guardarResourceBinario() {
		
	    Persistencia.guardarRecursoSubastaQuindioBinario(subasta);
	}


	public void cargarResourceXML() {
		subasta = new SubastaQuindio();
		subasta = Persistencia.cargarRecursoSubastaQuindioXML();
	}


	public void guardarResourceXML() {
		
	    Persistencia.guardarRecursoSubastaQuindioXML(subasta);
	}
	public void agregarArticulo(Articulo articuloNuevo) throws ArticuloException, IOException {
		subasta.agregarArticulo(articuloNuevo);
		
	}

	
    
}
