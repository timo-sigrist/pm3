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
import persistence.GoalDO;
import persistence.exceptions.DeleteFailedException;
import persistence.exceptions.UpdateFailedException;
import services.GoalService;

/**
 * Controler for a goal
 */
public class GoalController extends BaseController {

    @FXML
    private AnchorPane anchorGoal;

    @FXML
    private TextField goalTitleField;

    @FXML
    private TextArea goalDescriptionField;

    @FXML
    private Button deleteButton;

    private final GoalService goalService;
    private GoalDO goal;

    public GoalController() {
        goalService = new GoalService(getEntityManager());
    }

    /**
     * initializes the view
     */
    @FXML
    private void initialize() {
        goalTitleField.focusedProperty().addListener(new GoalFocusListener());
        goalDescriptionField.focusedProperty().addListener(new GoalFocusListener());
    }

    /**
     * Deletes a goal
     *
     * @param event event triggered
     */
    @FXML
    private void deleteGoal(ActionEvent event) {
        try {
            goalService.deleteGoal(goal);
            VBox vBox = (VBox) anchorGoal.getParent();
            vBox.getChildren().remove(deleteButton.getParent());
        } catch (DeleteFailedException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method sets textfields to the actual goal content.
     *
     * @param goal
     */
    public void setGoal(GoalDO goal) {
        this.goal = goal;
        goalTitleField.setText(goal.getTitle());
        goalDescriptionField.setText(goal.getDescription());
    }

    /**
     * This Method updates the actual goal.
     */
    private void updateGoal() {
        goal.setTitle(goalTitleField.getText());
        goal.setDescription(goalDescriptionField.getText());
        try {
            goalService.updateGoal(goal);
        } catch (UpdateFailedException e) {
            e.printStackTrace();
        }
    }


    /**
     * This focus listener is triggered, when a textfield looses focus.
     */
    private class GoalFocusListener implements ChangeListener<Boolean> {

        @Override
        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean focusGain) {
            if (focusGain) {
                // on focus
            } else {
                //Focus out
                updateGoal();
            }
        }
    }

}