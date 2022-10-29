package co.edu.uniquindio.subasta.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import co.edu.uniquindio.subasta.exceptions.AnuncioException;
import co.edu.uniquindio.subasta.model.Anunciante;
import co.edu.uniquindio.subasta.model.Anuncio;
import co.edu.uniquindio.subasta.model.TipoArticulo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CrudAnuncioController {

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();
	// _________________________________________________________________
	
    @FXML
    private Button btnAgregarFoto;

    @FXML
    private Button btnVolver;

    @FXML
    private Button crearAnuncio;

    @FXML
    private DatePicker fechaFinal;

    @FXML
    private DatePicker fechaInicial;

    @FXML
    private Label lblFoto;

    @FXML
    private ComboBox<TipoArticulo> tipoProducto;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtNombreArticulo;

    @FXML
    private TextField txtPrecioArticulo;

    @FXML
    void AdjuntarFoto(ActionEvent event) {

    }

    @FXML
    void CrearAnuncio(ActionEvent event) {
    	
    	String nombre = txtNombreArticulo.getText();
    	TipoArticulo tipoArticulo = tipoProducto.getSelectionModel().getSelectedItem();
    	LocalDate fechaInicio = fechaInicial.getValue();
    	LocalDate fechaFin = fechaFinal.getValue();
    	String descripcion = txtDescripcion.getText();
    	Image foto = null;
    	float precio = Float.parseFloat(txtPrecioArticulo.getText());
    	
    	Anuncio anuncio = new Anuncio(nombre, usuario.getNombre(), "venta", descripcion, usuario.getIdUsuario(), tipoArticulo, fechaInicio, fechaFin, precio, foto);
    	try {
			CrearAnuncioNuevo(anuncio);
		} catch (IOException | AnuncioException e) {
			e.printStackTrace();
		}
    	singleton.guardaRegistroLog("El usuario: "+usuario.getNombre()+" intento agregar más anuncios de los posibles, CantidadAnunciosException", 2, "CrudAnuncio");
    	singleton.guardaRegistroLog("El usuario: "+usuario.getNombre()+" intento agregar un anuncio con fecha erronea, FechaException", 2, "CrudAnuncio");
    	singleton.guardaRegistroLog("El usuario: "+usuario.getNombre()+" intento agregar un anuncio sin datos, AnuncioExcepcion", 2, "CrudAnuncio");
    	singleton.guardaRegistroLog("El usuario: "+usuario.getNombre()+" Creo un anuncio", 1, "CrudAnuncio");
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
			controlador.init(usuario);
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
    // ___________________________________________________________________________________
    
    private void CrearAnuncioNuevo(Anuncio anuncioNuevo) throws IOException, AnuncioException {

    	if(anuncioNuevo.getNombreArticulo().equals("") || anuncioNuevo.getDescripcion().equals("")){
//    		
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Primero agregue informacion");
			alert.showAndWait();
			singleton.guardaRegistroLog("El usuario: "+usuario.getNombre()+" intento agregar un anuncio sin datos, AnuncioExcepcion", 2, "CrudAnuncio");
			throw new AnuncioException("Falta información para agregar el anuncio.");
    	}else {
    		
    		singleton.agregarAnuncio(anuncioNuevo);
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Anuncio Agregado con exito.");
			alert.showAndWait();
			singleton.guardaRegistroLog("El usuario: "+usuario.getNombre()+" Creo un anuncio", 1, "CrudAnuncio");
    	}
    	if(anuncioNuevo != null) {
    		
    	}
    	

    }
    // ____________________________________________________________________________________
    
    
    
    
    private Anunciante usuario;
	@SuppressWarnings("static-access")
	public void init(Anunciante usuario) {
		this.usuario = usuario;
		TipoArticulo tipoArticulo = null;
		
		ArrayList<TipoArticulo> lista = new ArrayList<>();
//		Collections.addAll(lista, tipoArticulo.DEPORTES, tipoArticulo.HOGAR, tipoArticulo.TECNOLOGIA, tipoArticulo.VEHICULOS,
//				tipoArticulo.VIENESRAIZ);
//		
//		
//		tipoProducto.getItems().addAll(lista);
		
		tipoProducto.getItems().addAll(tipoArticulo.DEPORTES, tipoArticulo.HOGAR, tipoArticulo.TECNOLOGIA, tipoArticulo.VEHICULOS,
			tipoArticulo.VIENESRAIZ);
		fechaInicial.setEditable(false);
		fechaFinal.setEditable(false);
	}

}