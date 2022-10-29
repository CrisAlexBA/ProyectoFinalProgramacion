package co.edu.uniquindio.subasta.controller;

import javafx.scene.control.TextField;
import java.io.IOException;

import co.edu.uniquindio.subasta.model.Anunciante;
import co.edu.uniquindio.subasta.model.Comprador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuAnuncianteController{

	/*
	 * Atributos
	 */
	ModelFactoryController singleton = ModelFactoryController.getInstance();
    
    Stage primaryStage;

    //__________________________________________________________
    
    @FXML
    private Button btnAtras;
    
    @FXML
    private Button btnGuardar;
    
    @FXML
    private Button btnCrearAnuncio;

    @FXML
    private Button btnCrearProducto;

    @FXML
    private Button btnVerListaVentas;
    
    @FXML
    private Button btnAgregarDin;
    
    @FXML
    private TextField txtAgregar;

    //_____________________________________________________________________
    @FXML
    void agregarDin(ActionEvent event) {
    	usuario.setDinero(Float.parseFloat(txtAgregar.getText()));
    	singleton.guardaRegistroLog("El usuario:" + usuario.getNombre() + " agrego dinero", 1, "MenuAnunciante");
    	
    }

	//____________________________________________________________________ 

    
    /*
     * Metodo que permite crear un anuncio
     */
    @FXML
    void btnCrearAnuncioEvent(ActionEvent event) {
    	try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/ViewCrudAnuncio.fxml"));
			Parent root = loader.load();
	
			CrudAnuncioController controlador = loader.getController();
			controlador.init(usuario);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			
			stage.setScene(scene);
			stage.setOnCloseRequest(e -> controlador.volver(event));
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			Stage myStage = (Stage) this.btnCrearAnuncio.getScene().getWindow();
			myStage.close();
    	}catch(IOException ex) {
    		ex.printStackTrace();
    	}
    	
		
    }
    //____________________________________________________________________ 

    
    /*
     * Metodo que permite crear un producto con sus diferentes propiedades
     */
//    @FXML
//    void btnCrearProductoEvent(ActionEvent event) {
//    	try {
//	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/ViewCrudArticulo.fxml"));
//			Parent root = loader.load();
//	
//			CrudArticuloController controlador = loader.getController();
//			controlador.init(usuario);
//			Scene scene = new Scene(root);
//			Stage stage = new Stage();
//			
//			stage.setScene(scene);
//			stage.setOnCloseRequest(e -> controlador.volver(event));
//			stage.show();
//			Stage myStage = (Stage) this.btnCrearProducto.getScene().getWindow();
//			myStage.close();
//    	}catch(IOException ex) {
//    		ex.printStackTrace();
//    	}
//    }
    //____________________________________________________________________ 
    

    /*
     * Metodo que permite devolverse a la ventana principal
     */
    @FXML
    void btnMostrarVentanaPrincipal(ActionEvent event) throws Exception {
  
    	try {
    		
    		singleton.guardaRegistroLog("El usuario: "+usuario.getNombre()+" cerró sesión", 1, "MenuAnunciante");
    		singleton.guardarResourceXML();
			singleton.guardarResourceBinario();
			System.out.println("Se guardaron los datos");
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/subasta/view/MenuPrincipal.fxml"));
			Parent root = loader.load();
	
			PrincipalController controlador = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			
			stage.setScene(scene);
			stage.show();
			
			Stage myStage = (Stage) this.btnAtras.getScene().getWindow();
			myStage.close();
    	}catch(IOException ex) {
    		ex.printStackTrace();
    	}
    }
    //____________________________________________________________________ 

    
    /*
     * Metodo que permite ver la lista de ventas
     */
    @FXML
    void btnVerListaVentasEvent(ActionEvent event) {
    	singleton.guardaRegistroLog("El usuario: " + usuario.getNombre()+ " abrió la ventana lista de ventas", 1, "MenuAnunciante");
    }
    //____________________________________________________________________ 
    
    private Anunciante usuario;
    
	public void init(Anunciante usuario) {
		this.usuario = usuario;
		
	}

    //____________________________________________________________________ 


}
