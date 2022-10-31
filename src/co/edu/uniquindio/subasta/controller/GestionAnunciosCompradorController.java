package co.edu.uniquindio.subasta.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class GestionAnunciosCompradorController {

    @FXML
    private Button btnAtras;

    @FXML
    private TableColumn<?, ?> colCategoria;

    @FXML
    private TableColumn<?, ?> colEstado;

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private TableColumn<?, ?> colIdAnuncio;

    @FXML
    private TableColumn<?, ?> colNombreComprador;

    @FXML
    private TableColumn<?, ?> colNombreProducto;

    @FXML
    private TableColumn<?, ?> colPrecio;

    @FXML
    private TableView<?> tblDatos;

    @FXML
    void btnMostrarVentanaPrincipal(ActionEvent event) {
    	try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/MenuComprador.fxml"));
			Parent root = loader.load();
	
			MenuCompradorController controlador = loader.getController();
			controlador.init();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Menú Comprador");
			stage.setScene(scene);
			stage.show();
			
			Stage myStage = (Stage) this.btnAtras.getScene().getWindow();
			myStage.close();
    	}catch(IOException ex) {
    		ex.printStackTrace();
    	}
    }

}
