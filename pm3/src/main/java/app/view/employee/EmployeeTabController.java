package app.view.employee;

import app.controller.BaseController;

import persistence.EmployeeDO;
import persistence.exceptions.DeleteFailedException;
import services.EmployeeService;

import app.view.ViewManager;
import app.view.WindowController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for employee tab
 */
public class EmployeeTabController extends BaseController {
    private final EmployeeService employeeService;
    private final ObservableList<EmployeeDO> employeeTableData;
    private final TableColumn<EmployeeDO, Integer> idColumn;
    private final TableColumn<EmployeeDO, String> firstnameColumn;
    private final TableColumn<EmployeeDO, String> lastnameColumn;
    private final TableColumn<EmployeeDO, String> emailColumn;
    private final TableColumn<EmployeeDO, String> birthdateColumn;
    private final TableColumn<EmployeeDO, String> teamnameColumn;
    private final TableColumn<EmployeeDO, String> ssnColumn;

    public EmployeeTabController() {
        employeeService = new EmployeeService(getEntityManager());
        employeeTableData = FXCollections.observableArrayList();

        idColumn = new TableColumn<>("ID");
        firstnameColumn = new TableColumn<>("Firstname");
        lastnameColumn = new TableColumn<>("Lastname");
        emailColumn = new TableColumn<>("Email");
        birthdateColumn = new TableColumn<>("Birthdate");
        teamnameColumn = new TableColumn<>("Teamname");
        ssnColumn = new TableColumn<>("SSN");
    }

    @FXML
    public TableView<EmployeeDO> employeeTableView;

    /**
     * Initializes employee table
     */
    @FXML
    public void initialize() {
        idColumn.setPrefWidth(80);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));

        firstnameColumn.setPrefWidth(150);
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));

        lastnameColumn.setPrefWidth(150);
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        emailColumn.setPrefWidth(150);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        birthdateColumn.setPrefWidth(100);
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));

        teamnameColumn.setPrefWidth(150);
        teamnameColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));

        ssnColumn.setPrefWidth(200);
        ssnColumn.setCellValueFactory(new PropertyValueFactory<>("socialSecurityNumber"));

        employeeTableView.getColumns().addAll(idColumn, firstnameColumn, lastnameColumn, emailColumn, birthdateColumn, teamnameColumn, ssnColumn);
        employeeTableView.setItems(employeeTableData);

        reloadData();
    }

    /**
     * Reloads data in table from db
     */
    public void reloadData() {
        employeeTableData.clear();
        employeeTableData.addAll(employeeService.findByUser(loggedInUser));
        employeeTableView.refresh();
    }

    /**
     * Opens window to create employee
     */
    @FXML
    private void createEmployee() {
        WindowController window = new WindowController(ViewManager.View.CREATE_EMPLOYEE, "Employee");

        window.getStage().showAndWait();
        reloadData();
    }

    /**
     * Opens window to edit employee
     */
    @FXML
    private void editEmployee() {
        WindowController window = new WindowController(ViewManager.View.CREATE_EMPLOYEE, "Employee");

        EmployeeDO employee = employeeTableView.getSelectionModel().getSelectedItem();

        ((EmployeeModalController) window.getLoader().getController()).setEmployeeDO(employee);

        window.getStage().showAndWait();

        reloadData();
    }

    /**
     * Deletes selected employee
     */
    @FXML
    private void deleteEmployee() {
        EmployeeDO employee = employeeTableView.getSelectionModel().getSelectedItem();
        try {
            employeeService.deleteEmployee(employee);
        } catch (DeleteFailedException e) {
            // TODO: handle exception
        }

        reloadData();
    }
}
