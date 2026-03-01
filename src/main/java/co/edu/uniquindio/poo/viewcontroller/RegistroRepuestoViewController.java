package co.edu.uniquindio.poo.viewcontroller;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.paths.Paths;
import co.edu.uniquindio.poo.controller.AlertaController;
import co.edu.uniquindio.poo.model.DetalleRepuesto;
import co.edu.uniquindio.poo.model.Repuesto;
import co.edu.uniquindio.poo.model.Taller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.LinkedList;
import java.util.List;

public class RegistroRepuestoViewController {

    @FXML
    private TextField fxNombreRp;

    @FXML
    private TextField fxCodigoRp;

    @FXML
    private TextField fxStockRp;

    @FXML
    private Button fxBtnCrearRp;

    @FXML
    private Button fxBtnAtras;

    @FXML
    void cambioHaciaInicio(ActionEvent event) {
        App.app.setScene(Paths.INICIO);
    }

    @FXML
    void crearRepuesto(ActionEvent event) {
        String nombre = fxNombreRp.getText();
        String codigo = fxCodigoRp.getText();
        String stock = fxStockRp.getText();

        // Validar que los campos no estén vacíos
        if (nombre.isBlank() || codigo.isBlank() || stock.isBlank()) {
            AlertaController.showError("Todos los campos son requeridos");
            return;
        }

        int stockDisponible;
        try {
            stockDisponible = Integer.parseInt(stock);
            if (stockDisponible < 0) {
                AlertaController.showError("El stock debe ser un número entero no negativo");
                return;
            }
        } catch (NumberFormatException e) {
            AlertaController.showError("El stock debe ser un número entero");
            return;
        }

        Taller taller = App.taller;

        try {
            List<DetalleRepuesto> detalles = new LinkedList<>();
            Repuesto nuevoRepuesto = new Repuesto(nombre, codigo, stockDisponible, taller, detalles);
            String resultado = taller.agregarRepuesto(nuevoRepuesto);

            if (resultado.contains("fue agregado exitosamente")) {
                AlertaController.showInformation("Repuesto registrado exitosamente");
            } else if (resultado.contains("ya existe en el taller")) {
                AlertaController.showError("El repuesto ya existe en el taller");
            } else {
                AlertaController.showError("Error al registrar el repuesto, verifica todos los campos");
            }
        } catch (Exception e) {
            AlertaController.showError("Error al registrar el repuesto: " + e.getMessage());
        }
    }

}
