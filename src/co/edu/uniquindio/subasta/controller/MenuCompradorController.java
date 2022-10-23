package co.edu.uniquindio.subasta.controller;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuCompradorController implements Initializable{

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();
	// _________________________________________________________________
	/*
	 * Atributos
	 */
	
    @FXML
    private TextField txtAgregar;
	
    @FXML
    private Button btnAtras;


    @FXML
    private Button btnVerAnuncios;
    
    @FXML
    private Button btnVerPujas;
    
    @FXML
    private Button btnAgregarDin;
    
    

    //_____________________________________________________________________
    @FXML
    void agregarDin(ActionEvent event) {
    	
    	singleton.guardaRegistroLog("El usuario: (nombre usuario) agrego dinero", 1, "MenuAnunciante");
    	
    }
    //____________________________________________________________________ 

   
    
    /*
     * Metodo que permite mostrar la ventana principal
     */
    @FXML
    void btnMostrarVentanaPrincipal(ActionEvent event) throws Exception {

    	try {
    		singleton.guardaRegistroLog("El usuario: (nombre usuario) cerró sesión", 1, "MenuAnunciante");
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
     * Metodo que permite ver los anuncios
     */
    @FXML
    void btnVerAnunciosEvent(ActionEvent event) {
    	try {

    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/GestionAnuncios.fxml"));
			Parent root = loader.load();

			GestionAnunciosController controlador = loader.getController();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			
			stage.setScene(scene);
			stage.show();
			stage.setTitle("Login Anunciante");
			Stage myStage = (Stage) this.btnAtras.getScene().getWindow();
			myStage.close();

		} catch (IOException ex) {

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
    	
  
    }
    //____________________________________________________________________ 

    
    /*
     * Metodo que permite ver las pujas
     */
    @FXML
    void btnVerPujasEvent(ActionEvent event) {

    }
    //____________________________________________________________________ 


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
