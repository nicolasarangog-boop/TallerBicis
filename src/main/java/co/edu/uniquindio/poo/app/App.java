package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.Servicio;
import co.edu.uniquindio.poo.model.Taller;
import co.edu.uniquindio.poo.paths.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.LinkedList;
import java.util.List;
import java.util.List;
import co.edu.uniquindio.poo.model.DetalleRepuesto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class App extends Application {

    public static App app;
    public static Taller taller = new Taller("Taller Bicis", "+57 311", "cll 1 #2-30", "900.700.340",
            new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
    // Contenedor de resultados para la tabla de historial de bicicleta
    public static List<Servicio> resultadosHistorialBici = new LinkedList<>();
    // Contenedor de resultados para la tabla de orden programada
    public static List<Servicio> resultadosOrdenProgramada = new LinkedList<>();
    // Detalles (repuestos) del servicio en curso, compartidos entre vistas
    public static ObservableList<DetalleRepuesto> detallesServicioActual = FXCollections.observableArrayList();
    private Stage stageWindow;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        app = this;
        stageWindow = stage;
        Parent root = FXMLLoader.load(getClass().getResource(Paths.INICIO));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource(Paths.CSS_INICIO).toExternalForm()
        );

        stage.setScene(scene);
        stage.show();
    }

    public void setScene(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            javafx.scene.layout.Pane pane = loader.load();
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(
                    getClass().getResource(Paths.CSS_INICIO).toExternalForm()
            );
            stageWindow.setScene(scene);
            stageWindow.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
