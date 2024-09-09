package app.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller used for handling all the windows
 */
public class WindowController {
    private static final Logger logger = Logger.getLogger("uiLogger");
    private FXMLLoader loader;
    private Stage stage;
    private Object controller;

    public WindowController(ViewManager.View view, String title) {
        try {
            this.loader = new FXMLLoader(this.getClass().getResource(ViewManager.getViews().get(view)));
            Pane rootNode = (Pane) this.loader.load();
            Scene scene = new Scene(rootNode);
            this.controller = this.loader.getController();
            this.stage = new Stage();
            this.stage.setTitle(title);
            this.stage.setScene(scene);
            this.stage.initModality(Modality.APPLICATION_MODAL);
        } catch (Exception var4) {
            logger.log(Level.WARNING, "Window could not be loaded!", var4);
        }
    }

    public FXMLLoader getLoader() {
        return this.loader;
    }

    public Stage getStage() {
        return this.stage;
    }

    public Object getController() {
        return this.controller;
    }
}