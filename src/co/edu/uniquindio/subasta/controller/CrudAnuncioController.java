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

public class CrudAnuncioController implements InterfaceCrudAnuncio{

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();
	
    private Anunciante usuario = singleton.getAnunciante();
	// _________________________________________________________________
	 
    //Atributoss 
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

	// _____________________________________________________________________________________________
    
    /*
     * Método que permite agregar una imagen dentro del sistema
     */
    
    @FXML
    void AdjuntarFoto(ActionEvent event) {

    }
    
	// ______________________________________________________________________________________________________
    
	/*
	 * Método que permite crea el objeto anuncio y lo envia a otro método para ser creado
	 */
    @FXML
    void CrearAnuncio(ActionEvent event) {
    	//Extrae los datos de los txt para crear un objeto anuncio.
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
    	// Log de la creación de un anuncio //---> crear las verificaciones
    	singleton.guardaRegistroLog("El usuario: "+usuario.getNombre()+" intento agregar más anuncios de los posibles, CantidadAnunciosException", 2, "CrudAnuncio");
    	singleton.guardaRegistroLog("El usuario: "+usuario.getNombre()+" intento agregar un anuncio con fecha erronea, FechaException", 2, "CrudAnuncio");
    	singleton.guardaRegistroLog("El usuario: "+usuario.getNombre()+" intento agregar un anuncio sin datos, AnuncioExcepcion", 2, "CrudAnuncio");
    	singleton.guardaRegistroLog("El usuario: "+usuario.getNombre()+" Creo un anuncio", 1, "CrudAnuncio");
    }
    
// ___________________________________________________________________________________
    
    /*
     * Método que permite crear junto con la clase principal un nuevo anuncio
     */
    
    @Override
	public void CrearAnuncioNuevo(Anuncio anuncioNuevo) throws IOException, AnuncioException {

    	if(anuncioNuevo.getNombreArticulo().equals("") || anuncioNuevo.getDescripcion().equals("")){
//    		
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Primero agregue informacion");
			alert.showAndWait();
			// log y exception propias
			singleton.guardaRegistroLog("El usuario: "+usuario.getNombre()+" intento agregar un anuncio sin datos, AnuncioExcepcion", 2, "CrudAnuncio");
			throw new AnuncioException("Falta información para agregar el anuncio.");
    	}else {
    		//Agrega el nuevo anuncio al objeto subasta
    		singleton.agregarAnuncio(anuncioNuevo);
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Anuncio Agregado con exito.");
			alert.showAndWait();
			//Log
			singleton.guardaRegistroLog("El usuario: "+usuario.getNombre()+" Creo un anuncio", 1, "CrudAnuncio");
    	}
    	if(anuncioNuevo != null) {
    		
    	}
    	

    }
    
	// _______________________________________________________________________________________
    
	/*
	 * Método que permite devolverse y abre la pestaña de menú anunciante
	 */
    @FXML
    void volver(ActionEvent event) {
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
			Stage myStage = (Stage) this.btnVolver.getScene().getWindow();
			myStage.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
    }
    
    // ____________________________________________________________________________________

	/*
	 * Método que permite inicializar los datos de la ventana
	 */
	@SuppressWarnings("static-access")
	public void init() {
		TipoArticulo tipoArticulo = null;
		
		ArrayList<TipoArticulo> lista = new ArrayList<>();

		//Carga los datos del Enum
		tipoProducto.getItems().addAll(tipoArticulo.DEPORTES, tipoArticulo.HOGAR, tipoArticulo.TECNOLOGIA, tipoArticulo.VEHICULOS,
			tipoArticulo.VIENESRAIZ);
		fechaInicial.setEditable(false);
		fechaFinal.setEditable(false);
	}
	
	// ____________________________________________________________________
	/*
	 * No se sabe que hace el método, pero no se puede eliminar porque genera error al cargar la pestaña	
	 */
	
    @FXML
    void ValidarText(KeyEvent event) {

    }

}