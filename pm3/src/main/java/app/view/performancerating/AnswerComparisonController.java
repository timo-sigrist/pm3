package app.view.performancerating;

import app.controller.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import persistence.PerformanceRatingDO;
import persistence.QuestionDO;
import services.QuestionService;

public class AnswerComparisonController extends BaseController {
    @FXML
    private AnchorPane anchorAnswer;

    @FXML
    private TextField questionTitleField;

    @FXML
    private TextArea employerAnswerField;

    @FXML
    private TextArea employeeAnswerField;

    private final QuestionService questionService;
    private QuestionDO question;
    private PerformanceRatingDO performanceRatingDO;

    public AnswerComparisonController() {
        questionService = new QuestionService(getEntityManager());
    }

    /**
     * intializes the view and loads listeners
     */
    @FXML
    private void initialize() {
        questionTitleField.setEditable(false);
        employerAnswerField.setEditable(false);
        employeeAnswerField.setEditable(false);
    }

    /**
     * Setter for Question
     *
     * @param question object
     */
    public void setQuestion(QuestionDO question) {
        this.question = question;

        questionTitleField.setText(question.getQuestionTitle());
        employerAnswerField.setText(question.getAnswerEmployer());
        employeeAnswerField.setText(question.getAnswerEmployee());
    }

    /**
     * Setter for Performance rating
     *
     * @param performanceRatingDO object
     */
    public void setPerformanceRatingDO(PerformanceRatingDO performanceRatingDO) {
        this.performanceRatingDO = performanceRatingDO;
    }
}
