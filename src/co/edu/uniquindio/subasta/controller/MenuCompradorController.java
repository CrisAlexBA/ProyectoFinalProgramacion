package co.edu.uniquindio.subasta.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuCompradorController {

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();
	// _________________________________________________________________
	/*
	 * Atributos
	 */
    @FXML
    private Button btnAtras;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnVerAnuncios;

    @FXML
    private Button btnVerPujas;
    //____________________________________________________________________ 
    
    
    /*
     * Metodo que permite guardar lo que haya pasado en la app en un archivo xml y binario
     */
    @FXML
    void btnGuardarEvent(ActionEvent event) {

    }
    //____________________________________________________________________ 
    

    /*
     * Metodo que permite mostrar la ventana principal
     */
    @FXML
    void btnMostrarVentanaPrincipal(ActionEvent event) {

    	try {
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
     * Metodo que permite ver los anuncios
     */
    @FXML
    void btnVerAnunciosEvent(ActionEvent event) {

    }
    //____________________________________________________________________ 

    
    /*
     * Metodo que permite ver las pujas
     */
    @FXML
    void btnVerPujasEvent(ActionEvent event) {

    }
    //____________________________________________________________________ 

}
