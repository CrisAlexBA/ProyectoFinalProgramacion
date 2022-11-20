package co.edu.uniquindio.subasta.controller;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.subasta.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GestionAnunciosCompradorController {

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();

	private Comprador usuario = singleton.getComprador();



	private ObservableList<Anuncio> anuncios = FXCollections.observableArrayList();

	//_______________________________________________________________________________________
	
	@FXML
	private Button btnAtras;

	@FXML
	private TableColumn<Anuncio, TipoArticulo> colCategoria;

	@FXML
	private TableColumn<Anuncio, String> colEstado;

	@FXML
	private TableColumn<Anuncio, String> colFoto;

	@FXML
	private TableColumn<Anuncio, String> colIdAnuncio;

	@FXML
	private TableColumn<Anuncio, String> colNombreComprador;

	@FXML
	private TableColumn<Anuncio, String> colNombreProducto;

	@FXML
	private TableColumn<Anuncio, Integer> colPrecio;

	@FXML
	private TableView<Anuncio> tblDatos;

	//_______________________________________________________________________________________
	
	/*
	 * Método que retorna a la ventana principal
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

	//_______________________________________________________________________________________
	
	/*
	 * Metodo que inicializa datos de la ventana anterior
	 */
	@SuppressWarnings("unchecked")
	public void init(Comprador usuario) {

		this.usuario = usuario;
		ArrayList<Anuncio> listaAnuncios = usuario.getListaCompras();
		System.out.println(listaAnuncios);
		colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
		colNombreComprador.setCellValueFactory(new PropertyValueFactory<>("nombreAnunciante"));
		colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
		colIdAnuncio.setCellValueFactory(new PropertyValueFactory<>("idAnuncio"));
		colCategoria.setCellValueFactory(new PropertyValueFactory<>("tipoArticulo"));
		colPrecio.setCellValueFactory(new PropertyValueFactory<>("valor"));
		colFoto.setCellValueFactory(new PropertyValueFactory<>("foto"));

		for (int i = 0; i < listaAnuncios.size(); i++) {
			Anuncio anuncio = new Anuncio(listaAnuncios.get(i).getNombreArticulo(),
					listaAnuncios.get(i).getNombreAnunciante(), listaAnuncios.get(i).getEstado(),
					listaAnuncios.get(i).getIdAnuncio(), listaAnuncios.get(i).getTipoArticulo(),
					listaAnuncios.get(i).getValor(), listaAnuncios.get(i).getFoto());

			anuncios.add(anuncio);

		}
		tblDatos.setItems(anuncios);
		tblDatos.refresh();
	}

	//_______________________________________________________________________________________
}
