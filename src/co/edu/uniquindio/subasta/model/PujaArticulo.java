package co.edu.uniquindio.subasta.model;

import co.edu.uniquindio.subasta.controller.ModelFactoryController;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("serial")
public class PujaArticulo implements Serializable {


	ModelFactoryController singleton = ModelFactoryController.getInstance();
	ArrayList <Anunciante> listaAnunciantes = singleton.traerListaAnunciantes();
	ArrayList <Comprador> listaCompradores= singleton.getSubasta().getListaCompradores();
	// Metodos Constructor
	public PujaArticulo() {
		super();
	}

	public void entrgarArticulo (Anuncio anuncio) throws IOException {

		LocalDate fechaActual = LocalDate.now();
		String fecha = fechaActual.toString();

		//Si ya se termino la subasta del articulo, se procede a entregar el producto
		if (fecha.equals(anuncio.getFechaCumlinacion())){

			Comprador comprador = anuncio.getPujante();
			anuncio.setEstado("Vendido");
			comprador.setAununcio(anuncio);


			//Devolver Dinero a los que perdieron la puja;
			actualizarDinero(comprador);

			//se cobra por el valor de la puja
			anuncio.getPujante().setDinero(anuncio.getPujante().getDinero()-anuncio.getValor());


			//se elimina el anuncio de las pujas disponibles
			singleton.getSubasta().getListaAnuncios().remove(anuncio);

			singleton.actualizarComprador(comprador.getNombre(), comprador.getIdUsuario(), comprador.getEdad(), comprador.getDinero(),
					comprador.getCantPujas(), comprador.getListaCompras());


			//se agrega el dinero al anunciante al ultimo valor de la puja del anuncio
			for (int i = 0; i < listaAnunciantes.size(); i++){
				if (anuncio.getNombreAnunciante().equals(listaAnunciantes.get(i).getNombre()))
					listaAnunciantes.get(i).setDinero(listaAnunciantes.get(i).getDinero() + anuncio.getValor());
		}

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Anuncio Vendido");
			alert.setContentText("El articulo: " + anuncio.getNombreArticulo() + " fue vendido exitosamente a el usuario: " +
					anuncio.getPujante().getNombre() + " por un valor de: " + anuncio.getValor());
			alert.showAndWait();
		}
	}


	// Metodo que actualiza el valor pujado por los compradores que no ganaron la puja
	void actualizarDinero(Comprador c) {
		try {
				for (int i = 0; i < listaCompradores.size(); i++){
					if (!Objects.equals(c.getNombre(), listaCompradores.get(i).getNombre()))
						listaCompradores.get(i).setDinero(listaCompradores.get(i).getDinero()+c.getDinero());
				}
		} catch (NumberFormatException e) {

		}

	}






}