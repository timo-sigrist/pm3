package app.view.questioncatalog;

import app.controller.BaseController;
import persistence.QuestionTemplateDO;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for question template modal
 */
public class QuestionTemplateModalController extends BaseController {

    QuestionTemplateDO questionTemplateDO;

    @FXML
    private TextField titleField;

    @FXML
    private TextArea textField;

    @FXML
    private VBox fieldsContainer;

    /**
     * Creates a new field group
     */
    @FXML
    private void addField() {
        // Create a new field group
        VBox fieldGroup = new VBox(5);
        fieldGroup.setPadding(new Insets(5));
        TextField fieldTitle = new TextField();
        fieldTitle.setPromptText("Enter field title...");
        TextField fieldText = new TextField();
        fieldText.setPromptText("Enter field text...");
        fieldGroup.getChildren().addAll(fieldTitle, fieldText);

        // Add the field group to the fields container
        fieldsContainer.getChildren().add(fieldGroup);
    }

    /**
     * Initializes view be given question template
     * @param questionTemplateDO
     */
    public void setQuestionTemplateDO(QuestionTemplateDO questionTemplateDO) {
        this.questionTemplateDO = questionTemplateDO;
        titleField.setText(questionTemplateDO.getTitle());
        textField.setText(questionTemplateDO.getText());
    }

    /**
     * Closes window
     */
    @FXML
    private void discard() {
        // Implement discard functionality here
        ((Stage) titleField.getScene().getWindow()).close();
    }

    /**
     * Saves question templates to database
     */
    @FXML
    private void save() {
        questionTemplateDO.setTitle(titleField.getText());
        questionTemplateDO.setText(textField.getText());
        questionTemplateDO.setUserDo(loggedInUser);

        discard();
    }

}