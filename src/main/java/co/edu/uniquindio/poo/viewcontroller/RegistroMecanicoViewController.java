package co.edu.uniquindio.poo.viewcontroller;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.model.TipoBicicleta;
import co.edu.uniquindio.poo.model.TipoEspecialidad;
import co.edu.uniquindio.poo.paths.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import co.edu.uniquindio.poo.controller.AlertaController;
import co.edu.uniquindio.poo.model.Mecanico;
import co.edu.uniquindio.poo.model.Servicio;
import co.edu.uniquindio.poo.model.Taller;
import java.util.LinkedList;

public class RegistroMecanicoViewController {

    @FXML
    private TextField fxNombre;

    @FXML
    private TextField fxID;

    @FXML
    private TextField fxTelefono;

    @FXML
    private TextField fxDireccion;

    @FXML
    private TextField fxSueldo;

    @FXML
    private ComboBox<TipoEspecialidad> cbEspecialidad;

    @FXML
    private Button fxRegistrarMecanico;

    @FXML
    private Button btnAtras;

    @FXML
    void cambioHaciaInicio(javafx.event.ActionEvent event) {
        App.app.setScene(Paths.INICIO);
    }

    @FXML
    void initialize() {
        cbEspecialidad.getItems().addAll(TipoEspecialidad.values());
    }

    @FXML
    void crearMecanico(ActionEvent event){
        String nombre = fxNombre.getText();
        String id = fxID.getText();
        String telefono = fxTelefono.getText();
        String direccion = fxDireccion.getText();
        String sueldoTexto = fxSueldo.getText();
        TipoEspecialidad especialidad = cbEspecialidad.getValue();

        // Validar que los campos no estén vacíos
        if (nombre.isBlank() || id.isBlank() || telefono.isBlank() || direccion.isBlank() || sueldoTexto.isBlank() || especialidad == null) {
            AlertaController.showError("Todos los campos son requeridos");
            return;
        }

        int sueldo;
        try {
            sueldo = Integer.parseInt(sueldoTexto);
        } catch (NumberFormatException e) {
            AlertaController.showError("El sueldo debe ser un número entero válido");
            return;
        }

        Taller taller = App.taller;

        try {
            LinkedList<Servicio> listaServicios = new LinkedList<>();
            Mecanico nuevoMecanico = new Mecanico(nombre, telefono, direccion, id, taller, listaServicios, especialidad, sueldo, "");
            String resultado = taller.agregarPersona(nuevoMecanico);

            if (resultado.contains("fue agregado exitosamente")) {
                AlertaController.showInformation("Mecánico registrado exitosamente");
            } else if (resultado.contains("ya existe en el taller")) {
                AlertaController.showError("El mecánico ya existe en el taller");
            } else {
                AlertaController.showError("Error al registrar el mecánico, verifica todos los campos");
            }
        } catch (Exception e) {
            AlertaController.showError("Error al registrar el mecánico: " + e.getMessage());
        }
    }

}
