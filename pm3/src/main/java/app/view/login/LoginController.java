package app.view.login;

import app.controller.BaseController;
import services.LoginService;
import services.exceptions.InvalidPasswordException;
import services.exceptions.UserNotFoundException;
import app.view.ViewManager.View;

import app.view.WindowController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller for login functionality
 */
public class LoginController extends BaseController {
    
    private final LoginService loginService = new LoginService(getEntityManager(), true);

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text validationText;

    @FXML
    private Button submitButton;


    /**
     * Handles login
     */
    @FXML
    public void login() {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        try
        {
            loggedInUser = loginService.login(username, password);

            redirectToMainWindow();
        }
        catch(UserNotFoundException e)
        {
            validationText.setText("Username or Password is invalid!");
        }
        catch (InvalidPasswordException e)
        {
            validationText.setText("Username or Password is invalid!");
        } 
    }

    /**
     * Closes login window and redirects to main window
     */
    private void redirectToMainWindow() {
        //Close login window
        ((Stage) submitButton.getScene().getWindow()).close();

        //Open main window
        WindowController window = new WindowController(View.MAIN_WINDOW, "Overview");
        window.getStage().show();
    }
}
