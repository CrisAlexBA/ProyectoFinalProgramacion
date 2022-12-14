package co.edu.uniquindio.subasta.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import co.edu.uniquindio.subasta.exceptions.AnuncioException;
import co.edu.uniquindio.subasta.exceptions.FechaException;
import co.edu.uniquindio.subasta.model.Anunciante;
import co.edu.uniquindio.subasta.model.Anuncio;
import co.edu.uniquindio.subasta.model.TipoArticulo;
import com.sun.jndi.toolkit.url.Uri;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import javax.swing.*;

public class CrudAnuncioController implements InterfaceCrudAnuncio {

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();

	private Anunciante usuario = singleton.getAnunciante();

	private LocalDate fechaActual = LocalDate.now();
	// _________________________________________________________________

	// Atributoss
	@FXML
	private Button btnAgregarFoto;

	@FXML
	private Button btnVolver;

	@FXML
    private ImageView imagen;
	
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

	String foto;

	// _____________________________________________________________________________________________

	/*
	 * M??todo que permite agregar una imagen dentro del sistema
	 */

	@FXML
    void AdjuntarFoto(ActionEvent event) {

    	Stage primaryStage = null;

    	try {
    		FileChooser fileChooser = new FileChooser();
    		fileChooser.setTitle("Buscar Imagen");
    		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Archivos Imagen", "*.png", "*.jpg"));
    		File selectedFile = fileChooser.showOpenDialog(primaryStage);
			foto = selectedFile.getPath();

    		if (selectedFile != null) {
    			FileInputStream input = new FileInputStream(selectedFile);
    			Image image = new Image(input);
    			imagen.setImage(image);
    		}
    	} catch (Exception e) {
    		System.out.println("Debes manejar el error: " + e.getMessage());
    	}

    }


	// ______________________________________________________________________________________________________

	/*
	 * M??todo que permite crea el objeto anuncio y lo envia a otro m??todo para ser
	 * creado
	 */
	
	
	
	@FXML
	void CrearAnuncio(ActionEvent event) {
		// Extrae los datos de los txt para crear un objeto anuncio.
		String nombre = txtNombreArticulo.getText();
		TipoArticulo tipoArticulo = tipoProducto.getSelectionModel().getSelectedItem();
		String fechaInicio = fechaInicial.getValue()+"";
		String fechaFin = fechaFinal.getValue()+"";
		String descripcion = txtDescripcion.getText();
		int precio = Integer.parseInt(txtPrecioArticulo.getText());

		Anuncio anuncio = new Anuncio(nombre, usuario.getNombre(), "venta", descripcion, singleton.EnviarCodigo(),
				tipoArticulo, fechaInicio, fechaFin, precio, foto);
		try {
			CrearAnuncioNuevo(anuncio);

			if (usuario.getListaAnuncios() == null) {
				ArrayList<Anuncio> listaAnuncios = new ArrayList<>();
				listaAnuncios.add(anuncio);
				usuario.setListaAnuncios(listaAnuncios);
			} else {
				ArrayList<Anuncio> listaAnuncios = usuario.getListaAnuncios();
				listaAnuncios.add(anuncio);
				usuario.setListaAnuncios(listaAnuncios);

			}
			singleton.actualizarAnunciante(usuario.getNombre(), usuario.getIdUsuario(), usuario.getEdad(),
					usuario.getDinero(), usuario.getCantAnuncios() + 1, usuario.getListaAnuncios());
		} catch (IOException | AnuncioException | FechaException e) {
			e.printStackTrace();
		}
	}


// ___________________________________________________________________________________

	/*
	 * M??todo que permite crear junto con la clase principal un nuevo anuncio
	 */

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void CrearAnuncioNuevo(Anuncio anuncioNuevo) throws IOException, AnuncioException, FechaException {
		LocalDate fechaInicioInt = fechaInicial.getValue();
		LocalDate fechaFinInt= fechaFinal.getValue();
		
		if (anuncioNuevo.getNombreArticulo().equals("") || anuncioNuevo.getDescripcion().equals("")
				|| anuncioNuevo.getFechaPublicacion().equals("") || anuncioNuevo.getFechaCumlinacion().equals("")
				|| anuncioNuevo.getTipoArticulo() == null || anuncioNuevo.getFoto() == null) {
//    		
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Primero agregue informacion");
			alert.showAndWait();
			// log y exception propias
			singleton.guardaRegistroLog(
					"El usuario: " + usuario.getNombre() + " intento agregar un anuncio sin datos, AnuncioExcepcion", 2,
					"CrudAnuncio");
			throw new AnuncioException("Falta informaci??n para agregar el anuncio.");
		} else if (fechaInicioInt.isAfter(fechaFinInt)) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Las fechas del anuncio no cuadran");
			alert.showAndWait();
			singleton.guardaRegistroLog("El usuario: " + usuario.getNombre()
					+ " intento agregar un anuncio con fecha erronea, FechaException", 2, "CrudAnuncio");

			throw new FechaException("Trato de ingresar una serie de fechas erroneas");
		}
		else if (fechaActual.isAfter(fechaInicioInt)) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText(null);
			alert.setTitle("Fecha Exception");
			alert.setContentText("La fecha de iniciaci??n no puede ser antes que la fecha actual");
			alert.showAndWait();
			throw new FechaException("Trato de ingresar la fecha de iniciacion antes de la fecha actual");
		}

		else {
			// Agrega el nuevo anuncio al objeto subasta
			singleton.agregarAnuncio(anuncioNuevo);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Anuncio Agregado con exito.");
			alert.showAndWait();
			// Log
			limpiarCampos();
			singleton.guardaRegistroLog("El usuario: " + usuario.getNombre() + " Creo un anuncio", 1, "CrudAnuncio");
		}

	}

	private void limpiarCampos() {
		txtNombreArticulo.setText("");
		txtDescripcion.setText("");
		txtPrecioArticulo.setText("");
		fechaInicial.setValue(null);
		fechaFinal.setValue(null);
		imagen.setImage(null);
		tipoProducto.setValue(null);
	}

	// _______________________________________________________________________________________

	/*
	 * M??todo que permite devolverse y abre la pesta??a de men?? anunciante
	 */
	@FXML
	void volver(ActionEvent event) {
		singleton.guardarResourceXML();
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/co/edu/uniquindio/subasta/view/MenuAnunciante.fxml"));
			Parent root = loader.load();

			MenuAnuncianteController controlador = loader.getController();
			controlador.init();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Men?? Anunciante");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/Logo Subasta.png")));
			stage.setScene(scene);
			stage.show();
			Stage myStage = (Stage) this.btnVolver.getScene().getWindow();
			myStage.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// ____________________________________________________________________________________

	/*
	 * M??todo que permite inicializar los datos de la ventana
	 */
	@SuppressWarnings("static-access")
	public void init() {
		TipoArticulo tipoArticulo = null;

		ArrayList<TipoArticulo> lista = new ArrayList<>();

		// Carga los datos del Enum
		tipoProducto.getItems().addAll(tipoArticulo.DEPORTES, tipoArticulo.HOGAR, tipoArticulo.TECNOLOGIA,
				tipoArticulo.VEHICULOS, tipoArticulo.VIENESRAIZ);
		fechaInicial.setEditable(false);
		fechaFinal.setEditable(false);
	}

	// ____________________________________________________________________
	/*
	 * No se sabe que hace el m??todo, pero no se puede eliminar porque genera error
	 * al cargar la pesta??a
	 */

	@FXML
	void ValidarText(KeyEvent event) {

	}

}