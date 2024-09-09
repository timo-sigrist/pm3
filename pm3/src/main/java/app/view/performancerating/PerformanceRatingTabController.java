package app.view.performancerating;

import app.controller.BaseController;
import app.view.ViewManager;
import app.view.WindowController;
import app.view.performancerating.ratingprocess.PerformanceRatingProcessController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import persistence.EmployeeDO;
import persistence.PerformanceRatingDO;
import services.PerformanceRatingService;

import java.util.List;

/**
 * Controller for performance rating tab
 */
public class PerformanceRatingTabController extends BaseController {
    private final PerformanceRatingService performanceRatingService;
    private ObservableList<PerformanceRatingDO> performanceRatingTableData;

    private final TableColumn<PerformanceRatingDO, Integer> idColumn = new TableColumn<>("ID");
    private final TableColumn<PerformanceRatingDO, String> userColumn = new TableColumn<>("User");
    private final TableColumn<PerformanceRatingDO, String> employeeColumn = new TableColumn<>("Employee");
    private final TableColumn<PerformanceRatingDO, String> commentColumn = new TableColumn<>("Kommentar");
    private final TableColumn<PerformanceRatingDO, String> statusColumn = new TableColumn<>("Status");


    public PerformanceRatingTabController() {
        performanceRatingService = new PerformanceRatingService(getEntityManager());
    }

    @FXML
    private TableView<PerformanceRatingDO> performanceRatingTableView;

    /**
     * Initializes view
     */
    @FXML
    public void initialize() {
        initPerformanceRatingTable();
    }

    private void initPerformanceRatingTable() {


        performanceRatingTableData = FXCollections.observableArrayList();

        idColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getPerformanceRatingId()).asObject());

        commentColumn.setPrefWidth(150);
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

        userColumn.setPrefWidth(150);
        userColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getUserDo().getFullName()));

        employeeColumn.setPrefWidth(150);
        employeeColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getEmployeeDo() != null) {
                return new SimpleStringProperty(cellData.getValue().getEmployeeDo().getFullName());
            } else {
                return new SimpleStringProperty("placeholder");
            }
        });

        statusColumn.setPrefWidth(50);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        performanceRatingTableView.getColumns().addAll(idColumn, userColumn, employeeColumn, commentColumn, statusColumn);

        performanceRatingTableView.setItems(performanceRatingTableData);

        reloadData();
    }

    /**
     * Reloads data in table from database
     */
    public void reloadData() {
        performanceRatingTableData.clear();

        List<PerformanceRatingDO> performanceRatingDOList = performanceRatingService.findByEmployer(loggedInUser);
        performanceRatingTableData.addAll(performanceRatingDOList);
        performanceRatingTableView.refresh();
    }

    /**
     * Creates a performance rating for logged in user
     */
    @FXML
    private void addPerformanceRating() {
        PerformanceRatingDO performanceRating = new PerformanceRatingDO();
        openPerformanceRating(performanceRating);
        reloadData();
    }

    /**
     * Opens performance rating view
     */
    @FXML
    private void editPerformanceRating() {
        PerformanceRatingDO performanceRating = performanceRatingTableView.getSelectionModel().getSelectedItem();
        if (performanceRating != null) {
            openPerformanceRating(performanceRating);
        }
    }

    private void openPerformanceRating(PerformanceRatingDO performanceRating) {
        WindowController window = new WindowController(ViewManager.View.PERFORMANCE_RATING_OVERVIEW, "Overview");
        PerformanceRatingProcessController performanceRatingOverviewController = window.getLoader().getController();

        if (performanceRating.getEmployeeDo() != null) {
            EmployeeDO employee = performanceRating.getEmployeeDo();
            performanceRatingOverviewController.setEmployee(employee);
        }

        performanceRatingOverviewController.setPerformanceRating(performanceRating);

        window.getStage().showAndWait();
    }

    /**
     * Opens performance rating answers view
     */
    @FXML
    private void answerQuestions() {
        WindowController window = new WindowController(ViewManager.View.PERFORMANCE_RATING_ANSWER, "Fill out performance review");
        PerformanceRatingAnswerController controller = (PerformanceRatingAnswerController) window.getController();


        List<PerformanceRatingDO> performanceRatings = performanceRatingService.findByEmployer(loggedInUser);
        controller.initView(performanceRatings.get(0));

        Stage stage = window.getStage();
        stage.showAndWait();
    }
}
