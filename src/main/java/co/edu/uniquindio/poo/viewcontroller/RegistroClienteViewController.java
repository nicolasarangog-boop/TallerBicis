package co.edu.uniquindio.poo.viewcontroller;

import co.edu.uniquindio.poo.app.App;
import co.edu.uniquindio.poo.controller.AlertaController;
import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Taller;
import co.edu.uniquindio.poo.paths.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.LinkedList;

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
    void registrarCliente() {
        String nombre= fxNombre.getText();
        String id= fxID.getText();
        String telefono= fxTelefono.getText();
        String direccion= fxDireccion.getText();

        //Validar que los campos no esten vacios
        if(nombre.isEmpty() || id.isEmpty() || telefono.isEmpty() || direccion.isEmpty()){
            AlertaController.showError("Todos los campos son requeridos");
            return;
        }
        Taller taller= App.taller;
        LinkedList listaBicicletas= new LinkedList();
        LinkedList listaServicios= new LinkedList();
        try{
            Cliente nuevoCliente= new Cliente(nombre, telefono, direccion, id, taller, listaBicicletas, listaServicios);
            String resultado= taller.agregarPersona(nuevoCliente);
            if (resultado.contains("fue agregado exitosamente")){
                AlertaController.showInformation("Cliente registrado exitosamente");
            } else if(resultado.contains("ya existe en el taller")){
                AlertaController.showError("El cliente ya existe en el taller");
            } else {
                AlertaController.showError("Error al registrar el cliente, verifica todos los campos");
            }
        } catch (Exception e){
            AlertaController.showError("Error al registrar el cliente: " + e.getMessage());
        }
    }

}
