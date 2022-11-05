package co.edu.uniquindio.subasta.controller;

import java.io.IOException;

import co.edu.uniquindio.subasta.model.Anunciante;
import co.edu.uniquindio.subasta.model.Anuncio;
import co.edu.uniquindio.subasta.model.Comprador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransaccionPujaCompraController {

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();

	private Comprador usuario = singleton.getComprador();
	private Anuncio anuncio;
	private Anunciante anunciante;
//________________________________________________________________________________________
	// Atributos
	@FXML
	private Button btnAtras;

	@FXML
	private Button btnPujar;

	@FXML
	private TextArea textDescripcion;

	@FXML
	private TextField txtMontoPujar;

	@FXML
	private Label lblPersonaPujante;

	@FXML
	private Label lblArticulo;

	@FXML
	private Label lblCategoria;

	@FXML
	private Label lblComprador;

	@FXML
	private Label lblFechaFin;

	@FXML
	private Label lblFechaInicio;

	@FXML
	private Label lblNomAnunciante;

	@FXML
	private Label lblPrecio;

	@FXML
	private Label lblDineroAnunciante;

//____________________________________________________________________ 
	/*
	 * Método que permite volver al menu del comprador
	 */
	@FXML
	void btnMostrarVentanaPrincipal(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/co/edu/uniquindio/subasta/view/MenuComprador.fxml"));
			Parent root = loader.load();

			MenuCompradorController controlador = loader.getController();
			controlador.init();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Menú Comprador");
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
	 * Método que permite realizar una puja por el anuncio que esta visualizando
	 */
	@FXML
	void btnPujasEvent(ActionEvent event) {

		double dineroAPujar = Double.parseDouble(txtMontoPujar.getText());

		if (dineroAPujar <= 0) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Dinero insuficiente.");
			alert.showAndWait();

			singleton.guardaRegistroLog(
					"El usuario: " + usuario.getNombre()
							+ " intento hacer una puja sin el dinero suficiente, DineroInsuficienteException",
					2, "TransaccionPuja");

		} else if (dineroAPujar < anuncio.getValor()) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Has comenzado a pujar.");
			alert.showAndWait();

//			double dineroDescontar = usuario.getDinero();

			double dineroActual = usuario.getDinero() - dineroAPujar;

			System.out.println(dineroActual);

			lblPersonaPujante.setText(usuario.getNombre());

			singleton.guardaRegistroLog(
					"El usuario: " + usuario.getNombre() + " Ha comenzado a pujar por el articulo seleccionado.", 2,
					"TransaccionPuja");

		} else if (dineroAPujar == anuncio.getValor()) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText("Te lo has comprado chaval.");
			alert.showAndWait();

			anuncio.setEstado("Comprado");

			System.out.println(anuncio.getEstado());

			singleton.guardaRegistroLog(
					"El usuario: " + usuario.getNombre() + " Compro directamente el articulo selesccionado.", 2,
					"TransaccionPuja");

		} else if (dineroAPujar > anuncio.getValor()) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificacion");
			alert.setContentText(
					"Excede el precio del producto, por favor ingrese el valor exacto ó,  un precio menor para pujar");
			alert.showAndWait();

			singleton.guardaRegistroLog(
					"El usuario: " + usuario.getNombre() + " Compro directamente el articulo selesccionado.", 2,
					"TransaccionPuja");

		}
//		singleton.guardaRegistroLog("El usuario: " + usuario.getNombre()
//				+ " intento hacer una puja menor a la indicada, PujaMenorException", 2, "TransaccionPuja");
//		singleton.guardaRegistroLog(
//				"El usuario: " + usuario.getNombre()
//						+ " intento hacer una puja sin el dinero suficiente, DineroInsuficienteException",
//				2, "TransaccionPuja");
//		singleton.guardaRegistroLog("El usuario: " + usuario.getNombre()
//				+ " intento hacer más pujas de las posibles, CantidadPujaException", 2, "TransaccionPuja");
//		singleton.guardaRegistroLog("El usuario: " + usuario.getNombre() + " hizo una puja por un producto", 1,
//				"TransaccionPuja");
	}
	// ____________________________________________________________________

	/*
	 * Método que permite inicializar los datos de la ventana
	 */
	public void init(Anuncio anuncio) {
		this.anuncio = anuncio;
		lblArticulo.setText(anuncio.getNombreArticulo());
		lblCategoria.setText(anuncio.getTipoArticulo() + "".toUpperCase());
		lblFechaFin.setText(anuncio.getFechaCumlinacion() + "");
		lblFechaInicio.setText(anuncio.getFechaPublicacion() + "");
		lblNomAnunciante.setText(anuncio.getNombreAnunciante().toUpperCase());
		lblPrecio.setText(anuncio.getValor() + "");
		lblPersonaPujante.setText("");
		textDescripcion.setEditable(false);
		textDescripcion.setText(anuncio.getDescripcion());
	}
	// ____________________________________________________________________

	/*
	 * Método que permite inicializar los datos de la ventana
	 */
	public void init(Anunciante anunciante) {
//		this.lblNomAnunciante = anunciante;
		lblDineroAnunciante.setText(anunciante.getDinero() + "".toUpperCase());
//		lblCategoria.setText(anuncio.getTipoArticulo()+"".toUpperCase());
//		lblFechaFin.setText(anuncio.getFechaCumlinacion()+"");
//		lblFechaInicio.setText(anuncio.getFechaPublicacion()+"");
//		lblNomAnunciante.setText(anuncio.getNombreAnunciante().toUpperCase());
//		lblPrecio.setText(anuncio.getValor()+"");
//		textDescripcion.setEditable(false);
//		textDescripcion.setText(anuncio.getDescripcion());
	}
	// ____________________________________________________________________
}
