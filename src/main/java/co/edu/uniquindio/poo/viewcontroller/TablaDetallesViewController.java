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

        // Asegurar que la tabla use una lista observable enlazada al respaldo global
        if (fxTablaDetalleRepuestos.getItems() == null || fxTablaDetalleRepuestos.getItems().isEmpty()) {
            fxTablaDetalleRepuestos.setItems(javafx.collections.FXCollections.observableList(App.detallesServicioActual));
        }
        // Agregar a través de los items de la tabla para disparar notificaciones y mantener sincronía
        fxTablaDetalleRepuestos.getItems().add(detalle);

        // Limpiar campos
        fxCantidad.clear();
        fxPrecioU.clear();
        fxRepuesto.getSelectionModel().clearSelection();
    }

    @FXML
    public void initialize() {
        try {
            // Poblar ComboBox de repuestos con nombre legible
            if (fxRepuesto != null) {
                java.util.List<Repuesto> repuestos =
                        (App.taller != null && App.taller.getListaRepuestos() != null)
                                ? App.taller.getListaRepuestos()
                                : java.util.Collections.emptyList();

                fxRepuesto.setItems(javafx.collections.FXCollections.observableArrayList(repuestos));
                fxRepuesto.setPromptText(repuestos.isEmpty() ? "No hay repuestos" : "Seleccione un repuesto");

                // Renderizado amigable: mostrar el nombre del repuesto en la lista y en la celda seleccionada
                javafx.util.Callback<javafx.scene.control.ListView<Repuesto>, javafx.scene.control.ListCell<Repuesto>> cellFactory =
                        lv -> new javafx.scene.control.ListCell<Repuesto>() {
                            @Override
                            protected void updateItem(Repuesto item, boolean empty) {
                                super.updateItem(item, empty);
                                setText(empty || item == null ? null : getNombreRepuesto(item));
                            }
                        };
                fxRepuesto.setCellFactory(cellFactory);
                // Usar una ListCell explícita para el botón, evitando llamar 'call(null)'
                fxRepuesto.setButtonCell(new javafx.scene.control.ListCell<Repuesto>() {
                    @Override
                    protected void updateItem(Repuesto item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty || item == null ? null : getNombreRepuesto(item));
                    }
                });
            }

            // Configurar y poblar TableView de detalles con respaldo global
            if (fxTablaDetalleRepuestos != null) {
                java.util.List<javafx.scene.control.TableColumn<DetalleRepuesto, ?>> cols = fxTablaDetalleRepuestos.getColumns();

                if (cols.isEmpty()) {
                    javafx.scene.control.TableColumn<DetalleRepuesto, String> colRep = new javafx.scene.control.TableColumn<>("Repuesto");
                    colRep.setCellValueFactory(cd ->
                            new javafx.beans.property.SimpleStringProperty(nombreRepuestoDeDetalle(cd.getValue())));

                    javafx.scene.control.TableColumn<DetalleRepuesto, String> colCant = new javafx.scene.control.TableColumn<>("Cantidad");
                    colCant.setCellValueFactory(cd ->
                            new javafx.beans.property.SimpleStringProperty(asIntString(valueByMethods(cd.getValue(), "getCantidad"))));

                    javafx.scene.control.TableColumn<DetalleRepuesto, String> colPrecio = new javafx.scene.control.TableColumn<>("Precio U");
                    colPrecio.setCellValueFactory(cd ->
                            new javafx.beans.property.SimpleStringProperty(asMoneyString(valueByMethods(cd.getValue(), "getPrecioUnitario", "getPrecioU", "getPrecio"))));

                    javafx.scene.control.TableColumn<DetalleRepuesto, String> colSub = new javafx.scene.control.TableColumn<>("Subtotal");
                    colSub.setCellValueFactory(cd ->
                            new javafx.beans.property.SimpleStringProperty(asMoneyString(valueByMethods(cd.getValue(), "getSubtotal", "calcularSubtotal"))));

                    fxTablaDetalleRepuestos.getColumns().addAll(colRep, colCant, colPrecio, colSub);
                } else if (cols.size() >= 4) {
                    @SuppressWarnings("unchecked")
                    javafx.scene.control.TableColumn<DetalleRepuesto, String> colRep = (javafx.scene.control.TableColumn<DetalleRepuesto, String>) cols.get(0);
                    @SuppressWarnings("unchecked")
                    javafx.scene.control.TableColumn<DetalleRepuesto, String> colCant = (javafx.scene.control.TableColumn<DetalleRepuesto, String>) cols.get(1);
                    @SuppressWarnings("unchecked")
                    javafx.scene.control.TableColumn<DetalleRepuesto, String> colPrecio = (javafx.scene.control.TableColumn<DetalleRepuesto, String>) cols.get(2);
                    @SuppressWarnings("unchecked")
                    javafx.scene.control.TableColumn<DetalleRepuesto, String> colSub = (javafx.scene.control.TableColumn<DetalleRepuesto, String>) cols.get(3);

                    colRep.setCellValueFactory(cd ->
                            new javafx.beans.property.SimpleStringProperty(nombreRepuestoDeDetalle(cd.getValue())));
                    colCant.setCellValueFactory(cd ->
                            new javafx.beans.property.SimpleStringProperty(asIntString(valueByMethods(cd.getValue(), "getCantidad"))));
                    colPrecio.setCellValueFactory(cd ->
                            new javafx.beans.property.SimpleStringProperty(asMoneyString(valueByMethods(cd.getValue(), "getPrecioUnitario", "getPrecioU", "getPrecio"))));
                    colSub.setCellValueFactory(cd ->
                            new javafx.beans.property.SimpleStringProperty(asMoneyString(valueByMethods(cd.getValue(), "getSubtotal", "calcularSubtotal"))));
                }

                // Enlazar la tabla a la lista global para persistir entre escenas
                fxTablaDetalleRepuestos.setItems(javafx.collections.FXCollections.observableList(App.detallesServicioActual));
            }
        } catch (Exception e) {
            AlertaController.showError("No fue posible cargar los repuestos o los detalles");
        }
    }

    // Obtiene un nombre representativo del repuesto sin depender de una implementación específica
    private String getNombreRepuesto(Repuesto r) {
        if (r == null) return "";
        try {
            java.lang.reflect.Method m = r.getClass().getMethod("getNombre");
            Object val = m.invoke(r);
            return val != null ? val.toString() : "";
        } catch (Exception ignored) {
            try {
                java.lang.reflect.Method m = r.getClass().getMethod("getNombreRepuesto");
                Object val = m.invoke(r);
                return val != null ? val.toString() : "";
            } catch (Exception ignored2) {
                return r.toString();
            }
        }
    }

    // Helpers para columnas de la tabla
    private String nombreRepuestoDeDetalle(DetalleRepuesto detalle) {
        if (detalle == null) return "";
        try {
            Object repuesto = null;
            String[] getters = new String[] { "getOwnedByRepuesto", "getRepuesto" };
            for (String g : getters) {
                try {
                    java.lang.reflect.Method m = detalle.getClass().getMethod(g);
                    repuesto = m.invoke(detalle);
                    if (repuesto != null) break;
                } catch (NoSuchMethodException ignored) {
                    // probar siguiente
                }
            }
            if (repuesto instanceof Repuesto) {
                return getNombreRepuesto((Repuesto) repuesto);
            }
            return repuesto != null ? repuesto.toString() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private Object valueByMethods(Object target, String... methodNames) {
        if (target == null) return null;
        for (String name : methodNames) {
            try {
                java.lang.reflect.Method m = target.getClass().getMethod(name);
                return m.invoke(target);
            } catch (NoSuchMethodException ignored) {
                // probar siguiente
            } catch (Exception e) {
                // continuar
            }
        }
        return null;
    }

    private String asIntString(Object v) {
        if (v == null) return "0";
        if (v instanceof Number) return String.valueOf(((Number) v).intValue());
        try {
            return String.valueOf((int) Double.parseDouble(v.toString()));
        } catch (Exception e) {
            return v.toString();
        }
    }

    private String asMoneyString(Object v) {
        if (v == null) return "0.00";
        double d;
        if (v instanceof Number) {
            d = ((Number) v).doubleValue();
        } else {
            try {
                d = Double.parseDouble(v.toString());
            } catch (Exception e) {
                return v.toString();
            }
        }
        return String.format("%.2f", d);
    }
}
