package app.view.questioncatalog;

import app.controller.BaseController;
import persistence.QuestionCatalogDO;
import app.view.ViewManager;
import app.view.WindowController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import persistence.exceptions.DeleteFailedException;
import persistence.exceptions.SaveFailedException;
import persistence.exceptions.UpdateFailedException;
import services.QuestionCatalogService;

import java.util.Arrays;
import java.util.logging.Logger;

import java.util.logging.Level;

/**
 * Controller for the question catalog tab
 */
public class QuestionCatalogTabController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(QuestionCatalogTabController.class.getName());

    private final QuestionCatalogService questionCatalogService = new QuestionCatalogService(getEntityManager());
    ObservableList<QuestionCatalogDO> questionCatalogTableData = FXCollections.observableArrayList();
    TableColumn<QuestionCatalogDO, Long> idColumn = new TableColumn<>("ID");
    TableColumn<QuestionCatalogDO, String> nameColumn = new TableColumn<>("Name");

    @FXML
    private TableView<QuestionCatalogDO> questionCatalogTableview;

    /**
     * Initializes the view
     */
    @FXML
    public void initialize() {
        initQuestionCatalogTable();
    }

    private void initQuestionCatalogTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        nameColumn.setPrefWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        questionCatalogTableview.getColumns().addAll(Arrays.asList(idColumn, nameColumn));

        questionCatalogTableview.setItems(questionCatalogTableData);

        reloadData();
    }

    /**
     * Reloads data from db and sets it in table view
     */
    public void reloadData() {
        //save changes to questionTemplateDo within userDO
        questionCatalogTableData.clear();
        questionCatalogTableData.addAll(questionCatalogService.findByUser(loggedInUser));
        questionCatalogTableview.refresh();
    }

    @FXML
    private void addQuestionCatalog() {
        WindowController window = new WindowController(ViewManager.View.QUESTION_CATALOG_MODAL, "Question Catalogs");
        QuestionCatalogDO questionCatalogDO = new QuestionCatalogDO(loggedInUser);
        QuestionCatalogModalController questionCatalogModalController = window.getLoader().getController();

        // create catalog before opening modal to be able to add question templates on top of it
        try {
            questionCatalogService.createNewQuestionCatalog(questionCatalogDO);
            questionCatalogModalController.setQuestionCatalogDO(questionCatalogDO);

            window.getStage().showAndWait();
        } catch (SaveFailedException e) {
            // TODO: handle exception

            LOGGER.log(Level.SEVERE, String.format( "Failed to create new question catalog, %s", e));
        }

        reloadData();
    }

    @FXML
    private void editQuestionCatalog() {
        WindowController window = new WindowController(ViewManager.View.QUESTION_CATALOG_MODAL, "Question Catalogs");
        QuestionCatalogDO questionCatalogDO = questionCatalogTableview.getSelectionModel().getSelectedItem();
        ((QuestionCatalogModalController) window.getLoader().getController()).setQuestionCatalogDO(questionCatalogDO);

        window.getStage().showAndWait();

        try {
            questionCatalogService.updateQuestionCatalog(questionCatalogDO);
        } catch (UpdateFailedException e) {
            // TODO: handle exception
        }

        reloadData();
    }

    @FXML
    private void delQuestionCatalog() {
        QuestionCatalogDO questionCatalogDO = questionCatalogTableview.getSelectionModel().getSelectedItem();

        try {
            questionCatalogService.deleteQuestionCatalog(questionCatalogDO);
        } catch (DeleteFailedException e) {
            // TODO: handle exception
        }

        reloadData();
    }
}
