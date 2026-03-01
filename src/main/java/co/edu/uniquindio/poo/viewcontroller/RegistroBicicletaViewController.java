package co.edu.uniquindio.poo.viewcontroller;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.controller.AlertaController;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Taller;
import co.edu.uniquindio.poo.model.TipoBicicleta;
import co.edu.uniquindio.poo.paths.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.LinkedList;
import java.util.Optional;

import co.edu.uniquindio.poo.model.Bicicleta;
import co.edu.uniquindio.poo.model.Persona;

public class RegistroBicicletaViewController {

    @FXML
    private TextField fxMarca;

    @FXML
    private TextField fxNoSerial;

    @FXML
    private TextField fxColor;

    @FXML
    private TextField fxAnio;

    @FXML
    private TextField fxID;

    @FXML
    private ComboBox<TipoBicicleta> fxTipoBicicleta;

    @FXML
    private Button fxBotonRegistrar;

    @FXML
    private Button btnAtras;

    @FXML
    void initialize() {
        fxTipoBicicleta.getItems().addAll(TipoBicicleta.values());
    }

    @FXML
    void cambioHaciaInicio(ActionEvent event) {
        App.app.setScene(Paths.INICIO);
    }

    @FXML
    void crearBicicleta(ActionEvent event) {
        String marca = fxMarca.getText();
        String NoSerial = fxNoSerial.getText();
        String color = fxColor.getText();
        String anio = fxAnio.getText();
        String id = fxID.getText();
        TipoBicicleta tipo = fxTipoBicicleta.getValue();

        // Validar que los campos no estén vacíos
        if (marca.isBlank() || NoSerial.isBlank() || color.isBlank() || anio.isBlank() || id.isBlank() || tipo == null) {
            AlertaController.showError("Todos los campos son requeridos");
            return;
        }

        Taller taller = App.taller;

        try {
            // Verificar que el id pertenezca a un cliente ya registrado en el taller
            Optional personaExistente = taller.buscarPersona(id);
            if (personaExistente.isEmpty()) {
                AlertaController.showError("No existe un cliente registrado con el id proporcionado");
                return;
            }
            Object personaObj = personaExistente.get();
            if (!(personaObj instanceof Cliente)) {
                AlertaController.showError("El id proporcionado no pertenece a un cliente");
                return;
            }
            Cliente cliente = (Cliente) personaObj;

            // Crear la bicicleta y registrarla en el taller
            Bicicleta nuevaBicicleta = new Bicicleta(color, marca, NoSerial, anio, tipo, cliente, taller);
            String resultado = taller.agregarBiciceta(nuevaBicicleta);

            if (resultado.contains("fue agregado exitosamente")) {
                // Añadir la bicicleta al cliente mediante el set
                java.util.List<Bicicleta> bicisCliente = cliente.getListaBicicletas();
                if (bicisCliente == null) {
                    bicisCliente = new LinkedList<>();
                }
                bicisCliente.add(nuevaBicicleta);
                cliente.setListaBicicletas(bicisCliente);

                AlertaController.showInformation("Bicicleta registrada exitosamente");
            } else if (resultado.contains("ya existe en el taller")) {
                AlertaController.showError("La bicicleta ya existe en el taller");
            } else {
                AlertaController.showError("Error al registrar la bicicleta, verifica todos los campos");
            }
        } catch (Exception e) {
            AlertaController.showError("Error al registrar la bicicleta: " + e.getMessage());
        }
    }

}
