package co.edu.uniquindio.subasta.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.subasta.model.Anunciante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TransaccionCompradorLogController{
	/*
	 * Instancia del singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();
	//___________________________________________________________________________
	
	
	/*
	 * Atributos
	 */
    @FXML
    private Button btnLogin;
    
    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtIndentificacion;

    @FXML
    private TextField txtNombre;
    //___________________________________________________________________________
    
    
    /*
     * Metodo que permite volver a la pantalla principal
     */
    @FXML
    void volver(ActionEvent event) {
    	try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/MenuPrincipal.fxml"));
			Parent root = loader.load();
	
			PrincipalController controlador = loader.getController();
	
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			
			stage.setScene(scene);
			stage.show();
			Stage myStage = (Stage) this.btnVolver.getScene().getWindow();
			myStage.close();
    	}catch(IOException ex) {
    		ex.printStackTrace();
    	}
    }
    //___________________________________________________________________________
    
    
    /*
     * Metodo que permite hacer el login de los compradores
     */
    @FXML
    void login(ActionEvent event) {

		String nombre = this.txtNombre.getText();
		String idUsuario = this.txtIndentificacion.getText();
		
		// Aqui entra al metodo de la lina de codigo numero 87
		if(singleton.inicioSesionComprador(nombre, idUsuario) == true){
			try {
		
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/MenuComprador.fxml"));
				Parent root = loader.load();
		
				MenuCompradorController controlador = loader.getController();
		
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				
				stage.setScene(scene);
				stage.show();
				Stage myStage = (Stage) this.btnVolver.getScene().getWindow();
				myStage.close();
			} catch (IOException ex) {

				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			}

			// Si el metodo retorno false entonces mandara una alerta
		}else{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Los datos no coinciden, vuelva a intentarlo.");
			alert.showAndWait();
		}	
    }
	//________________________________________________________
}

