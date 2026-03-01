package co.edu.uniquindio.poo.viewcontroller;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.model.Servicio;
import co.edu.uniquindio.poo.model.Taller;
import co.edu.uniquindio.poo.paths.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.uniquindio.poo.controller.AlertaController;
import co.edu.uniquindio.poo.model.Cliente;
import java.time.LocalDate;
import java.util.LinkedList;

public class RegistroServicioViewController {

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextArea txtMotivo;

    @FXML
    private TextArea txtDiagnostico;

    @FXML
    private TextField txtTrabajos;

    @FXML
    private TextField txtValorServicio;

    @FXML
    private Label lblTotal;

    @FXML
    private Button agregarRepuesto;

    @FXML
    private Button crearServicio;

    @FXML
    private Button btnAtras;

    @FXML
    private TextField txtNoSerialBici;

    @FXML
    private TextField txtIdCliente;

    // Contador interno de trabajos realizados para la bicicleta actual (no editable por el usuario)
    private int trabajosRealizadosActual = 0;

    // Estado persistente del formulario para conservar datos al cambiar de escena
    private static FormState formState;

    private static class FormState {
        java.time.LocalDate fecha;
        String codigo;
        String motivo;
        String diagnostico;
        String trabajos;
        String valorServicio;
        String noSerialBici;
        String idCliente;
        String total;
    }

    private void restoreFormState() {
        if (formState == null) return;
        if (dpFecha != null) dpFecha.setValue(formState.fecha);
        if (txtCodigo != null) txtCodigo.setText(formState.codigo);
        if (txtMotivo != null) txtMotivo.setText(formState.motivo);
        if (txtDiagnostico != null) txtDiagnostico.setText(formState.diagnostico);
        if (txtTrabajos != null) txtTrabajos.setText(formState.trabajos != null ? formState.trabajos : "0");
        if (txtValorServicio != null) txtValorServicio.setText(formState.valorServicio);
        if (txtNoSerialBici != null) txtNoSerialBici.setText(formState.noSerialBici);
        if (txtIdCliente != null) txtIdCliente.setText(formState.idCliente);
        if (lblTotal != null && formState.total != null) lblTotal.setText(formState.total);
    }

    private void saveFormState() {
        FormState s = new FormState();
        s.fecha = dpFecha != null ? dpFecha.getValue() : null;
        s.codigo = txtCodigo != null ? txtCodigo.getText() : null;
        s.motivo = txtMotivo != null ? txtMotivo.getText() : null;
        s.diagnostico = txtDiagnostico != null ? txtDiagnostico.getText() : null;
        s.trabajos = txtTrabajos != null ? txtTrabajos.getText() : null;
        s.valorServicio = txtValorServicio != null ? txtValorServicio.getText() : null;
        s.noSerialBici = txtNoSerialBici != null ? txtNoSerialBici.getText() : null;
        s.idCliente = txtIdCliente != null ? txtIdCliente.getText() : null;
        s.total = lblTotal != null ? lblTotal.getText() : null;
        formState = s;
    }

    @FXML
    public void initialize() {
        if (txtTrabajos != null) {
            txtTrabajos.setEditable(true);
            txtTrabajos.setFocusTraversable(true);
            if (txtTrabajos.getText() == null || txtTrabajos.getText().isBlank()) {
                txtTrabajos.setText("0");
            }
            // Filtro: solo permitir dígitos
            txtTrabajos.textProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null && !newVal.matches("\\d*")) {
                    txtTrabajos.setText(newVal.replaceAll("\\D", ""));
                }
            });
        }
        // Ya no se actualiza automáticamente por No. Serial; el usuario ingresa el valor manualmente
        // Restaurar el estado previo (si existe) al volver del agregado de repuestos
        restoreFormState();
    }

    /**
     * Actualiza el número de trabajos realizados para la bicicleta con el No. Serial dado.
     */
    private void actualizarTrabajosPorBicicleta(String noSerial) {
        int trabajos = 0;
        if (noSerial != null && !noSerial.isBlank()) {
            trabajos = consultarTrabajosPorBicicleta(noSerial);
        }
        trabajosRealizadosActual = Math.max(0, trabajos);
        if (txtTrabajos != null) {
            txtTrabajos.setText(String.valueOf(trabajosRealizadosActual));
        }
    }

    private int consultarTrabajosPorBicicleta(String noSerial) {
        if (noSerial == null || noSerial.isBlank() || App.taller == null) return 0;

        final Object taller = App.taller;

        // 1) Intentar directamente obtener la colección de servicios por No. Serial
        String[] metodosServicios = new String[] {
                "consultarServiciosPorIdBici",
                "consultarHistorialServiciosBicicleta",
                "obtenerServiciosPorBicicleta",
                "buscarServiciosPorBicicleta"
        };
        Object res = invokeFirst(taller, metodosServicios, new Class[]{String.class}, noSerial);
        int size = sizeIfCollection(res);
        if (size > 0) return size;

        // 2) Fallback: obtener la bicicleta y luego su lista de servicios
        String[] metodosBici = new String[] {
                "buscarBicicleta",
                "consultarBicicletaPorSerial",
                "obtenerBicicletaPorSerial",
                "getBicicleta"
        };
        Object bicicleta = invokeFirst(taller, metodosBici, new Class[]{String.class}, noSerial);
        if (bicicleta != null) {
            String[] metodosHist = new String[] { "getHistorialServicios", "getServicios", "getListaServicios" };
            Object lista = invokeFirst(bicicleta, metodosHist, new Class[] {});
            return sizeIfCollection(lista);
        }

        return 0;
    }

    private Object invokeFirst(Object target, String[] names, Class<?>[] paramTypes, Object... args) {
        for (String nombre : names) {
            try {
                java.lang.reflect.Method m = target.getClass().getMethod(nombre, paramTypes);
                return m.invoke(target, args);
            } catch (NoSuchMethodException ignored) {
                // Probar con el siguiente nombre
            } catch (Exception e) {
                // Continuar con el siguiente nombre ante cualquier excepción
            }
        }
        return null;
    }

    private int sizeIfCollection(Object obj) {
        return (obj instanceof java.util.Collection) ? Math.max(0, ((java.util.Collection<?>) obj).size()) : 0;
    }

    @FXML
    void cambioHaciaInicio(ActionEvent event) {
        App.app.setScene(Paths.INICIO);
    }

    @FXML
    void cambioHaciaTablaDetalles(ActionEvent event) {
        // Guardar el estado actual del formulario antes de cambiar de escena
        saveFormState();
        App.app.setScene(Paths.TABLA_DETALLES_REPUESTO);
    }

    @FXML
    void calcularTotal() {
        // Validar el valor del servicio
        String valorStr = txtValorServicio.getText();
        if (valorStr == null || valorStr.isBlank()) {
            AlertaController.showWarning("Ingrese el valor del servicio para calcular el total");
            return;
        }
        double valorServicio;
        try {
            valorServicio = Double.parseDouble(valorStr);
            if (valorServicio < 0) {
                AlertaController.showError("El valor del servicio debe ser un número no negativo");
                return;
            }
        } catch (NumberFormatException e) {
            AlertaController.showError("Ingrese un valor numérico válido para el servicio");
            return;
        }

        // Tomar los trabajos realizados ingresados manualmente
        String trabajosStr = txtTrabajos.getText();
        int trabajosRealizadosParaCalculo;
        try {
            trabajosRealizadosParaCalculo = Integer.parseInt(trabajosStr);
            if (trabajosRealizadosParaCalculo < 0) {
                AlertaController.showError("El número de trabajos realizados debe ser un entero no negativo");
                return;
            }
        } catch (NumberFormatException ex) {
            AlertaController.showError("Ingrese un número entero válido en 'Trabajos realizados'");
            return;
        }

        // Construir un Servicio temporal con los detalles actuales para reutilizar la lógica de negocio
        Servicio servicioTemp = new Servicio(
                LocalDate.now(),
                "TEMP",
                "",
                "",
                trabajosRealizadosParaCalculo,
                0.0,
                App.taller,
                null,
                null,
                new LinkedList<>(App.detallesServicioActual)
        );

        double total = servicioTemp.calcularCostoTotal(valorServicio);
        lblTotal.setText(String.format("%.2f", total));
    }

    @FXML
    void generarServicio(ActionEvent event) {
        // 1) Validar y obtener datos
        java.util.Optional<DatosServicio> datosOpt = obtenerYValidarDatos();
        if (datosOpt.isEmpty()) return;

        DatosServicio datos = datosOpt.get();

        // 2) Confirmar creación
        if (!confirmarCreacion()) return;

        // 3) Buscar y validar cliente
        Cliente cliente = buscarYValidarCliente(datos.idCliente());
        if (cliente == null) return;

        // 4) Construir Servicio y calcular costo total
        Servicio nuevoServicio = construirServicio(datos, cliente);

        // 5) Persistir y limpiar
        persistirServicioYLimpiar(nuevoServicio);
    }

    private void limpiarFormulario() {
        dpFecha.setValue(null);
        txtCodigo.clear();
        txtMotivo.clear();
        txtDiagnostico.clear();
        if (txtTrabajos != null) {
            txtTrabajos.setText("0");
        }
        txtValorServicio.clear();
        txtNoSerialBici.clear();
        txtIdCliente.clear();
        lblTotal.setText("0.00");
        // Al limpiar el formulario tras crear el servicio, también limpiamos el estado persistido
        formState = null;
    }

    // Contenedor inmutable para datos validados del formulario
    private record DatosServicio(
            java.time.LocalDate fecha,
            String codigo,
            String motivo,
            String diagnostico,
            int trabajosRealizados,
            double valorServicio,
            String noSerialBici,
            String idCliente
    ) {}

    // 1) Obtiene y valida entradas, devolviendo datos parseados o vacío si hay error
    private java.util.Optional<DatosServicio> obtenerYValidarDatos() {
        if (dpFecha.getValue() == null) {
            AlertaController.showError("La fecha es obligatoria");
            return java.util.Optional.empty();
        }
        String codigo = txtCodigo.getText();
        String motivo = txtMotivo.getText();
        String diagnostico = txtDiagnostico.getText();
        String trabajosStr = txtTrabajos.getText();
        String noSerialBici = txtNoSerialBici.getText();
        String idCliente = txtIdCliente.getText();
        String valorServicioStr = txtValorServicio.getText();

        if (codigo == null || codigo.isBlank()
                || motivo == null || motivo.isBlank()
                || diagnostico == null || diagnostico.isBlank()
                || trabajosStr == null || trabajosStr.isBlank()
                || noSerialBici == null || noSerialBici.isBlank()
                || idCliente == null || idCliente.isBlank()
                || valorServicioStr == null || valorServicioStr.isBlank()) {
            AlertaController.showError("Todos los campos son requeridos");
            return java.util.Optional.empty();
        }

        int trabajosRealizados;
        try {
            trabajosRealizados = Integer.parseInt(trabajosStr);
            if (trabajosRealizados < 0) {
                AlertaController.showError("El número de trabajos realizados debe ser un entero no negativo");
                return java.util.Optional.empty();
            }
        } catch (NumberFormatException e) {
            AlertaController.showError("Ingrese un número entero válido para 'Trabajos realizados'");
            return java.util.Optional.empty();
        }

        double valorServicio;
        try {
            valorServicio = Double.parseDouble(valorServicioStr);
            if (valorServicio < 0) {
                AlertaController.showError("El valor del servicio debe ser no negativo");
                return java.util.Optional.empty();
            }
        } catch (NumberFormatException e) {
            AlertaController.showError("Ingrese un valor numérico válido para el servicio");
            return java.util.Optional.empty();
        }

        if (App.detallesServicioActual.isEmpty()) {
            AlertaController.showWarning("Agregue al menos un detalle de repuesto antes de generar el servicio");
            return java.util.Optional.empty();
        }

        return java.util.Optional.of(new DatosServicio(
                dpFecha.getValue(),
                codigo,
                motivo,
                diagnostico,
                trabajosRealizados,
                valorServicio,
                noSerialBici,
                idCliente
        ));
    }

    // 2) Pide confirmación al usuario
    private boolean confirmarCreacion() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmación");
        confirm.setHeaderText("¿Confirmar creación del servicio?");
        confirm.setContentText("Verifique que ha agregado todos los detalles de repuestos.");
        java.util.Optional<ButtonType> res = confirm.showAndWait();
        return res.isPresent() && res.get() == ButtonType.OK;
    }

    // 3) Busca el cliente y valida su existencia/tipo
    private Cliente buscarYValidarCliente(String idCliente) {
        Taller taller = App.taller;
        java.util.Optional personaOpt = taller.buscarPersona(idCliente);
        if (personaOpt.isEmpty() || !(personaOpt.get() instanceof Cliente)) {
            AlertaController.showError("No existe un cliente registrado con el ID proporcionado");
            return null;
        }
        return (Cliente) personaOpt.get();
    }

    // 4) Construye el Servicio y calcula su costo total
    private Servicio construirServicio(DatosServicio datos, Cliente cliente) {
        Servicio servicio = new Servicio(
                datos.fecha(),
                datos.codigo(),
                datos.motivo(),
                datos.diagnostico(),
                datos.trabajosRealizados(),
                0.0,
                App.taller,
                null, // Mecanico no especificado en el formulario
                cliente,
                new java.util.LinkedList<>(App.detallesServicioActual)
        );
        double costoTotal = servicio.calcularCostoTotal(datos.valorServicio());
        servicio.setCostoTotal(costoTotal);
        return servicio;
    }

    // 5) Persiste el servicio en el taller y limpia estado/formulario
    private void persistirServicioYLimpiar(Servicio servicio) {
        Taller taller = App.taller;
        String resultado = taller.agregarServicio(servicio);
        if (resultado != null && resultado.contains("fue agregado exitosamente")) {
            AlertaController.showInformation("Servicio creado exitosamente. Costo total: " + String.format("%.2f", servicio.getCostoTotal()));
            App.detallesServicioActual.clear();
            limpiarFormulario();
        } else {
            AlertaController.showError("Error al crear el servicio. " + (resultado != null ? resultado : ""));
        }
    }
}
