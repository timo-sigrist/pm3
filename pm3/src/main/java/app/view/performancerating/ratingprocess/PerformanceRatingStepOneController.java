package app.view.performancerating.ratingprocess;

import app.view.ViewManager;
import app.view.performancerating.GoalController;
import app.view.performancerating.QuestionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import persistence.EmployeeDO;
import persistence.GoalDO;
import persistence.PerformanceRatingDO;
import persistence.QuestionDO;
import persistence.exceptions.SaveFailedException;
import services.EmployeeService;
import services.GoalService;
import services.QuestionService;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Controller for first performance rating step
 */
public class PerformanceRatingStepOneController extends PerformanceRatingStepBaseController {

    protected EmployeeService employeeService;

    protected List<EmployeeDO> availabeEmployees;

    @FXML
    protected ComboBox employeeDropdown;

    @FXML
    private void initialize() {
        EntityManager entityManager = getEntityManager();
        this.goalService = new GoalService(entityManager);
        this.questionService = new QuestionService(entityManager);
        this.employeeService = new EmployeeService(entityManager);
        this.availabeEmployees = employeeService.findByUser(loggedInUser);
        addAvailableEmployeesToDropdown(availabeEmployees);
    }

    /**
     * Adds a goal to the performance rating
     *
     * @param actionEvent
     */
    @FXML
    public void addGoal(ActionEvent actionEvent) {
        if (employee == null) {
            missingEmployeeBox();
            return;
        }
        try {
            GoalDO goal = goalService.createNewGoal(performanceRating, "Your goal", "Describe your goal here");
            addGoalElement(goal);
            goals.add(goal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a question to the performance rating
     *
     * @param actionEvent
     */
    @FXML
    public void addQuestion(ActionEvent actionEvent) {
        if (employee == null) {
            missingEmployeeBox();
            return;
        }
        try {
            QuestionDO question = questionService.createNewQuestion(performanceRating, "Your question", "Describe your question here");
            addQuestionElement(question);
            questions.add(question);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This Method sets a performance rating with all its questions and goals.
     *
     * @param performanceRating performance rating object
     */
    public void setPerformanceRating(PerformanceRatingDO performanceRating) {
        this.performanceRating = performanceRating;
        // add goals
        goals = this.goalService.findByPerformenceRatingId(performanceRating.getPerformanceRatingId());
        try {
            for (GoalDO goal : goals) {
                addGoalElement(goal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // add questions
        questions = this.questionService.findByPerformenceRatingId(performanceRating.getPerformanceRatingId());
        try {
            for (QuestionDO question : questions) {
                addQuestionElement(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method adds a goal element to the UI.
     *
     * @param goal goal object
     * @throws IOException
     */
    private void addGoalElement(GoalDO goal) throws IOException {
        //check file path where the file should be
        URL fxmlUrl = getClass().getResource(ViewManager.getViews().get(ViewManager.View.PERFORMANCE_RATING_STEP_ONE_GOAL_ROW));

        //Build fxml files with gradle build when fxml are moved

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

        GoalController goalController = loader.getController();
        goalController.setGoal(goal);

        Scene scene = new Scene(root);
        // Add the subview to the VBox

        goalVBox.getChildren().add(scene.getRoot());
        //Set focus on scroll pane. Workaround for fxml-bug
        goalScrollPane.requestFocus();
    }

    /**
     * This method adds a question element to the UI.
     *
     * @param question question obeject
     * @throws IOException
     */
    private void addQuestionElement(QuestionDO question) throws IOException {
        //check file path where the file should be
        URL fxmlUrl = getClass().getResource(ViewManager.getViews().get(ViewManager.View.PERFORMANCE_RATING_STEP_ONE_QUESTION_ROW));

        //Build fxml files with gradle build when fxml are moved

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

        QuestionController questionController = loader.getController();
        questionController.setQuestion(question);
        questionController.setPerformanceRatingDO(performanceRating);

        Scene scene = new Scene(root);
        // Add the subview to the VBox
        questionVBox.getChildren().add(scene.getRoot());
        //Set focus on scroll pane. Workaround for fxml-bug
        questionScrollPane.requestFocus();
    }

    /**
     * Adds the employees of the current employeer to dropdown
     *
     * @param availabeEmployees available employees of the logged in user
     */
    protected void addAvailableEmployeesToDropdown(List<EmployeeDO> availabeEmployees) {
        for (EmployeeDO employee : availabeEmployees) {
            employeeDropdown.getItems().add(employee);
        }
    }

    /**
     * This method sets all employee information.
     *
     * @param employee
     */
    public void setEmployeeObject(EmployeeDO employee) {
        if (employee != null) {
            nameLabel.setText(employee.getFullName());
            birthdateLabel.setText(employee.getBirthdate());
            teamLabel.setText(employee.getTeamName());
            socialLabel.setText(employee.getSocialSecurityNumber());
            employeeDropdown.setValue(employee);

            setEmployee(employee);
        }
    }

    /**
     * Setter for employee.
     *
     * @param employee employee object
     */
    public void setEmployee(EmployeeDO employee) {
        this.employee = employee;
    }

    /**
     * Sets the employee of the current performance rating
     */
    @FXML
    public void setEmployeeByDropdown() {
        EmployeeDO selectedEmployee = (EmployeeDO) employeeDropdown.getSelectionModel().getSelectedItem();
        setEmployeeObject(selectedEmployee);
        try {
            performanceRating = performanceRatingService.createNew(loggedInUser, selectedEmployee.getEmployeeId());
        } catch (SaveFailedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Message box with user warning that employee is not set
     */
    public void missingEmployeeBox() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Missing Employee");
        alert.setHeaderText("Please select employee first");
        alert.showAndWait();
    }
}
