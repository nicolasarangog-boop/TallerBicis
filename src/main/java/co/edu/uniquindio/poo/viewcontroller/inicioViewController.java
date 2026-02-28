package co.edu.uniquindio.poo.viewcontroller;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.paths.Paths;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;

public class inicioViewController {

    @FXML
    private Circle fxBotonRegistroBici;

    @FXML
    private Circle fxBotonRegistroPersona;

    @FXML
    private Circle fxBotonRegistroServicio;

    @FXML
    private Circle fxBotonHistorialServicios;

    @FXML
    private Circle fxBotonConsultaServiciosDia;

    @FXML
    void cambioHaciaRegistrarCliente(MouseEvent event) {
        App.app.setScene(Paths.REGISTRO_CLIENTE);
    }

    @FXML
    void cambioHaciaRegistrarMecanico(MouseEvent event) {
        App.app.setScene(Paths.REGISTRO_MECANICO);
    }

    @FXML
    void cambioHaciaRegistrarBicicleta(MouseEvent event) {
        App.app.setScene(Paths.REGISTRO_BICICLETA);
    }

    @FXML
    void cambioHaciaHistorialServiciosBici(MouseEvent event) {
        App.app.setScene(Paths.HISTORIAL_SERVICIOS_BICI);
    }

    @FXML
    void cambioHaciaCrearRepuesto(MouseEvent event) {
        App.app.setScene(Paths.CREAR_REPUESTO);
    }

    @FXML
    void cambioHaciaRegistrarServicio(MouseEvent event){
        App.app.setScene(Paths.CREAR_SERVICIO);
    }

    @FXML
    void cambioHaciaConsultarOrden(MouseEvent event){
        App.app.setScene(Paths.CREAR_REPUESTO);
    }
}
