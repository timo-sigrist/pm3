package app;

import app.view.ViewManager;
import app.view.ViewManager.View;
import app.view.WindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App extends Application {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    private final Map<View, FXMLLoader> screenLoaders = new HashMap<>();

    public App() {
        LOGGER.log(Level.INFO, "Main Application has been started");
    }

    /**
     * Starts the application and opens login view
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        loadAllScreens();
        openLoginWindow(primaryStage);
    }

    /**
     * loads all screens
     */
    private void loadAllScreens() {
        Map<View, String> views = ViewManager.getViews();

        for (Map.Entry<View, String> entry : views.entrySet()) {
            loadScreen(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Loads a screen fxml files and puts it into loadScreens
     */
    private void loadScreen(View name, String filename) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
            screenLoaders.put(name, loader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a window and sets the Szene the MainWindow.fxml
     *
     * @param stage primary stage
     */
    private void openLoginWindow(Stage stage) {
        WindowController loginWindow = new WindowController(View.LOGIN, "PM3");
        loginWindow.getStage().show();
    }
}