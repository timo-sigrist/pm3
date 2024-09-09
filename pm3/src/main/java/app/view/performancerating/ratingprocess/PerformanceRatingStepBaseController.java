package app.view.performancerating.ratingprocess;

import app.controller.BaseController;
import app.view.ViewManager;
import app.view.performancerating.GoalController;
import app.view.performancerating.QuestionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import persistence.EmployeeDO;
import persistence.GoalDO;
import persistence.PerformanceRatingDO;
import persistence.QuestionDO;
import persistence.exceptions.SaveFailedException;
import services.EmployeeService;
import services.GoalService;
import services.PerformanceRatingService;
import services.QuestionService;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class PerformanceRatingStepBaseController extends BaseController {
    /**
     * Holder of a switchable view.
     */
    @FXML
    protected VBox goalVBox;

    @FXML
    protected VBox questionVBox;

    @FXML
    protected ScrollPane goalScrollPane;

    @FXML
    protected ScrollPane questionScrollPane;

    @FXML
    protected Label id;

    @FXML
    protected Label nameLabel;

    @FXML
    protected Label birthdateLabel;

    @FXML
    protected Label teamLabel;

    @FXML
    protected Label socialLabel;

    protected PerformanceRatingDO performanceRating;
    protected EmployeeDO employee;

    protected GoalService goalService;
    protected QuestionService questionService;
    protected PerformanceRatingService performanceRatingService;

    protected List<GoalDO> goals;
    protected List<QuestionDO> questions;

    /**
     * Initialzies the view and loads the required services
     */
    @FXML
    private void initialize() {
        EntityManager entityManager = getEntityManager();
        this.performanceRatingService = new PerformanceRatingService(entityManager);
    }
}
