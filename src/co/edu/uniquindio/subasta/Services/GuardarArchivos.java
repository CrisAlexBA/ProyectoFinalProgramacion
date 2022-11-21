package co.edu.uniquindio.subasta.Services;

import co.edu.uniquindio.subasta.controller.ModelFactoryController;

public class GuardarArchivos extends Thread{

    ModelFactoryController singleton = ModelFactoryController.getInstance(); // Instancia al Singleton
    // metodo constructor
    public GuardarArchivos() {

    }

    // Metodo para iniciar el hilo
    public void run() {

        // Recorre los archivos del programa
        for (int i = 0; i < 20; i++) {
            singleton.copiaSeguridad();

            try {
                sleep(60000); // Detiene el programa durante un minuto
            } catch (InterruptedException e) {
            }

        }
    }
}