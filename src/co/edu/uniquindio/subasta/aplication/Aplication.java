package co.edu.uniquindio.subasta.aplication;

import java.io.IOException;

import co.edu.uniquindio.subasta.Services.GuardarArchivos;
import co.edu.uniquindio.subasta.controller.ModelFactoryController;
import co.edu.uniquindio.subasta.model.Anuncio;
import co.edu.uniquindio.subasta.model.TipoArticulo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Aplication extends Application {

	/*
	 * COSAS POR HACER EN EL CÓDIGO:
	 * 
	 * - Poder seleccionar un anuncio y ser enviado a la interfaz de puja. LISTO
	 * 
	 * - Ver como agregar anuncios dentro de un usuario y luego ser
	 * leidos al cargar el programa. LISTO
	 * 
	 * - Añadir anuncios a la carga de datos y los
	 * método que esto conlleva. -----> LISTO FALTA EL METODO ACTUALIZAR
	 * 
	 * - Borrar, editar un anuncio. ----> no
	 * fundamental FALTA EL METODO EDITAR
	 * 
	 * - Implementar la interfaz para ver donde el usuario ha hecho
	 * pujas o mejor los articulos que ha comprado. ---> dificil  LISTO 
	 * 
	 * - Agregar las
	 * verificaciones de la creación de un anuncio. LISTO
	 * 
	 * - Ver como solucionar el
	 * problema del valor de algunos articulos cuando su precio es muy alto. LISTO A MEDIAS, VER QUE TAN RENTABLE ES LA SOLUCION
	 * 
	 * - Ver
	 * como implementar hilos en el proyecto. ----> A la hora de que se haga una
	 * copia de seguridad o se guarde el programa posiblemente 
	 * 
	 * - Hacer toda la
	 * logica para hacer una puja. 
	 * 
	 * - Añadir la logica para agregar una imagen en la
	 * creacion de un anuncio. LISTO
	 * 
	 * - Realizar toda la lógica para verificación(agregar
	 * dinero positivo, crear un anuncio, valores de puja, etc). LISTO
	 * 
	 * - Ver si es
	 * necesario añadir un id unico para cada articulo. Si es mejor para buscar y
	 * manipular los anuncios, LISTO
	 * 
	 */
	// Inicialización del singleton
	static ModelFactoryController singleton = ModelFactoryController.getInstance();

	@Override
	public void start(Stage primaryStage) {

		try {
			// Cargo la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplication.class.getResource("/co/edu/uniquindio/subasta/view/MenuPrincipal.fxml"));

			// Cargo la ventana
			Pane ventana = (Pane) loader.load();

			// Cargo el scene
			Scene scene = new Scene(ventana);

			// Seteo la scene y la muestro
			primaryStage.setScene(scene);
			primaryStage.setTitle("Proyecto Subastas del Quindio");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/Logo Subasta.png")));
			// primaryStage.initStyle(StageStyle.UNDECORATED); //---> linea que permite
			// ocultar la barra superior de la ventana
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		GuardarArchivos guardar = new GuardarArchivos();
		guardar.start();

		// Saca la copia de seguridad
		singleton.copiaSeguridad();
		launch(args);

	}
}