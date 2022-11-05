package co.edu.uniquindio.subasta.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.subasta.exceptions.CompradorException;
import co.edu.uniquindio.subasta.exceptions.EdadException;
import co.edu.uniquindio.subasta.model.Comprador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CrudCompradorRegistroController {

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

	// ________________________________________________________________________________

	// Atributos
	@FXML
	private Button btnRegistro;

	@FXML
	private Button btnVolver;

	@FXML
	private TextField txtEdad;

	@FXML
	private TextField txtIdUsuario;

	@FXML
	private TextField txtNombre;
	// _________________________________________________________________

	/*
	 * Metodo que permite volver a la pantalla principal
	 */
	@FXML
	void volver(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/co/edu/uniquindio/subasta/view/MenuPrincipal.fxml"));
			Parent root = loader.load();

			PrincipalController controlador = loader.getController();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Proyecto Subastas del Quindio");
			stage.setScene(scene);
			stage.show();
			Stage myStage = (Stage) this.btnVolver.getScene().getWindow();
			myStage.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	// _________________________________________________________________

	/*
	 * Metodo que permite hacer el registro de un comprador
	 */
	@FXML
	void registro(ActionEvent event) throws EdadException, CompradorException {

		String nombre = this.txtNombre.getText();
		String idUsuario = this.txtIdUsuario.getText();
		int edad = Integer.parseInt(txtEdad.getText());

		Comprador comprador = new Comprador(nombre, idUsuario, edad);

		// Aqui mandamos el objeto con el nombre, id y la edad hacia un metodo que nos
		// permite crear
		// el comprador
		crearComprador(comprador);

	}

	// ______________________________________________________________________
	/*
	 * Metodo que permite hacer las respectivas validaciones para posterior, crear
	 * un comprador
	 */
	private void crearComprador(Comprador comprador) throws EdadException, CompradorException {

		if (comprador.getNombre().equals("") && comprador.getIdUsuario().equals("") || comprador.getEdad() == 0) {
			modelFactoryController.guardaRegistroLog("Se intento registrar un usuario erroneamente", 2,
					"RegistroComprador");
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Primero agregue informacion");
			alert.showAndWait();
			// Excepcion propia
			throw new CompradorException("Falta información para agregar el comprador.");
		} else if (comprador.getEdad() < 18) {
			modelFactoryController.guardaRegistroLog("Se intento registrar un usuario menor de edad", 2,
					"RegistroComprador");
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Debes ser mayor de edad para ingresar.");
			alert.showAndWait();
			// Excepcion propia
			throw new EdadException("La edad es menor a los 18 a�os.");

		} else {

			// Este metodo no se usa, X, el proposito era comparar que no exista ya la misma
			// identificacion
			if (comprador.getIdUsuario().equals(txtIdUsuario)) {
				modelFactoryController.guardaRegistroLog("Se intento registrar un usuario existente", 2,
						"RegistroComprador");
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Notificacion");
				alert.setContentText("El usuario ya existe.");
				alert.showAndWait();

			} else {

				// Si ninguno de los campos esta vacio y la ID no esta repetida procede a crear
				// ese anunciante y
				// Abrirle la respectiva intefaz
				try {
					modelFactoryController.agregarComprador(comprador);
					modelFactoryController.guardaRegistroLog("Se registro el usuario: " + this.txtNombre.getText(), 1,
							"RegistroComprador");
					modelFactoryController.setComprador(comprador);
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/co/edu/uniquindio/subasta/view/MenuComprador.fxml"));
					Parent root = loader.load();

					MenuCompradorController controlador = loader.getController();

					Scene scene = new Scene(root);
					Stage stage = new Stage();
					stage.setTitle("Menú Comprador");
					stage.setScene(scene);
					controlador.init();
					stage.show();
					stage.setOnCloseRequest(e -> {
						try {
							controlador.btnMostrarVentanaPrincipal(null);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					});
					Stage myStage = (Stage) this.btnRegistro.getScene().getWindow();
					myStage.close();

				} catch (IOException ex) {

					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("Error");
					alert.setContentText(ex.getMessage());
					alert.showAndWait();
				}
			}

		}

	}

	// ____________________________________________________________________

	/*
	 * Método que permite inicializar los datos de la ventana
	 */
	public void init() {
	}
	// ____________________________________________________________________

}