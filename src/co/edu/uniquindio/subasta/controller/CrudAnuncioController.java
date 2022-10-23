package co.edu.uniquindio.subasta.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CrudAnuncioController {


	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();
	// _________________________________________________________________
    @FXML
    private Button btnListaArticulos;

    @FXML
    private Button btnVolver;

    @FXML
    private ImageView campoImagen;

    @FXML
    private Button crearAnuncio;

    @FXML
    private TextArea descripsion;

    @FXML
    private DatePicker fechaFinal;

    @FXML
    private DatePicker fechaInicial;

    @FXML
    private TextField nombreArticulo;

    @FXML
    private TextField precioArticulo;

    @FXML
    private ComboBox<?> tipoProducto;

    @FXML
    void CargarMenuArticulos(ActionEvent event) {

    }

    @FXML
    void CrearAnuncio(ActionEvent event) {
    	singleton.guardaRegistroLog("El usuario: (nombre usuario) intento agregar más anuncios de los posibles, CantidadAnunciosException", 2, "CrudAnuncio");
    	singleton.guardaRegistroLog("El usuario: (nombre usuario) intento agregar un anuncio con fecha erronea, FechaException", 2, "CrudAnuncio");
    	singleton.guardaRegistroLog("El usuario: (nombre usuario) intento agregar un anuncio sin datos, AnuncioExcepcion", 2, "CrudAnuncio");
    	singleton.guardaRegistroLog("El usuario: (nombre usuario) Creo un anuncio", 1, "CrudAnuncio");
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