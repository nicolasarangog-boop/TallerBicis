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

        // Solo formato ISO: yyyy-MM-dd
        LocalDate fechaIngresada;
        try {
            fechaIngresada = LocalDate.parse(fechaTexto);
        } catch (DateTimeParseException e) {
            AlertaController.showError("Formato de fecha inválido. Use el formato yyyy-MM-dd");
            return;
        }

        // Validación de rango
        LocalDate hoy = LocalDate.now();
        if (fechaIngresada.isAfter(hoy.plusMonths(1))) {
            AlertaController.showError("La fecha no puede ser mayor a un mes a partir de hoy");
            return;
        }
        if (fechaIngresada.isBefore(hoy.minusYears(1))) {
            AlertaController.showError("La fecha no puede ser anterior a un año desde hoy");
            return;
        }

        try {
            // Consultar primero con LocalDate; si no, con el mismo String ingresado
            java.util.List<Servicio> resultados = consultarOrdenesProgramadasFlex(fechaIngresada, fechaTexto);

            // Fallback: filtrar manualmente por LocalDate
            if (resultados == null || resultados.isEmpty()) {
                java.util.List<Servicio> todos = obtenerListaCompletaServicios();
                java.util.List<Servicio> filtrados = new java.util.ArrayList<>();
                for (Servicio s : todos) {
                    LocalDate f = extraerFechaServicio(s);
                    if (f != null && f.isEqual(fechaIngresada)) {
                        filtrados.add(s);
                    }
                }
                resultados = filtrados;
            }

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

    // ----------------- Helpers privados -----------------

    private LocalDate parseFlexibleDate(String txt) {
        try {
            return LocalDate.parse(txt); // ISO yyyy-MM-dd
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDate.parse(txt, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception ignored) { }
        try {
            return LocalDate.parse(txt, java.time.format.DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        } catch (Exception ignored) { }
        try {
            return LocalDate.parse(txt, java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (Exception ignored) { }
        return null;
    }

    private java.util.List<Servicio> consultarOrdenesProgramadasFlex(LocalDate fecha, String fechaTexto) {
        Object taller = App.taller;
        if (taller == null) return java.util.Collections.emptyList();

        String[] nombres = new String[] {
                "consultarOrdenProgramada",
                "consultarOrdenesProgramadas",
                "buscarOrdenProgramada",
                "buscarOrdenesProgramadas",
                "consultarServiciosPorFecha",
                "consultarServiciosDia"
        };

        // Intentar primero con LocalDate
        for (String n : nombres) {
            Object res = tryInvoke(taller, n, new Class<?>[]{LocalDate.class}, fecha);
            java.util.List<Servicio> list = toServiciosList(res);
            if (!list.isEmpty()) return list;
        }

        // Intentar con el mismo String que ingresó el usuario (formato ISO esperado)
        for (String n : nombres) {
            Object res = tryInvoke(taller, n, new Class<?>[]{String.class}, fechaTexto);
            java.util.List<Servicio> list = toServiciosList(res);
            if (!list.isEmpty()) return list;
        }

        return java.util.Collections.emptyList();
    }

    private Object tryInvoke(Object target, String method, Class<?>[] paramTypes, Object arg) {
        try {
            java.lang.reflect.Method m = target.getClass().getMethod(method, paramTypes);
            return m.invoke(target, arg);
        } catch (NoSuchMethodException ignored) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private java.util.List<Servicio> toServiciosList(Object res) {
        if (res instanceof java.util.List<?> list) {
            // Intentar castear elemento a Servicio; si falla, se ignora
            java.util.List<Servicio> out = new java.util.ArrayList<>();
            for (Object o : list) {
                if (o instanceof Servicio s) out.add(s);
            }
            return out;
        }
        return java.util.Collections.emptyList();
    }

    private java.util.List<Servicio> obtenerListaCompletaServicios() {
        Object taller = App.taller;
        if (taller == null) return java.util.Collections.emptyList();
        String[] posibles = new String[] { "getListaServicios", "getServicios", "getHistorialServicios", "consultarTodosServicios" };
        for (String n : posibles) {
            Object res = tryInvoke(taller, n, new Class<?>[]{}, null);
            java.util.List<Servicio> list = toServiciosList(res);
            if (!list.isEmpty()) return list;
        }
        return java.util.Collections.emptyList();
    }

    private LocalDate extraerFechaServicio(Servicio s) {
        if (s == null) return null;
        try {
            java.lang.reflect.Method m = s.getClass().getMethod("getFecha");
            Object v = m.invoke(s);
            if (v instanceof LocalDate ld) return ld;
            if (v instanceof java.util.Date d) {
                return d.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            }
            if (v instanceof String str) {
                try {
                    // Solo se admite ISO yyyy-MM-dd
                    return LocalDate.parse(str);
                } catch (DateTimeParseException ignored) {
                    return null;
                }
            }
        } catch (Exception ignored) { }
        return null;
    }

    
}
