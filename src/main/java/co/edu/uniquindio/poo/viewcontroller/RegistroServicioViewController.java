package co.edu.uniquindio.poo.viewcontroller;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.paths.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private TextArea txtTrabajos;

    @FXML
    private TextArea txtCosto;

    @FXML
    private TableView tablaRepuestos;

    @FXML
    private Label lblTotal;

    @FXML
    private Button agregarRepuesto;

    @FXML
    private Button calcularTotal;

    @FXML
    private Button btnAtras;

    @FXML
    void cambioHaciaInicio(ActionEvent event) {
        App.app.setScene(Paths.INICIO);
    }





}
