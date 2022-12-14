package co.edu.uniquindio.subasta.controller;

import co.edu.uniquindio.subasta.model.Usuario;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.subasta.exceptions.AnuncianteException;
import co.edu.uniquindio.subasta.exceptions.DineroException;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;

public class MenuCompradorController {

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();

	private Comprador usuario = singleton.getComprador();
	// _________________________________________________________________
	/*
	 * Atributos
	 */

	@FXML
	private TextField txtAgregar;

	@FXML
	private Button btnAtras;

	@FXML
	private Button btnVerAnuncios;

	@FXML
	private Button btnVerPujas;

	@FXML
	private Button btnAgregarDin;

	@FXML
	private Label lblNombre;

	@FXML
	private Label lblDinero;


	// _____________________________________________________________________

	/*
	 * Método que permite modificar el usuario para que agregue dinero a su cuenta
	 */
	@FXML
	void agregarDin(ActionEvent event) throws DineroException {
		// usuario.setDinero(Float.parseFloat(txtAgregar.getText()));
		try {
			double dineroIngresar = Integer.parseInt(txtAgregar.getText());
			if (dineroIngresar >= 0) {

				usuario.setContadorPuja(usuario.getDinero()+dineroIngresar);
				// Actualiza el usuario para modificar el dinero
				singleton.actualizarComprador(usuario.getNombre(), usuario.getIdUsuario(), usuario.getEdad(),
						usuario.getDinero() + dineroIngresar, usuario.getCantPujas(),
						usuario.getListaCompras(), usuario.getContadorPuja());
				// Trae el "nuevo" usuario

				usuario = singleton.getComprador();

				// Envia los datos al lbl
				lblDinero.setText(usuario.getDinero() + "");
				txtAgregar.setText("");
				// Log
				singleton.guardaRegistroLog("El usuario:" + usuario.getNombre() + " agrego dinero", 1, "MenuComprador");
			} else {

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Notificacion");
				alert.setContentText("Debe ingresar dinero de manera positiva.");
				alert.showAndWait();
				txtAgregar.setText("");
			}
		} catch (NumberFormatException | IOException e) {

			txtAgregar.setText("");
			throw new DineroException("Trato de ingresar dinero erroneamente");
		}
	}
	// ____________________________________________________________________

	/*
	 * Metodo que permite mostrar la ventana principal
	 */
	@FXML
	void btnMostrarVentanaPrincipal(ActionEvent event) throws Exception {

		try {
			singleton.guardaRegistroLog("El usuario: " + usuario.getNombre() + " cerró sesión", 1, "MenuComprador");
			singleton.guardarResourceXML();
			singleton.guardarResourceBinario(); // ----> error a la hora de guardar comentar
			System.out.println("Se guardaron los datos");
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
			Stage myStage = (Stage) this.btnAtras.getScene().getWindow();
			myStage.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	// ____________________________________________________________________

	/*
	 * Metodo que permite abrir la ventana para ver los anuncios
	 */
	@FXML
	void btnVerAnunciosEvent(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/co/edu/uniquindio/subasta/view/GestionAnuncios.fxml"));
			Parent root = loader.load();

			GestionAnunciosController controlador = loader.getController();
			controlador.init();
			Scene scene = new Scene(root);
			Stage stage = new Stage();

			stage.setTitle("Lista de anuncios");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/Logo Subasta.png")));
			stage.setScene(scene);
			stage.show();
			Stage myStage = (Stage) this.btnAtras.getScene().getWindow();
			myStage.close();

		} catch (IOException ex) {

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}

	}
	// ____________________________________________________________________

	/*
	 * Metodo que permite abrir la ventana para ver las pujas
	 */
	@FXML
	void btnVerPujasEvent(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/co/edu/uniquindio/subasta/view/GestionAnunciosComprador.fxml"));
			Parent root = loader.load();

			GestionAnunciosCompradorController controlador = loader.getController();
			controlador.init(usuario);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Lista de anuncios comprados");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/Logo Subasta.png")));
			stage.setScene(scene);
			stage.show();
			Stage myStage = (Stage) this.btnAtras.getScene().getWindow();
			myStage.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		singleton.guardaRegistroLog("El usuario: " + usuario.getNombre() + " abrió la ventana de ver pujas", 1,
				"MenuComprador");
	}
	// ____________________________________________________________________

	/*
	 * Método que permite inicializar los datos de la ventana
	 */
	public void init(Comprador u) {
		singleton.cargarResourceXML();
		lblNombre.setText(u.getNombre().toUpperCase());
		lblDinero.setText(u.getDinero() + "");
		this.usuario = u;

	}

	public void init() {
		singleton.cargarResourceXML();
		lblNombre.setText(usuario.getNombre().toUpperCase());
		lblDinero.setText(usuario.getDinero() + "");
	}
	// ____________________________________________________________________

}
