package co.edu.uniquindio.subasta.model;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.subasta.exceptions.ArticuloException;
import co.edu.uniquindio.subasta.persistencia.Persistencia;


public class SubastaQuindio {

	// Atributos
	private String nombre;
	private ArrayList<Anunciante> listaAnunciantes = new ArrayList<Anunciante>();
	private ArrayList<Comprador> listaCompradores = new ArrayList<Comprador>();
	private ArrayList<Anuncio> listaAnuncios = new ArrayList<Anuncio>();
//____________________________________________________________________ 

	// Metodos constructor
	
	//Constructor 1
	public SubastaQuindio(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	//Constructor 2
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
		for(int i = 0; i < listaAnunciantes.size() && bandera == 0; i++) {
			if(listaAnunciantes.get(i).getIdUsuario().equals(anunciante.getIdUsuario())) {
				bandera = 1;
			}
		}
		if(bandera == 0){
			listaAnunciantes.add(anunciante);
			Persistencia.guardarAnunciante(listaAnunciantes);
			System.out.println("Se agregO un nuevo anunciante .");
		}else{
			System.out.println("Este usuario ya existe");
		}
		
	}

//____________________________________________________________________ 

	/*
	 * Método que permite agregar un comprador a la lista y mandarlo a persistencia
	 */
	public void agregarComprador(Comprador comprador) throws IOException {
		
		int bandera = 0;
		for(int i = 0; i < listaCompradores.size() && bandera == 0; i++) {
			if(listaCompradores.get(i).getIdUsuario().equals(comprador.getIdUsuario())) {
				bandera = 1;
			}
		}
		if(bandera == 0){
			listaCompradores.add(comprador);
			Persistencia.guardarComprador(listaCompradores);
			System.out.println("Se agrego un nuevo comprador.");
		}else{
			System.out.println("Este usuario ya existe");
		}
		
	}
	
//____________________________________________________________________ 

	/*
	 * Método que permite agregar un anuncio a la lista y mandarlo a persistencia
	 */
    public void agregarAnuncio(Anuncio anuncioNuevo) throws IOException {
		
		int bandera = 0;
		for(int i = 0; i < listaAnuncios.size() && bandera == 0; i++) {
			if(listaAnuncios.get(i).getNombreArticulo().equals(anuncioNuevo.getNombreArticulo())) {
				bandera = 1;
			}
		}
		if(bandera == 0){
			listaAnuncios.add(anuncioNuevo);
			Persistencia.guardarAnuncio(listaAnuncios);
			System.out.println("Se agrego un nuevo anuncio.");
		}else{
			System.out.println("Este usuario ya existe");
		}
	}
    
 //____________________________________________________________________ 

    /*
	 * Método que permite actualizar un anunciante de la lista y mandarlo a persistencia
	 */
    public boolean actualizarAnunciante(String nombre, String idUsuario, int edad, double dinero, int canAnuncios, ArrayList<Anuncio> listaAnuncios) throws IOException {
		Anunciante anuncianteNu = new Anunciante(nombre, idUsuario, edad, dinero, canAnuncios, listaAnuncios);
		if (idUsuario != null) {
            for (int i = 0 ; i < listaAnunciantes.size() ; i++) {
                if (listaAnunciantes.get(i).getIdUsuario().equals(idUsuario)) {
                	listaAnunciantes.set(i, anuncianteNu);
        			Persistencia.guardarAnunciante(listaAnunciantes);
                	return true;
                }
            }
        }
		return false;
	}
    
 //____________________________________________________________________ 

    /*
	 * Método que permite actualizar un comprador de la lista y mandarlo a persistencia
	 */
    public boolean actualizarComprador(String nombre, String idUsuario, int edad, double dinero, int canPujas, ArrayList<Anuncio> listaCompras) throws IOException {
		Comprador compradorNu = new Comprador(nombre, idUsuario, edad, dinero, canPujas, listaCompras);
		if (idUsuario != null) {
            for (int i = 0 ; i < listaCompradores.size() ; i++) {
                if (listaCompradores.get(i).getIdUsuario().equals(idUsuario)) {
                	listaCompradores.set(i, compradorNu);
        			Persistencia.guardarComprador(listaCompradores);
                	return true;
                }
            }
        }
		return false;
	}

//____________________________________________________________________ 

    /*
     * Método que permite buscar un comprador en especifico y retornarlo.
     */
    public Comprador leerComprador(String id) {
        if (id != null) {
            for (Comprador c :listaCompradores) {
                if (c.getIdUsuario().equals(id))
                    return c;
            }
        }
        return null;
    }
//____________________________________________________________________ 
    
    
    
	
}