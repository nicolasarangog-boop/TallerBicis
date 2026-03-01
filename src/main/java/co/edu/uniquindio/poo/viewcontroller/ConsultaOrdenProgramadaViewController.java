package co.edu.uniquindio.poo.viewcontroller;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.controller.AlertaController;
import co.edu.uniquindio.poo.model.Servicio;
import co.edu.uniquindio.poo.paths.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

public class ConsultaOrdenProgramadaViewController {

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
    void cambioHaciaTabla(){
        App.app.setScene(Paths.TABLA_CONSULTAR_ORDEN);
    }

    @FXML
    void consultarServicios(ActionEvent event) {
        String fechaTexto = fxIngresarNoSerial.getText() == null ? "" : fxIngresarNoSerial.getText().trim();

        if (fechaTexto.isEmpty()) {
            AlertaController.showError("La fecha es requerida");
            return;
        }

        LocalDate fechaIngresada;
        try {
            // Se asume formato ISO: yyyy-MM-dd
            fechaIngresada = LocalDate.parse(fechaTexto);
        } catch (DateTimeParseException e) {
            AlertaController.showError("Formato de fecha inválido. Use el formato yyyy-MM-dd");
            return;
        }

        LocalDate hoy = LocalDate.now();
        // No puede ser mayor a un mes (en el futuro) ni mayor a un año hacia atrás (más antigua que 1 año)
        if (fechaIngresada.isAfter(hoy.plusMonths(1))) {
            AlertaController.showError("La fecha no puede ser mayor a un mes a partir de hoy");
            return;
        }
        if (fechaIngresada.isBefore(hoy.minusYears(1))) {
            AlertaController.showError("La fecha no puede ser anterior a un año desde hoy");
            return;
        }

        try {
            List<Servicio> resultados = App.taller.consultarOrdenProgramada(fechaTexto);
            if (resultados != null && !resultados.isEmpty()) {
                App.resultadosOrdenProgramada = new LinkedList<>(resultados);
                cambioHaciaTabla();
            } else {
                AlertaController.showInformation("No se encontraron órdenes programadas para la fecha indicada");
            }
        } catch (Exception ex) {
            AlertaController.showError("Error al consultar las órdenes: " + ex.getMessage());
        }
    }

    
}
