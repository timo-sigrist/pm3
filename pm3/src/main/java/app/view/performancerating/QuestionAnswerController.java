package app.view.performancerating;

import app.controller.BaseController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import persistence.PerformanceRatingDO;
import persistence.QuestionDO;
import persistence.exceptions.DeleteFailedException;
import persistence.exceptions.UpdateFailedException;
import services.QuestionService;

/**
 * Controller for manipulating a question
 */
public class QuestionAnswerController extends BaseController {
    @FXML
    private AnchorPane anchorGoal;

    @FXML
    private Label questionTitleLabel;

    @FXML
    private Label questionDescriptionLabel;

    @FXML
    private TextArea questionAnswerField;

    private final QuestionService questionService;
    private QuestionDO question;
    private PerformanceRatingDO performanceRatingDO;

    public QuestionAnswerController() {
        questionService = new QuestionService(getEntityManager());
    }

    /**
     * intializes the view and loads listeners
     */
    @FXML
    private void initialize() {
        //Todo initialize listener
    }

    /**
     * This method updates the actual question
     */
    private void updateQuestion() {
        //Todo to be implemented
    }

    /**
     * Setter for Question
     *
     * @param question object
     */
    public void setQuestion(QuestionDO question) {
        this.question = question;
        questionTitleLabel.setText(question.getQuestionTitle());
        questionDescriptionLabel.setText(question.getQuestionDescription());
    }

    /**
     * Setter for Performance rating
     *
     * @param performanceRatingDO object
     */
    public void setPerformanceRatingDO(PerformanceRatingDO performanceRatingDO) {
        this.performanceRatingDO = performanceRatingDO;
    }


    /**
     * This focus listener is triggered, when a textfield looses focus.
     */
    private class QuestionFocusListener implements ChangeListener<Boolean> {
        @Override
        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean focusGain) {
            if (focusGain) {
                // on focus
            } else {
                //Focus out
                updateQuestion();
            }
        }
    }
}
