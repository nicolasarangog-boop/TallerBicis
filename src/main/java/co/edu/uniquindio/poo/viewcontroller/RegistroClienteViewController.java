package co.edu.uniquindio.poo.viewcontroller;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.paths.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistroClienteViewController {

    @FXML
    private TextField fxNombre;

    @FXML
    private TextField fxID;

    @FXML
    private TextField fxTelefono;

    @FXML
    private TextField fxDireccion;

    @FXML
    private Button fxBotonRegistrar;

    @FXML
    private Button btnAtras;

    @FXML
    void cambioHaciaInicio(ActionEvent event) {
        App.app.setScene(Paths.INICIO);
    }

    @FXML
    void registrarCliente(ActionEvent event) {
    }
}
