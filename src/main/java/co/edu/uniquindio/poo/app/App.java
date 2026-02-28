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

public class App extends Application {

    public static App app;
    public static Taller taller = new Taller("Taller Bicis", "+57 311", "cll 1 #2-30", "900.700.340",
            new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
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
