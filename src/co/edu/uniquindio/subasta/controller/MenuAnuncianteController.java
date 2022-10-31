package co.edu.uniquindio.subasta.controller;

import javafx.scene.control.TextField;
import java.io.IOException;

import co.edu.uniquindio.subasta.model.Anunciante;
import co.edu.uniquindio.subasta.model.Comprador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuAnuncianteController{

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();
	
    private Anunciante usuario = singleton.getAnunciante();

    //__________________________________________________________
    
    //Atributos
    @FXML
    private Button btnAtras;
    
    @FXML
    private Button btnGuardar;
    
    @FXML
    private Button btnCrearAnuncio;

    @FXML
    private Button btnCrearProducto;

    @FXML
    private Button btnVerListaVentas;
    
    @FXML
    private Button btnAgregarDin;
    
    @FXML
    private TextField txtAgregar;
    
    @FXML
    private Label lblDinero;

    @FXML
    private Label lblNombre;

    //_____________________________________________________________________
    
    /*
     * Método que permite modificar el usuario para que agregue dinero a su cuenta
     */
    @FXML
    void agregarDin(ActionEvent event) {
    	try {
    		//Actualiza el usuario para modificar el dinero
			singleton.actualizarAnunciante(usuario.getNombre(), usuario.getIdUsuario(), usuario.getEdad(), usuario.getDinero() + Float.parseFloat(txtAgregar.getText()), usuario.getCantAnuncios(), usuario.getListaAnuncios());
			//Trae el "nuevo" usuario
			usuario = singleton.getAnunciante();
			//Envia los datos al lbl
			lblDinero.setText(usuario.getDinero()+"");
			txtAgregar.setText("");
			//Log
	    	singleton.guardaRegistroLog("El usuario:" + usuario.getNombre() + " agrego dinero", 1, "MenuAnunciante");
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
    }

	//____________________________________________________________________ 

    
    /*
     * Metodo que permite abrir la ventana para crear un anuncio
     */
    @FXML
    void btnCrearAnuncioEvent(ActionEvent event) {
    	try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/ViewCrudAnuncio.fxml"));
			Parent root = loader.load();
	
			CrudAnuncioController controlador = loader.getController();
			controlador.init();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Registro Anuncios");
			stage.setScene(scene);
			stage.setOnCloseRequest(e -> controlador.volver(event));
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			Stage myStage = (Stage) this.btnCrearAnuncio.getScene().getWindow();
			myStage.close();
    	}catch(IOException ex) {
    		ex.printStackTrace();
    	}
    	
		
    }
    //____________________________________________________________________ 

    /*
     * Metodo que permite devolverse a la ventana principal
     */
    @FXML
    void btnMostrarVentanaPrincipal(ActionEvent event) throws Exception {
  
    	try {
    		
    		singleton.guardaRegistroLog("El usuario: "+usuario.getNombre()+" cerró sesión", 1, "MenuAnunciante");
    		singleton.guardarResourceXML();
			singleton.guardarResourceBinario(); // -----> Error en la consola a la hora de guardar, comentar error
			System.out.println("Se guardaron los datos");
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/MenuPrincipal.fxml"));
			Parent root = loader.load();
	
			PrincipalController controlador = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Proyecto Subastas del Quindio");
			stage.setScene(scene);
			stage.show();
			
			Stage myStage = (Stage) this.btnAtras.getScene().getWindow();
			myStage.close();
    	}catch(IOException ex) {
    		ex.printStackTrace();
    	}
    }
    //____________________________________________________________________ 

    
    /*
     * Metodo que permite abrir la ventana para ver la lista de ventas
     */
    @FXML
    void btnVerListaVentasEvent(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/co/edu/uniquindio/subasta/view/GestionAnunciosAnunciante.fxml"));
			Parent root = loader.load();

			GestionAnunciosAnuncianteController controlador = loader.getController();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Lista de anuncios comprados");
			stage.setScene(scene);
			stage.show();
			Stage myStage = (Stage) this.btnAtras.getScene().getWindow();
			myStage.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    	singleton.guardaRegistroLog("El usuario: " + usuario.getNombre()+ " abrió la ventana lista de ventas", 1, "MenuAnunciante");
    }
    //____________________________________________________________________ 
    
	/*
	 * Método que permite inicializar los datos de la ventana
	 */
	public void init() {
		lblNombre.setText(usuario.getNombre().toUpperCase());
		lblDinero.setText(usuario.getDinero()+"");
	}

    //____________________________________________________________________ 


}
