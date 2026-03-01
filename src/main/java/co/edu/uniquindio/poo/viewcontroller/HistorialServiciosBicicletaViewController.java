package co.edu.uniquindio.poo.viewcontroller;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.controller.AlertaController;
import co.edu.uniquindio.poo.model.Servicio;
import co.edu.uniquindio.poo.paths.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class HistorialServiciosBicicletaViewController {

    @FXML
    private TextField fxIngresarNoSerial;

    @FXML
    private Button fxBotonConsultar;

    @FXML
    private Button fxBotonAtras;

    @FXML
    void cambioHaciaInicio(ActionEvent event) {
        App.app.setScene(Paths.INICIO);
    }

    @FXML
    void cambioHaciaTabla() {
        App.app.setScene(Paths.TABLA_HISTORIAL_BICI);
    }

    @FXML
    void consultarServicios(ActionEvent event) {
        String codigoBici = fxIngresarNoSerial.getText() == null ? "" : fxIngresarNoSerial.getText().trim();

        if (codigoBici.isEmpty()) {
            AlertaController.showError("El código/serial de la bicicleta es requerido");
            return;
        }

        try {
            Optional<?> biciOpt = App.taller.buscarBicicleta(codigoBici);
            if (biciOpt.isEmpty()) {
                AlertaController.showError("La bicicleta no existe en el taller");
                return;
            }

            List<Servicio> historial = App.taller.consultarHistorialBici(codigoBici);
            if (historial != null && !historial.isEmpty()) {
                App.resultadosHistorialBici = new LinkedList<>(historial);
                cambioHaciaTabla();
            } else {
                AlertaController.showInformation("No se encontraron servicios/historial para la bicicleta indicada");
            }
        } catch (Exception ex) {
            AlertaController.showError("Error al consultar el historial: " + ex.getMessage());
        }
    }

}
