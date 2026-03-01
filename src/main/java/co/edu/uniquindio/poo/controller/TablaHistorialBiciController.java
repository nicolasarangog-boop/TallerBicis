package co.edu.uniquindio.poo.controller;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.model.Servicio;
import co.edu.uniquindio.poo.paths.Paths;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.lang.reflect.Method;
import java.util.List;

public class TablaHistorialBiciController {

    @FXML
    private TableView<Servicio> fxTablaHistorialBicis;

    @FXML
    private Button btnAtras;

    @FXML
    void cambioHaciaHistorial(ActionEvent event) {
        App.app.setScene(Paths.HISTORIAL_SERVICIOS_BICI);
    }

    @FXML
    private void initialize() {
        configurarColumnas();
        cargarDatos(App.resultadosHistorialBici);
    }

    private void configurarColumnas() {
        if (fxTablaHistorialBicis == null) return;
        List<TableColumn<Servicio, ?>> cols = fxTablaHistorialBicis.getColumns();
        if (cols.size() < 7) return;

        @SuppressWarnings("unchecked")
        TableColumn<Servicio, String> colFecha = (TableColumn<Servicio, String>) cols.get(0);
        @SuppressWarnings("unchecked")
        TableColumn<Servicio, String> colCodigo = (TableColumn<Servicio, String>) cols.get(1);
        @SuppressWarnings("unchecked")
        TableColumn<Servicio, String> colMotivo = (TableColumn<Servicio, String>) cols.get(2);
        @SuppressWarnings("unchecked")
        TableColumn<Servicio, String> colDiagnostico = (TableColumn<Servicio, String>) cols.get(3);
        @SuppressWarnings("unchecked")
        TableColumn<Servicio, String> colTrabajos = (TableColumn<Servicio, String>) cols.get(4);
        @SuppressWarnings("unchecked")
        TableColumn<Servicio, String> colCosto = (TableColumn<Servicio, String>) cols.get(5);
        @SuppressWarnings("unchecked")
        TableColumn<Servicio, String> colRepuestos = (TableColumn<Servicio, String>) cols.get(6);

        colFecha.setCellValueFactory(cd -> new SimpleStringProperty(invokeGetterAsString(cd.getValue(), "getFecha")));
        colCodigo.setCellValueFactory(cd -> new SimpleStringProperty(invokeGetterAsString(cd.getValue(), "getCodigo")));
        colMotivo.setCellValueFactory(cd -> new SimpleStringProperty(invokeGetterAsString(cd.getValue(), "getMotivo")));
        colDiagnostico.setCellValueFactory(cd -> new SimpleStringProperty(invokeGetterAsString(cd.getValue(), "getDiagnostico")));
        colTrabajos.setCellValueFactory(cd -> new SimpleStringProperty(invokeGetterAsString(cd.getValue(), "getTrabajosRealizados")));
        colCosto.setCellValueFactory(cd -> new SimpleStringProperty(invokeGetterAsString(cd.getValue(), "getCostoTotal")));
        colRepuestos.setCellValueFactory(cd -> new SimpleStringProperty(obtenerResumenRepuestos(cd.getValue())));
    }

    private void cargarDatos(List<Servicio> datos) {
        if (fxTablaHistorialBicis != null && datos != null) {
            fxTablaHistorialBicis.setItems(FXCollections.observableArrayList(datos));
        }
    }

    private String invokeGetterAsString(Object target, String getter) {
        if (target == null) return "-";
        try {
            Method m = target.getClass().getMethod(getter);
            Object value = m.invoke(target);
            return value == null ? "-" : String.valueOf(value);
        } catch (Exception e) {
            return "-";
        }
    }

    private String obtenerResumenRepuestos(Servicio s) {
        if (s == null) return "-";
        String[] posibles = new String[] {
                "getListaRepuestos",
                "getRepuestos",
                "getListaDetalleRepuestos",
                "getListaDetalleRepuesto"
        };
        for (String getter : posibles) {
            try {
                Method m = s.getClass().getMethod(getter);
                Object value = m.invoke(s);
                if (value instanceof List<?> list) {
                    return list.isEmpty() ? "0" : String.valueOf(list.size());
                }
            } catch (Exception ignored) { }
        }
        return "-";
    }
}
