package app.view.performancerating.ratingprocess;

import app.controller.BaseController;
import app.view.ViewManager;
import app.view.performancerating.GoalAnswerController;
import app.view.performancerating.GoalController;
import app.view.performancerating.QuestionAnswerController;
import app.view.performancerating.QuestionController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import persistence.EmployeeDO;
import persistence.GoalDO;
import persistence.PerformanceRatingDO;
import persistence.QuestionDO;
import services.EmployeeService;
import services.GoalService;
import services.QuestionService;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URL;

/**
 * Controller for second performance rating step
 */
public class PerformanceRatingStepTwoController extends PerformanceRatingStepBaseController {
    private PerformanceRatingDO performanceRating;

    @FXML
    private void initialize() {
        EntityManager entityManager = getEntityManager();
        this.goalService = new GoalService(entityManager);
        this.questionService = new QuestionService(entityManager);
    }

    /**
     * Setter for performance rating.
     *
     * @param performanceRating performance rating object.
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
        URL fxmlUrl = getClass().getResource(ViewManager.getViews().get(ViewManager.View.PERFORMANCE_RATING_STEP_TWO_GOAL_ANSWER_ROW));

        //Build fxml files with gradle build when fxml are moved

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

        GoalAnswerController goalAnswerController = loader.getController();
        goalAnswerController.setGoal(goal);

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
        URL fxmlUrl = getClass().getResource(ViewManager.getViews().get(ViewManager.View.PERFORMANCE_RATING_STEP_TWO_QUESTION_ANSWER_ROW));

        //Build fxml files with gradle build when fxml are moved

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

        QuestionAnswerController questionAnswerController = loader.getController();
        questionAnswerController.setQuestion(question);
        questionAnswerController.setPerformanceRatingDO(performanceRating);

        Scene scene = new Scene(root);
        // Add the subview to the VBox
        questionVBox.getChildren().add(scene.getRoot());
        //Set focus on scroll pane. Workaround for fxml-bug
        questionScrollPane.requestFocus();
    }
}
