package co.edu.uniquindio.subasta.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransaccionPujaCompraController {

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();
	
	//________________________________________________________________________________________
    @FXML
    private Button btnAtras;

    @FXML
    private Button btnPujar;

    @FXML
    private TextArea textDescripcion;

    @FXML
    private TextField txtMontoPujar;

    @FXML
    void btnMostrarVentanaPrincipal(ActionEvent event) {
    	try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/MenuComprador.fxml"));
			Parent root = loader.load();
	
			MenuCompradorController controlador = loader.getController();
	
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

    @FXML
    void btnPujasEvent(ActionEvent event) {
    	singleton.guardaRegistroLog("El usuario: (nombre usuario) intento hacer una puja menor a la indicada, PujaMenorException", 2, "TransaccionPuja");
    	singleton.guardaRegistroLog("El usuario: (nombre usuario) intento hacer una puja sin el dinero suficiente, DineroInsuficienteException", 2, "TransaccionPuja");
    	singleton.guardaRegistroLog("El usuario: (nombre usuario) intento hacer más pujas de las posibles, CantidadPujaException", 2, "TransaccionPuja");
    	singleton.guardaRegistroLog("El usuario: (nombre usuario) hizo una puja por un producto", 1, "TransaccionPuja");
    }

}
