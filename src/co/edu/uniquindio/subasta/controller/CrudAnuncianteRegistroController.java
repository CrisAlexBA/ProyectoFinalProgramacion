package co.edu.uniquindio.subasta.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.subasta.aplication.Aplication;
import co.edu.uniquindio.subasta.exceptions.AnuncianteException;
import co.edu.uniquindio.subasta.exceptions.EdadException;
import co.edu.uniquindio.subasta.model.Anunciante;
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
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CrudAnuncianteRegistroController {

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

	// _________________________________________________________________

	@FXML
	private Button btnRegistro;

	@FXML
	private Button btnVolver;

	@FXML
	private TextField txtIdUsuario;

	@FXML
	private TextField txtEdad;

	@FXML
	private TextField txtNombre;
	// ____________________________________________________________________

	/*
	 * Metodo que permite hacer el registro de un anunciante
	 */
	@FXML
	void registro(ActionEvent event) throws IOException, EdadException, AnuncianteException {

		String nombre = this.txtNombre.getText();
		String idUsuario = this.txtIdUsuario.getText();
		int edad = Integer.parseInt(txtEdad.getText());

		Anunciante anunciante = new Anunciante(nombre, idUsuario, edad);

		// Llama el metodo de la linea de codigo numero 66
		crearAnunciante(anunciante);
	}
	// ____________________________________________________________________

	/*
	 * Metodo que permite volver a la pantalla principal
	 */
	@FXML
	public void volver(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/co/edu/uniquindio/subasta/view/MenuPrincipal.fxml"));
			Parent root = loader.load();

			PrincipalController controlador = loader.getController();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Proyecto Subastas del Quindio");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/Logo Subasta.png")));
			stage.setScene(scene);
			stage.show();
			Stage myStage = (Stage) this.btnVolver.getScene().getWindow();
			myStage.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	// ____________________________________________________________________

	/*
	 * Metodo que se encargar de ir al de confirmar que no hayan espacios vacios, o
	 * de que el anunciante sea mayor de edad para luego, agregar ese anunciante a
	 * la lista
	 */
	private void crearAnunciante(Anunciante anunciante) throws EdadException, AnuncianteException {

		// Aqui verifica que ni el campo de nombre, id usuario o la edad esten vacios,
		// de estarlo manda una alerta
		if (anunciante.getNombre().equals("") && anunciante.getIdUsuario().equals("") || anunciante.getEdad() == 0) {
			modelFactoryController.guardaRegistroLog("Se intento registrar un usuario erroneamente", 2,
					"RegistroAnunciante");
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Primero agregue informacion");
			alert.showAndWait();
			throw new AnuncianteException("Falta informaci??n para agregar el anunciante.");
			// Si la edad es menor a 18, mandara una alerta y propaga la excepcion de edad
		} else if (anunciante.getEdad() < 18) {
			modelFactoryController.guardaRegistroLog("Se intento registrar un usuario menor de edad", 2,
					"RegistroAnunciante");
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Debes ser mayor de edad para ingresar.");
			alert.showAndWait();

			throw new EdadException("La edad es menor a los 18 a???os.");

		} else {

			// Este metodo no se usa, X, el proposito era comparar que no exista ya la misma
			// identificacion
			if (anunciante.getIdUsuario().equals(txtIdUsuario)) {
				modelFactoryController.guardaRegistroLog("Se intento registrar un usuario existente", 2,
						"RegistroAnunciante");
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
					modelFactoryController.agregarAnunciante(anunciante);
					modelFactoryController.guardaRegistroLog("Se registro el usuario: " + this.txtNombre.getText(), 1,
							"RegistroAnunciante");
					modelFactoryController.setAnunciante(anunciante);
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
					ex.printStackTrace();
				}
			}

		}

	}
	// ____________________________________________________________________

	/*
	 * M??todo que permite inicializar los datos de la ventana
	 */
	public void init() {
	}
	// ____________________________________________________________________

}