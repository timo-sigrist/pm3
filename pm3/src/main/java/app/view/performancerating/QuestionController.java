package app.view.performancerating;

import app.controller.BaseController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
public class QuestionController extends BaseController {
    @FXML
    private AnchorPane anchorQuestion;

    @FXML
    private TextField questionTitleField;

    @FXML
    private TextArea questionDescriptionField;

    @FXML
    private Button deleteButton;

    private final QuestionService questionService;
    private QuestionDO question;
    private PerformanceRatingDO performanceRatingDO;

    public QuestionController() {
        questionService = new QuestionService(getEntityManager());
    }

    /**
     * intializes the view and loads listeners
     */
    @FXML
    private void initialize() {
        questionTitleField.focusedProperty().addListener(new QuestionFocusListener());
        questionDescriptionField.focusedProperty().addListener(new QuestionFocusListener());
    }

    /**
     * Delete a question
     *
     * @param event event triggered
     */
    @FXML
    private void deleteQuestion(ActionEvent event) {
        try {
            questionService.deleteQuestion(question, performanceRatingDO);
            VBox vBox = (VBox) anchorQuestion.getParent();
            vBox.getChildren().remove(deleteButton.getParent());
        } catch (DeleteFailedException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method updates the actual question
     */
    private void updateQuestion() {
        question.setQuestionTitle(questionTitleField.getText());
        question.setQuestionDescription(questionDescriptionField.getText());
        try {
            questionService.updateQuestion(question);
        } catch (UpdateFailedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setter for Question
     *
     * @param question object
     */
    public void setQuestion(QuestionDO question) {
        this.question = question;
        questionTitleField.setText(question.getQuestionTitle());
        questionDescriptionField.setText(question.getQuestionDescription());

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
