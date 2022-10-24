package co.edu.uniquindio.subasta.controller;

import java.io.IOException;

import co.edu.uniquindio.subasta.model.Comprador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class GestionAnunciosController {
	
	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    @FXML
    private Button btnPujar;

    @FXML
    private TableColumn colCategoria;

    @FXML
    private TableColumn colFechaFin;

    @FXML
    private TableColumn colFechaInicio;

    @FXML
    private TableColumn colNombreAnunciante;

    @FXML
    private TableColumn colNombreProducto;

    @FXML
    private TableColumn colPrecio;

    @FXML
    private TableView<?> tblDatos;

    @FXML
    private Button btnAtras;
    
    @FXML
    void btnPujarEvent(ActionEvent event) {

    	try {

    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/TransaccionPujaCompra.fxml"));
			Parent root = loader.load();

			TransaccionPujaCompraController controlador = loader.getController();
			controlador.init(usuario);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			
			stage.setScene(scene);
			stage.show();
			stage.setTitle("Login Anunciante");
			Stage myStage = (Stage) this.btnPujar.getScene().getWindow();
			myStage.close();

		} catch (IOException ex) {

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
    }

    @FXML
    void btnMostrarVentanaPrincipal(ActionEvent event) {

    	try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/MenuComprador.fxml"));
			Parent root = loader.load();
	
			MenuCompradorController controlador = loader.getController();
			controlador.init(usuario);
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

    
    //  ____________________________________________________________________
    /*
     * Metodo que inicializa datos de la ventana anterior
     */
  //Atributo global del comprador que inicio sesión
    private Comprador usuario;
	public void init(Comprador usuario) {
		this.usuario = usuario;
		
	}


	
}
