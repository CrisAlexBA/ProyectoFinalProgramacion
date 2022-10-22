package co.edu.uniquindio.subasta.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.subasta.exceptions.AnuncianteException;
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

public class TransaccionAnuncianteLogController{

	/*
	 * Instancia del singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();
	// _____________________________________________________________________
	
	
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
	// _____________________________________________________________________
	
	
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
    // _____________________________________________________________________
 
    
    /*
     * Metodo que permite hacer el login de los anunciantes
     */
	@FXML
	void login(ActionEvent event) throws AnuncianteException {

		String nombre = this.txtNombre.getText();
		String idUsuario = this.txtIndentificacion.getText();
		
		
		if(singleton.inicioSesionAnunciante(nombre, idUsuario) == true){
			
			try {
				singleton.guardaRegistroLog("Usuario: "+nombre+" inicio sesion", 1, "LoginAnunciante");
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/MenuAnunciante.fxml"));
				Parent root = loader.load();

				MenuAnuncianteController controlador = loader.getController();

				Scene scene = new Scene(root);
				Stage stage = new Stage();
				
				stage.setScene(scene);
				stage.show();
				stage.setOnCloseRequest(e -> controlador.btnMostrarVentanaPrincipal(event));
				Stage myStage = (Stage) this.btnLogin.getScene().getWindow();
				myStage.close();

			} catch (IOException ex) {

				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			}
		}else{
			singleton.guardaRegistroLog("Se intento iniciar sesion sin cuenta", 2, "LoginAnunciante");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Los datos no coinciden, vuelva a intentarlo.");
			alert.showAndWait();
			throw new AnuncianteException("Anunciante no existe");
		}
		
	}
	//___________________________________________________________________________
}

