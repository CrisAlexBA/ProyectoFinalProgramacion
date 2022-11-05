package co.edu.uniquindio.subasta.controller;

import java.io.IOException;

import co.edu.uniquindio.subasta.exceptions.AnuncioException;
import co.edu.uniquindio.subasta.exceptions.FechaException;
import co.edu.uniquindio.subasta.model.Anuncio;

public interface InterfaceCrudAnuncio {

	void CrearAnuncioNuevo(Anuncio anuncioNuevo) throws IOException, AnuncioException, FechaException;

	void init();
}