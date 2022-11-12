package co.edu.uniquindio.subasta.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import co.edu.uniquindio.subasta.model.Anuncio;
import co.edu.uniquindio.subasta.model.TipoArticulo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionAnunciosController {

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();

	private ArrayList<Anuncio> listaAnuncios = singleton.traerListaAnuncios();

	// ____________________________________________________________________________________________

	// Atributos
	private ObservableList<Anuncio> anuncios = FXCollections.observableArrayList();

	@FXML
	private TableView<Anuncio> tblDatos;

	@FXML
	private TableColumn<Anuncio, TipoArticulo> colCategoria;

	@FXML
	private TableColumn<Anuncio, LocalDate> colFechaFin;

	@FXML
	private TableColumn<Anuncio, String> colDescripcion;

	@FXML
	private TableColumn<Anuncio, LocalDate> colFechaInicio;

	@FXML
	private TableColumn<Anuncio, String> colNombreAnunciante;

	@FXML
	private TableColumn<Anuncio, String> colNombreProducto;

	@FXML
	private TableColumn<Anuncio, Integer> colPrecio;

	@FXML
	private TableColumn<Anuncio, String> colIdArticulo;

	@FXML
	private Button btnAtras;

	@FXML
	private Button btnPujar;

	// ____________________________________________________________________________________

	/*
	 * Método que permite seleccionar un anuncio y abrir una pestaña de puja
	 */

	@FXML
	void btnPujarEvent(ActionEvent event) {

		try {
			Anuncio anuncioSelec = this.tblDatos.getSelectionModel().getSelectedItem();
			String codAnuncio = anuncioSelec.getIdAnuncio();
			Anuncio anuncioCompleto = singleton.traerAnuncio(codAnuncio);
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/co/edu/uniquindio/subasta/view/TransaccionPujaCompra.fxml"));
			Parent root = loader.load();

			TransaccionPujaCompraController controlador = loader.getController();
			controlador.init(anuncioCompleto);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Pestaña de pujar Anuncio");
			stage.setScene(scene);
			stage.show();
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
	// ______________________________________________________________________________________

	/*
	 * Método que permite volver al menú principal del comprador.
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
	 * Metodo que inicializa datos de la ventana anterior
	 */
	public void init() {
		
		//Codigo necesario para madandar los datos a la tabla view
		colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
		colNombreAnunciante.setCellValueFactory(new PropertyValueFactory<>("nombreAnunciante"));
		colCategoria.setCellValueFactory(new PropertyValueFactory<>("tipoArticulo"));
		colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
		colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaCumlinacion"));
		colPrecio.setCellValueFactory(new PropertyValueFactory<>("valor"));
		colIdArticulo.setCellValueFactory(new PropertyValueFactory<>("idAnuncio"));
		colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		//Se crean los objetos anuncio con los atributos necesarios para la tabla
		for (int i = 0; i < listaAnuncios.size(); i++) {
			if (listaAnuncios.get(i).getEstado().equals("venta")) {
				Anuncio anuncio = new Anuncio(listaAnuncios.get(i).getNombreArticulo(),
						listaAnuncios.get(i).getNombreAnunciante(), listaAnuncios.get(i).getTipoArticulo(),
						listaAnuncios.get(i).getFechaPublicacion(), listaAnuncios.get(i).getFechaCumlinacion(),
						listaAnuncios.get(i).getValor(), listaAnuncios.get(i).getIdAnuncio(),
						listaAnuncios.get(i).getDescripcion());

				anuncios.add(anuncio);
			}
		}
		tblDatos.setItems(anuncios);
		tblDatos.refresh();
	}

	//_______________________________________________________________________________________

}
