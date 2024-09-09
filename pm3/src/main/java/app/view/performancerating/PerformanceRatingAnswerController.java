package app.view.performancerating;

import app.controller.BaseController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import persistence.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import persistence.exceptions.UpdateFailedException;
import services.QuestionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for performance rating answer of employee
 */
public class PerformanceRatingAnswerController extends BaseController {
    @FXML
    private VBox dynamicQuestionsVBox;
    @FXML
    private Label employeeNameLbl;
    @FXML
    private Label managerNameLbl;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private GridPane answerGridPane;
    private final QuestionService questionService = new QuestionService(getEntityManager());
    private final List<String> employeeAnswers = new ArrayList<>();
    private List<QuestionDO> questions = new ArrayList<>();

    /**
     * Initializes view
     * @param performanceRatingDO the specific performance rating which questions will be answered
     */
    @FXML
    public void initView(PerformanceRatingDO performanceRatingDO) {

        EmployeeDO employeeDO = performanceRatingDO.getEmployeeDo();
        employeeNameLbl.setText("Mitarbeiter: " + employeeDO.getFullName());

        UserDO userDO = performanceRatingDO.getUserDo();
        managerNameLbl.setText("Vorgesetzter: " + userDO.getFirstname() + " " + userDO.getLastname());

        questions  = questionService.findByPerformenceRatingId(performanceRatingDO.getPerformanceRatingId());

        answerGridPane.setHgap(30);
        answerGridPane.setVgap(15);
        for (int i = 0; i < questions.size(); i++) {
            Label questionTextLabel = new Label(questions.get(i).getQuestionDescription());
            answerGridPane.add(questionTextLabel, 0, i);

            TextArea answerTextField = new TextArea();
            answerTextField.focusedProperty().addListener(new PerformanceRatingAnswerController.AnswerFocusListener());
            answerGridPane.add(answerTextField, 1, i);
        }
        dynamicQuestionsVBox.getChildren().add(answerGridPane);
    }

    /**
     * saves answer of employee in a list of answers
     */
    // TODO: refactor, implement sub view for question-answer (eg. like GoalRow)
    private void updateAnswers(){
        int row = 0;
        for (Node node : answerGridPane.getChildren()) {
            if (node instanceof TextArea) {
                employeeAnswers.set(row, ((TextArea) node).getText());
            }
            row++;
        }
    }

    /**
     * saves employeeAnswers to according questions to database
     */
    @FXML
    public void saveAnswers() {

        for (int i = 0; i < questions.size(); i++) {
            QuestionDO question = questions.get(i);
            String answer = employeeAnswers.get(i);

            try {
                question.setAnswerEmployee(answer);
                questionService.updateQuestion(question);
            } catch (UpdateFailedException e) {
            e.printStackTrace();
            }
        }
    }

    /**
     * This focus listener is triggered, when a textfield looses focus.
     */
    private class AnswerFocusListener implements ChangeListener<Boolean> {

        @Override
        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean focusGain) {
            if (focusGain) {
                // on focus
            } else {
                //Focus out
                updateAnswers();
            }
        }
    }
}
