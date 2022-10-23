package co.edu.uniquindio.subasta.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CrudArticuloController {


	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();
	// _________________________________________________________________
    @FXML
    private Button btnVolver;

    @FXML
    private Button creaqrArticulo;

    @FXML
    private TextField idArticulo;

    @FXML
    private TextField nombreArticulo;

    @FXML
    private ComboBox<?> tipoProducto;

    @FXML
    void CrearArticulo(ActionEvent event) {
    	singleton.guardaRegistroLog("El usuario: (nombre usuario) intento agregar un articulo sin datos, ArticuloException", 2, "CrudArticulo");
    	singleton.guardaRegistroLog("El usuario: (nombre usuario) creó un nuevo articulo", 1, "CrudArticulo");
    }

    @FXML
    void ValidarText(KeyEvent event) {

    }

    @FXML
    void volver(ActionEvent event) {
	    try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/MenuAnunciante.fxml"));
			Parent root = loader.load();
	
			MenuAnuncianteController controlador = loader.getController();
	
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

}
