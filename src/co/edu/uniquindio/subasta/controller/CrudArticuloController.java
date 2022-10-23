package co.edu.uniquindio.subasta.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import co.edu.uniquindio.subasta.exceptions.ArticuloException;
import co.edu.uniquindio.subasta.exceptions.CompradorException;
import co.edu.uniquindio.subasta.model.Anunciante;
import co.edu.uniquindio.subasta.model.Articulo;
import co.edu.uniquindio.subasta.model.TipoArticulo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CrudArticuloController implements Initializable{

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();
	// _______________________
    @FXML
    private Button btnVolver;

    @FXML
    private Button creaqrArticulo;

    @FXML
    private TextField txtNombreArticulo;

    @FXML
    private TextField txtiDArticulo;

    @FXML
    private ComboBox<TipoArticulo> tipoProducto;
    
    private Anunciante usuario;
    
    @FXML
    void CrearArticulo(ActionEvent event) throws ArticuloException, IOException {
    	
    	String nombreArticulo = this.txtNombreArticulo.getText();
    	String idArticulo = this.txtiDArticulo.getText();
    	TipoArticulo tipoArticulo = tipoProducto.getSelectionModel().getSelectedItem();
    	
    	Articulo articuloNuevo = new Articulo(usuario, nombreArticulo, idArticulo, tipoArticulo);
    	
    	crearNuevoArticulo(articuloNuevo);
    	
    
    	singleton.guardaRegistroLog("El usuario: (nombre usuario) intento agregar un articulo sin datos, ArticuloException", 2, "CrudArticulo");
    	singleton.guardaRegistroLog("El usuario: (nombre usuario) creÃ³ un nuevo articulo", 1, "CrudArticulo");
    }

    private void crearNuevoArticulo(Articulo articuloNuevo) throws ArticuloException, IOException {
    	
    	if(articuloNuevo.getNombreArticulo().equals("") || articuloNuevo.getIdArticulo().equals("") ||
    			articuloNuevo.getTipoArticulo().equals(null)){
    		
    		singleton.guardaRegistroLog("Se intento registrar un articulo erroneamente", 2, "RegistroArticulo");
    		
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Primero agregue informacion");
			alert.showAndWait();
			//Excepcion propia
			throw new ArticuloException("Falta informacion para agregar el articulo.");
    	}else{
    		singleton.agregarArticulo(articuloNuevo);
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Se registro un nuevo articulo con exito.");
			alert.showAndWait();
    	}
    	
    	
		
	}

	@FXML
    void ValidarText(KeyEvent event) {

    }
    
    @FXML
    void comboboxEvents(ActionEvent event) {

    	Object evt = event.getSource();
    	
    	if(evt.equals(tipoProducto)){
    		
    		
    	}
    	
    }
    

    @FXML
    void volver(ActionEvent event) {
	    try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/MenuAnunciante.fxml"));
			Parent root = loader.load();
	
			MenuAnuncianteController controlador = loader.getController();
	
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Proyecto Subastas del Quindio");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/SubastaQuindio.png")));
			stage.setScene(scene);
			stage.show();
			Stage myStage = (Stage) this.btnVolver.getScene().getWindow();
			myStage.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TipoArticulo tipoArticulo = null;
		
		ArrayList<TipoArticulo> lista = new ArrayList<>();
//		Collections.addAll(lista, tipoArticulo.DEPORTES, tipoArticulo.HOGAR, tipoArticulo.TECNOLOGIA, tipoArticulo.VEHICULOS,
//				tipoArticulo.VIENESRAIZ);
//		
//		
//		tipoProducto.getItems().addAll(lista);
		
		tipoProducto.getItems().addAll(tipoArticulo.DEPORTES, tipoArticulo.HOGAR, tipoArticulo.TECNOLOGIA, tipoArticulo.VEHICULOS,
			tipoArticulo.VIENESRAIZ);
	}

}