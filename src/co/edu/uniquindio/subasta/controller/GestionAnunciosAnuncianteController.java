package co.edu.uniquindio.subasta.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import co.edu.uniquindio.subasta.model.Anunciante;
import co.edu.uniquindio.subasta.model.Anuncio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class GestionAnunciosAnuncianteController {

	/*
	 * Instanciamos el singleton
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();

	private Anunciante usuario = singleton.getAnunciante();

	private ArrayList<Anuncio> listaAnuncios = usuario.getListaAnuncios();

	private ObservableList<Anuncio> anuncios = FXCollections.observableArrayList();

	//__________________________________________________________________________________
	
	@FXML
	private Button btnAtras;

	@FXML
	private Button btnBorrar;

	@FXML
	private Button btnEditar;

	@FXML
	private TableColumn<?, ?> colCategoria;

	@FXML
	private TableColumn<?, ?> colDescripcion;

	@FXML
	private TableColumn<Anuncio, String> colEstado;

	@FXML
	private TableColumn<Anuncio, LocalDate> colFechaFin;

	@FXML
	private TableColumn<Anuncio, LocalDate> colFechaInicio;

	@FXML
	private TableColumn<Anuncio, String> colFoto;

	@FXML
	private TableColumn<Anuncio, String> colIdAnuncio;

	@FXML
	private TableColumn<Anuncio, String> colNombreAnunciante;

	@FXML
	private TableColumn<Anuncio, String> colNombreProducto;

	@FXML
	private TableColumn<Anuncio, Integer> colPrecio;

	@FXML
	private TableView<Anuncio> tblDatos;
	
	//_________________________________________________________________________________________
	/*
	 * Método que permite borrar un elemento de la lista del anunciante y del programa
	 */

	@FXML
	void btnBorrarEvent(ActionEvent event) throws IOException {
		Anuncio anuncio = this.tblDatos.getSelectionModel().getSelectedItem();
		//Confirmación de seleccion de anuncio
		if (anuncio == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Seleccione primero un articulo para eliminar.");
			alert.showAndWait();
		} else {
			//metodos para remover de la tabla
			this.anuncios.remove(anuncio);
			this.tblDatos.refresh();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Info");
			alert.setContentText("Se ha eliminado correctamente");
			alert.showAndWait();
		}
		
		//Elimina los datos de la lista de anuncios del programa
		ArrayList<Anuncio> nuevaLista = usuario.getListaAnuncios();

		singleton.elimarAnuncio(anuncio);

		String codigoAnuncio = anuncio.getIdAnuncio();
		for (int i = 0; i < nuevaLista.size(); i++) {
			if (nuevaLista.get(i).getIdAnuncio().equals(codigoAnuncio)) {
				nuevaLista.remove(i);

				System.out.println("Se elimino el anuncio.");
				break;
			}

			//Actualiza la lista de anuncios del anunciante con base al estado del anuncio
			if (anuncio.getEstado().equals("venta")) {
				usuario.setListaAnuncios(nuevaLista);
				singleton.actualizarAnunciante(usuario.getNombre(), usuario.getIdUsuario(), usuario.getEdad(),
						usuario.getDinero(), usuario.getCantAnuncios() - 1, usuario.getListaAnuncios());
				// Trae el "nuevo" usuario
				usuario = singleton.getAnunciante();
			} else {
				usuario.setListaAnuncios(nuevaLista);
				singleton.actualizarAnunciante(usuario.getNombre(), usuario.getIdUsuario(), usuario.getEdad(),
						usuario.getDinero(), usuario.getCantAnuncios(), usuario.getListaAnuncios());
				// Trae el "nuevo" usuario
				usuario = singleton.getAnunciante();
			}
		}


		singleton.guardarResourceXML();
	}

	//_______________________________________________________________________________________________
	
	/*
	 * Método que permite editar un anuncio
	 */
	@FXML
	void btnEditarEvent(ActionEvent event) {

	}
	
	//__________________________________________________________________________________________

	/*
	 * Método que retorna a la ventana principal
	 */
	
	@FXML
	void btnMostrarVentanaPrincipal(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/co/edu/uniquindio/subasta/view/MenuAnunciante.fxml"));
			Parent root = loader.load();

			MenuAnuncianteController controlador = loader.getController();
			controlador.init();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Menú Anunciante");
			stage.setScene(scene);
			stage.show();
			Stage myStage = (Stage) this.btnAtras.getScene().getWindow();
			myStage.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	//____________________________________________________________________________________________
	
	/*
	 * Metodo que inicializa datos de la ventana anterior
	 */
	@SuppressWarnings("unchecked")
	public void init() {
		colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
		colNombreAnunciante.setCellValueFactory(new PropertyValueFactory<>("nombreAnunciante"));
		colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
		colIdAnuncio.setCellValueFactory(new PropertyValueFactory<>("idAnuncio"));
		colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		colCategoria.setCellValueFactory(new PropertyValueFactory<>("tipoArticulo"));
		colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
		colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaCumlinacion"));
		colPrecio.setCellValueFactory(new PropertyValueFactory<>("valor"));
		colFoto.setCellValueFactory(new PropertyValueFactory<>("foto"));

		for (int i = 0; i < listaAnuncios.size(); i++) {
			Anuncio anuncio = new Anuncio(listaAnuncios.get(i).getNombreArticulo(),
					listaAnuncios.get(i).getNombreAnunciante(), listaAnuncios.get(i).getEstado(),
					listaAnuncios.get(i).getDescripcion(), listaAnuncios.get(i).getIdAnuncio(),
					listaAnuncios.get(i).getTipoArticulo(), listaAnuncios.get(i).getFechaPublicacion(),
					listaAnuncios.get(i).getFechaCumlinacion(), listaAnuncios.get(i).getValor(),
					listaAnuncios.get(i).getFoto());

			anuncios.add(anuncio);

		}
		tblDatos.setItems(anuncios);
		tblDatos.refresh();
	}

	//_______________________________________________________________________________________
}
