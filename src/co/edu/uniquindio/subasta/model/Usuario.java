package co.edu.uniquindio.subasta.model;


public class Usuario {

	// Atributos
	private String nombre;
	private String idUsuario;
	private int edad;
	private double dinero;
//____________________________________________________________________ 
	
	// Metodo constructor
	
	//Constructor 1
	public Usuario(String nombre, String idUsuario, int edad, double dinero) {
		super();
		this.nombre = nombre;
		this.idUsuario = idUsuario;
		this.edad = edad;
		this.dinero = dinero;
	}
	
	//Constructor 2
	public Usuario() {
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
	
	public String getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public double getDinero() {
		return dinero;
	}
	
	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	
//____________________________________________________________________ 

	
	
}
