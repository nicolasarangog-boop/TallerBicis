package co.edu.uniquindio.poo.viewcontroller;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.model.Repuesto;
import co.edu.uniquindio.poo.paths.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.uniquindio.poo.controller.AlertaController;
import co.edu.uniquindio.poo.model.DetalleRepuesto;

public class TablaDetallesViewController {
    @FXML
    private ComboBox<Repuesto> fxRepuesto;

    @FXML
    private TextField fxCantidad;

    @FXML
    private TextField fxPrecioU;

    @FXML
    private Button fxAgregarDetalle;

    @FXML
    private Button fxBtnAtras;

    @FXML
    private TableView<DetalleRepuesto> fxTablaDetalleRepuestos;

    @FXML
    void cambioHaciaServicio(ActionEvent event){
        App.app.setScene(Paths.CREAR_SERVICIO);
    }

    @FXML
    void agregarATabla(ActionEvent event){
        // Validar selección de repuesto
        Repuesto repuestoSeleccionado = fxRepuesto.getValue();
        if (repuestoSeleccionado == null) {
            AlertaController.showError("Debe seleccionar un repuesto");
            return;
        }

        String cantidadStr = fxCantidad.getText();
        String precioUStr = fxPrecioU.getText();

        if (cantidadStr == null || cantidadStr.isBlank() || precioUStr == null || precioUStr.isBlank()) {
            AlertaController.showError("Cantidad y Precio Unitario son obligatorios");
            return;
        }

        int cantidadVal;
        double precioUVal;
        try {
            cantidadVal = Integer.parseInt(cantidadStr);
            precioUVal = Double.parseDouble(precioUStr);
            if (cantidadVal <= 0 || precioUVal <= 0) {
                AlertaController.showError("La cantidad y el precio unitario deben ser números positivos");
                return;
            }
        } catch (NumberFormatException e) {
            AlertaController.showError("Ingrese valores numéricos válidos para cantidad y precio unitario");
            return;
        }

        // Crear detalle, calcular subtotal y agregar a la tabla
        DetalleRepuesto detalle = new DetalleRepuesto(cantidadVal, precioUVal, 0.0, repuestoSeleccionado, null);
        detalle.setSubtotal(detalle.calcularSubtotal());

        if (fxTablaDetalleRepuestos.getItems() == null) {
            fxTablaDetalleRepuestos.setItems(javafx.collections.FXCollections.observableArrayList());
        }
        fxTablaDetalleRepuestos.getItems().add(detalle);

        // Limpiar campos
        fxCantidad.clear();
        fxPrecioU.clear();
        fxRepuesto.getSelectionModel().clearSelection();
    }


}
