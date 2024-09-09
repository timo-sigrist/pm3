package app.view.employee;

import java.util.logging.Logger;
import java.util.logging.Level;

import app.controller.BaseController;
import persistence.EmployeeDO;
import persistence.exceptions.SaveFailedException;
import persistence.exceptions.UpdateFailedException;
import services.EmployeeService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for employee modal
 */
public class EmployeeModalController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(EmployeeModalController.class.getName());
    private final EmployeeService employeeService = new EmployeeService(getEntityManager());
    public enum Mode
    {
        CREATE,
        EDIT
    }
    private Mode mode = Mode.CREATE;
    private EmployeeDO employeeDO;

    @FXML
    private TextField fnameField;
    @FXML
    private TextField lnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField birthdateField;
    @FXML
    private TextField teamnameField;
    @FXML
    private TextField ssnField;
    @FXML
    private void initialize() {
    }

    /**
     * Initializes view based on given employeeDo
     * @param employeeDO
     */
    public void setEmployeeDO(EmployeeDO employeeDO) {
        this.employeeDO = employeeDO;
        fnameField.setText(employeeDO.getFirstname());
        lnameField.setText(employeeDO.getLastname());
        emailField.setText(employeeDO.getEmail());
        birthdateField.setText(employeeDO.getBirthdate());
        teamnameField.setText(employeeDO.getTeamName());
        ssnField.setText(employeeDO.getSocialSecurityNumber());

        mode = Mode.EDIT;
    }

    /**
     *
     */
    @FXML
    private void discard() {
        // Implement discard functionality here
        ((Stage) fnameField.getScene().getWindow()).close();
    }

    /**
     * Saves employee data
     */
    @FXML
    public void save() {
        if (mode == Mode.CREATE) {
            employeeDO = new EmployeeDO();
        }

        employeeDO.setFirstname(fnameField.getText());
        employeeDO.setLastname(lnameField.getText());
        employeeDO.setEmail(emailField.getText());
        employeeDO.setBirthdate(birthdateField.getText());
        employeeDO.setTeamName(teamnameField.getText());
        employeeDO.setSocialSecurityNumber(ssnField.getText());
        employeeDO.setUserDo(loggedInUser);

        // Save if in create mode
        if (mode == Mode.CREATE)
        {
            createEmployee(employeeDO);
        } else {
            updateEmployee(employeeDO);
        }

        discard();
    }

    /**
     * Creates given employee
     * @param employeeDO
     */
    private void createEmployee(EmployeeDO employeeDO) {
        try {
            employeeService.createNewEmployee(employeeDO);
        } catch (SaveFailedException e) {
            // TODO: handle exception

            LOGGER.log(Level.SEVERE, String.format( "Failed to create new employee, %s", e));
        }
    }

    /**
     * Updates given employee
     * @param employeeDO
     */
    private void updateEmployee(EmployeeDO employeeDO) {
        try {
            employeeService.updateEmployee(employeeDO);
        } catch (UpdateFailedException e) {
            // TODO: handle exception
        }
    }
}