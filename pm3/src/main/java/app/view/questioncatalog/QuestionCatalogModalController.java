package app.view.questioncatalog;

import app.controller.BaseController;
import javafx.stage.Stage;
import persistence.exceptions.DeleteFailedException;
import persistence.exceptions.SaveFailedException;
import persistence.exceptions.UpdateFailedException;
import services.QuestionCatalogService;
import services.QuestionTemplateService;
import persistence.QuestionTemplateDO;
import persistence.QuestionCatalogDO;
import app.view.ViewManager;
import app.view.WindowController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Arrays;

/**
 * Controller for question catalog tab view
 */
public class QuestionCatalogModalController extends BaseController {
    private final QuestionCatalogService questionCatalogService;
    private final QuestionTemplateService questionTemplateService;
    private final ObservableList<QuestionTemplateDO> questionTemplateTableData;
    private final TableColumn<QuestionTemplateDO, Long> idColumn;
    private final TableColumn<QuestionTemplateDO, String> titleColumn;
    private final TableColumn<QuestionTemplateDO, String> textColumn;
    private QuestionCatalogDO questionCatalogDO;

    public QuestionCatalogModalController() {
        questionCatalogService = new QuestionCatalogService(getEntityManager());
        questionTemplateService = new QuestionTemplateService(getEntityManager());
        questionTemplateTableData = FXCollections.observableArrayList();
        idColumn = new TableColumn<>("ID");
        titleColumn = new TableColumn<>("Titel");
        textColumn = new TableColumn<>("Frage");
    }

    @FXML
    private TextField questionCatalogNameInput;

    @FXML
    private TableView<QuestionTemplateDO> questionTemplateTableview;

    /**
     * Initializes view
     */
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        titleColumn.setPrefWidth(150);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        textColumn.setPrefWidth(300);
        textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));

        questionTemplateTableview.getColumns().addAll(Arrays.asList(idColumn, titleColumn, textColumn));
        questionTemplateTableview.setItems(questionTemplateTableData);
    }

    public void setQuestionCatalogDO(QuestionCatalogDO questionCatalogDO) {
        this.questionCatalogDO = questionCatalogDO;
        questionCatalogNameInput.setText(questionCatalogDO.getName());

        reloadData();
    }

    /**
     * Reloads data in table from database
     */
    public void reloadData() {
        questionTemplateTableData.clear();

        questionTemplateTableData.addAll(questionTemplateService.findByCatalog(questionCatalogDO));
        questionTemplateTableview.refresh();
    }

    /**
     * Opens view to add a question template
     */
    @FXML
    private void addQuestionTemplate() {
        WindowController window = new WindowController(ViewManager.View.QUESTION_TEMPLATE_MODAL, "Question Templates");
        QuestionTemplateDO questionTemplateDO = new QuestionTemplateDO(loggedInUser, questionCatalogDO);
        QuestionTemplateModalController questionTemplateModalController = window.getLoader().getController();
        questionTemplateModalController.setQuestionTemplateDO(questionTemplateDO);

        window.getStage().showAndWait();

        try {
            questionTemplateService.createNewQuestionTemplate(questionTemplateDO);
        } catch (SaveFailedException e) {
            // TODO: handle exception
        }

        reloadData();
    }

    /**
     * Opens view to edit a question template
     */
    @FXML
    private void editQuestionTemplate() {
        WindowController window = new WindowController(ViewManager.View.QUESTION_TEMPLATE_MODAL, "Question Templates");
        QuestionTemplateDO questionTemplate = questionTemplateTableview.getSelectionModel().getSelectedItem();
        ((QuestionTemplateModalController) window.getLoader().getController()).setQuestionTemplateDO(questionTemplate);

        window.getStage().showAndWait();

        try {
            questionTemplateService.updateQuestionTemplate(questionTemplate);
            reloadData();
        } catch (UpdateFailedException e) {
            // TODO: handle exception
        }
    }

    /**
     * Opens view to delete a question template
     */
    @FXML
    private void delQuestionTemplate() {
        QuestionTemplateDO questionTemplate = questionTemplateTableview.getSelectionModel().getSelectedItem();

        try {
            questionTemplateService.deleteQuestionTemplate(questionTemplate);
            reloadData();
        } catch (DeleteFailedException e) {
            // TODO: handle exception
        }
    }

    /**
     * If the question catalog has no questionTemplates and the name is null, delete it.
     * And close the window.
     */
    @FXML
    private void onCancel() {
        if (questionCatalogDO.getName() == null && questionTemplateTableData.isEmpty()) {
            try {
                questionCatalogService.deleteQuestionCatalog(questionCatalogDO);
            } catch (DeleteFailedException e) {
                // TODO: handle exception
            }
        }

        ((Stage) questionCatalogNameInput.getScene().getWindow()).close();
    }

    @FXML
    private void onSave() {
        questionCatalogDO.setName(questionCatalogNameInput.getText());

        ((Stage) questionCatalogNameInput.getScene().getWindow()).close();
    }
}
