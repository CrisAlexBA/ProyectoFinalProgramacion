package co.edu.uniquindio.subasta.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import co.edu.uniquindio.subasta.exceptions.DineroInsuficienteException;
import co.edu.uniquindio.subasta.exceptions.PujaMenorException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;

public class TransaccionPujaCompraController {

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();

	private Comprador usuario = singleton.getComprador();
	private Anuncio anuncio;

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
    private ImageView imagen;
	
	@FXML
	private Label lblNomAnunciante;

	@FXML
	private Label lblPrecio;


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
			controlador.init(usuario);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Menú Comprador");
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
	 * Método que permite realizar una puja por el anuncio que esta visualizando
	 */
	@FXML
	void btnPujasEvent(ActionEvent event) throws PujaMenorException, DineroInsuficienteException {

		double dineroAPujar = Double.parseDouble(txtMontoPujar.getText());
		double valorProducto = anuncio.getValor();

		if(dineroAPujar < valorProducto) {
			//Notificación por puja menor
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificación");
			alert.setContentText("Puja por debajo del precio");
			alert.showAndWait();
			singleton.guardaRegistroLog("El usuario: " + usuario.getNombre() + " intento hacer una puja menor al valor del producto, PujaMenorException", 2, "TransaccionPuja");
			throw new PujaMenorException("La puja realizada es menor al precio del anuncio");
			
		}else if(usuario.getDinero() < dineroAPujar) {
			//Notificación por dinero insuficiente
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Notificación");
			alert.setContentText("Dinero insuficiente.");
			alert.showAndWait();
			
			singleton.guardaRegistroLog("El usuario: " + usuario.getNombre() + " intento hacer una puja sin el dinero suficiente, DineroInsuficienteException",2, "TransaccionPuja");
			throw new DineroInsuficienteException("El comprador no tiene dinero suficiente para hacer la puja");
		}else {

			//Lógica de la puja
			
			anuncio.setPujante(usuario);
			anuncio.setValor((int) dineroAPujar);
			this.lblPersonaPujante.setText(usuario.getNombre());
			this.lblPrecio.setText(dineroAPujar+"");

			//se elimina el usuario anterior
			anuncio.getPujante().delete(anuncio);

			//se agrega a la la lista de compras del usuario
			usuario.setAununcio(anuncio);



			//No sé si esta lógica va acá y si esta bien creada
			/*
			 * Trata de comparar si el usuario es el ultimo pujante y si no lo es
			 * le devuelve el dinero que pujo.
			 */
			
			double dineroEnCaja = 0;

			if(usuario.getNombre() == anuncio.getPujante().getNombre()) {
				dineroEnCaja = usuario.getDinero() - dineroAPujar;
				usuario.setDinero(dineroEnCaja);
				try {
					singleton.actualizarComprador(usuario.getNombre(),usuario.getIdUsuario(), usuario.getEdad(), usuario.getDinero(), 0, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
			singleton.guardaRegistroLog("El usuario: " + usuario.getNombre() + " hizo una puja por un producto", 1, "TransaccionPuja");
		
			
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
		//Trae el anuncio seleccionado y envia los datos a los labels
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
		FileInputStream input;
		try {

			input = new FileInputStream(anuncio.getFoto());
			Image imagenInternaImage = new Image(input);
			imagen.setImage(imagenInternaImage);

		} catch (FileNotFoundException e) {
			System.out.println("Hay un error");
		}

		//imprime el nombre del usuario con la puja actual
		try {
				this.lblPersonaPujante.setText(anuncio.getPujante().getNombre());
		} catch (NullPointerException e){

		}
	}

	// ____________________________________________________________________
}
