package co.edu.uniquindio.poo.viewcontroller;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.model.TipoBicicleta;
import co.edu.uniquindio.poo.paths.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
    }

}
