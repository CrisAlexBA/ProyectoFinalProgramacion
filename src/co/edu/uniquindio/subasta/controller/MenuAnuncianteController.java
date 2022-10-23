package co.edu.uniquindio.subasta.controller;

import java.awt.Label;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.subasta.aplication.Aplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MenuAnuncianteController{

	/*
	 * Atributos
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();
    
    Stage primaryStage;

    //__________________________________________________________
    
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
    private TextField txtAgregarDin;

    //_____________________________________________________________________
    @FXML
    void agregarDin(ActionEvent event) {
    	
    	
    	singleton.guardaRegistroLog("El usuario: (nombre usuario) agrego dinero", 1, "MenuAnunciante");
    }

	//____________________________________________________________________ 

    
    /*
     * Metodo que permite crear un anuncio
     */
    @FXML
    void btnCrearAnuncioEvent(ActionEvent event) {
    	try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/ViewCrudAnuncio.fxml"));
			Parent root = loader.load();
	
			CrudAnuncioController controlador = loader.getController();
	
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			
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
     * Metodo que permite crear un producto con sus diferentes propiedades
     */
    @FXML
    void btnCrearProductoEvent(ActionEvent event) {
    	try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/ViewCrudArticulo.fxml"));
			Parent root = loader.load();
	
			CrudArticuloController controlador = loader.getController();
	
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			
			stage.setScene(scene);
			stage.setOnCloseRequest(e -> controlador.volver(event));
			stage.show();
			Stage myStage = (Stage) this.btnCrearProducto.getScene().getWindow();
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
    		
    		
    		singleton.guardarXML();
			singleton.guardarBinario();
			System.out.println("Se guardaron");
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/MenuPrincipal.fxml"));
			Parent root = loader.load();
	
			PrincipalController controlador = loader.getController();
	
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			
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
     * Metodo que permite ver la lista de ventas
     */
    @FXML
    void btnVerListaVentasEvent(ActionEvent event) {

    }
    //____________________________________________________________________ 
    
    
    /*
     * Metodo que permite guardar todo lo uqe haya pasado en al app mediante una rchivo xml y binario
     */
    @FXML
    void btnGuardarEvent(ActionEvent event) throws Exception {

    	singleton.guardarXML();
    	singleton.guardarBinario();
    	
    	System.out.println("Se guardaron");
    }
    //____________________________________________________________________ 


}
