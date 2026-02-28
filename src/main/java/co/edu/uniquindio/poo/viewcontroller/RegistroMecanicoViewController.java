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
    }

}
