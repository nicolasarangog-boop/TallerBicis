package co.edu.uniquindio.poo.viewcontroller;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.paths.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistroRepuestoViewController {

    @FXML
    private TextField fxNombreRp;

    @FXML
    private TextField fxCodigoRp;

    @FXML
    private TextField fxStockRp;

    @FXML
    private Button fxBtnCrearRp;

    @FXML
    private Button fxBtnAtras;

    @FXML
    void cambioHaciaInicio(ActionEvent event) {
        App.app.setScene(Paths.INICIO);
    }

}
