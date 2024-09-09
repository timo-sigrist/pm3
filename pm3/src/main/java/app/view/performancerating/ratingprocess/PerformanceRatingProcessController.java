package app.view.performancerating.ratingprocess;

import app.controller.BaseController;
import app.view.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import persistence.ActiveStep;
import persistence.EmployeeDO;
import persistence.PerformanceRatingDO;
import services.PerformanceRatingService;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller for the performance rating process
 */
public class PerformanceRatingProcessController extends BaseController {

    private PerformanceRatingDO performanceRating;
    private EmployeeDO employee;
    private ActiveStep activeStep;

    /**
     * Holder of a switchable view.
     */
    @FXML
    private VBox vBox;

    @FXML
    private AnchorPane subview;

    @FXML
    private HBox processViewHbox;

    @FXML
    private Button closeButton;

    @FXML
    private Button finishButton;

    private PerformanceRatingStepOneController performanceRatingStepOneController;
    private PerformanceRatingStepTwoController performanceRatingStepTwoController;
    private PerformanceRatingStepThreeController performanceRatingStepThreeController;
    private Parent activeSubView;

    private PerformanceRatingService performanceRatingService;

    /**
     * initializes the view
     *
     * @throws IOException throws IOExceptions when fxml load error occures
     */
    @FXML
    private void initialize() throws IOException {
        initAndOpenStepOne();
        performanceRatingService = new PerformanceRatingService(getEntityManager());
    }

    /**
     * Setter for performance rating.
     *
     * @param performanceRating performance rating object.
     */
    public void setPerformanceRating(PerformanceRatingDO performanceRating) {
        this.performanceRating = performanceRating;
        performanceRatingStepOneController.setPerformanceRating(performanceRating);
    }

    /**
     * Setter for employee.
     *
     * @param employeeDO employee object.
     */
    public void setEmployee(EmployeeDO employeeDO) {
        employee = employeeDO;
        performanceRatingStepOneController.setEmployeeObject(employee);
    }

    /**
     * Closes current window
     */
    @FXML
    public void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Finishes this step and opens the next
     */
    @FXML
    public void finishStep() throws IOException {
        switch (activeStep){
            case STEP_ONE -> initAndOpenStepTwo();
            case STEP_TWO -> initAndOpenStepThree();
            case STEP_THREE -> closeWindow();
        }
    }

    private void initAndOpenStepOne() throws IOException {
        activeStep = ActiveStep.STEP_ONE;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewManager.getViews().get(ViewManager.View.PERFORMANCE_RATING_STEP_ONE)));
        Parent newSubView = fxmlLoader.load();

        performanceRatingStepOneController = fxmlLoader.getController();
        int index = vBox.getChildren().indexOf(subview);
        initStep(newSubView, index);
    }

    private void initAndOpenStepTwo() throws IOException {
        if (performanceRatingService.updateStatus(performanceRating, ActiveStep.STEP_TWO)) {
            activeStep = ActiveStep.STEP_TWO;
            UpdateProcessViewHBox(2);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewManager.getViews().get(ViewManager.View.PERFORMANCE_RATING_STEP_TWO)));
            Parent newSubView = fxmlLoader.load();

            performanceRatingStepTwoController = fxmlLoader.getController();
            performanceRatingStepTwoController.setPerformanceRating(performanceRating);

            int index = vBox.getChildren().indexOf(activeSubView);
            initStep(newSubView, index);
        }
    }

    private void initAndOpenStepThree() throws IOException {
        activeStep = ActiveStep.STEP_THREE;
        UpdateProcessViewHBox(4);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewManager.getViews().get(ViewManager.View.PERFORMANCE_RATING_STEP_THREE)));
        Parent newSubView = fxmlLoader.load();

        performanceRatingStepThreeController = fxmlLoader.getController();
        int index = vBox.getChildren().indexOf(activeSubView);
        initStep(newSubView, index);
    }

    private void initStep(Parent newSubView, int index) {
        vBox.getChildren().set(index, newSubView);
        activeSubView = newSubView;
    }

    private void UpdateProcessViewHBox(int startIndex){
        Circle circle = (Circle) processViewHbox.getChildren().get(startIndex);
        circle.setFill(Color.rgb(221,235, 255, 1));
        ImageView imageView = (ImageView) processViewHbox.getChildren().get(startIndex + 1);
        imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/app/view/arrow_blue.png"))));
    }
}
