package app.view.performancerating.ratingprocess;

import app.view.ViewManager;
import app.view.performancerating.AnswerComparisonController;
import app.view.performancerating.QuestionController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import persistence.GoalDO;
import persistence.PerformanceRatingDO;
import persistence.QuestionDO;
import services.EmployeeService;
import services.PerformanceRatingService;
import services.QuestionService;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URL;

/**
 * Controller for third performance rating step
 */
public class PerformanceRatingStepThreeController extends PerformanceRatingStepBaseController {
    @FXML
    private VBox questionVBox;

    @FXML
    private ScrollPane questionScrollPane;

    /**
     * Initialzies the view and loads the required services
     */
    @FXML
    private void initialize() {
        EntityManager entityManager = getEntityManager();
        this.questionService = new QuestionService(entityManager);
        this.performanceRatingService = new PerformanceRatingService(entityManager);

    }

    /**
     * This Method sets a performance rating with all its questions and goals.
     *
     * @param performanceRating performance rating object
     */
    public void setPerformanceRating(PerformanceRatingDO performanceRating) {
        this.performanceRating = performanceRating;

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
     * This method adds a question element to the UI.
     *
     * @param question question obeject
     * @throws IOException
     */
    private void addQuestionElement(QuestionDO question) throws IOException {
        //check file path where the file should be
        URL fxmlUrl = getClass().getResource(ViewManager.getViews().get(ViewManager.View.PERFORMANCE_RATING_STEP_THREE_COMPARISON_ROW));

        //Build fxml files with gradle build when fxml are moved

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

        AnswerComparisonController answerComparisonController = loader.getController();
        answerComparisonController.setQuestion(question);
        answerComparisonController.setPerformanceRatingDO(performanceRating);

        Scene scene = new Scene(root);
        // Add the subview to the VBox
        questionVBox.getChildren().add(scene.getRoot());
        //Set focus on scroll pane. Workaround for fxml-bug
        questionScrollPane.requestFocus();
    }
}
