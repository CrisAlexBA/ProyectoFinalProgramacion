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

public class GestionAnunciosAnuncianteController {

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnEditar;

    @FXML
    private TableColumn<?, ?> colCategoria;

    @FXML
    private TableColumn<?, ?> colFechaFin;

    @FXML
    private TableColumn<?, ?> colFechaInicio;

    @FXML
    private TableColumn<?, ?> colNombreAnunciante;

    @FXML
    private TableColumn<?, ?> colNombreProducto;

    @FXML
    private TableColumn<?, ?> colPrecio;

    @FXML
    private TableView<?> tblDatos;

    @FXML
    void btnBorrarEvent(ActionEvent event) {

    }

    @FXML
    void btnEditarEvent(ActionEvent event) {

    }

    @FXML
    void btnMostrarVentanaPrincipal(ActionEvent event) {
    	try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/MenuAnunciante.fxml"));
			Parent root = loader.load();
	
			MenuAnuncianteController controlador = loader.getController();
			controlador.init();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Menú Anunciante");
			stage.setScene(scene);
			stage.show();
			Stage myStage = (Stage) this.btnAtras.getScene().getWindow();
			myStage.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
    }

}
