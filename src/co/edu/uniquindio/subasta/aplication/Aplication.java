package co.edu.uniquindio.subasta.aplication;

import java.io.IOException;

import co.edu.uniquindio.subasta.controller.ModelFactoryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Aplication extends Application{
	//Inicialización del singleton
	static ModelFactoryController singleton = ModelFactoryController.getInstance();
	
	
	@Override
	public void start(Stage primaryStage) {

		try {
			//Cargo la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplication.class.getResource("/co/edu/uniquindio/subasta/view/MenuPrincipal.fxml"));

			// Cargo la ventana
			Pane ventana = (Pane) loader.load();

			// Cargo el scene
			Scene scene = new Scene(ventana);

			// Seteo la scene y la muestro
			primaryStage.setScene(scene);
			primaryStage.setTitle("Proyecto Subastas del Quindio");
			//primaryStage.initStyle(StageStyle.UNDECORATED); //---> linea que permite ocultar la barra superior de la ventana
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		//Saca la copia de seguridad
		singleton.copiaSeguridad();
		launch(args);
		

	}
}