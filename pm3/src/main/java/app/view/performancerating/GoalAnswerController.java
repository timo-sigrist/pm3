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
import persistence.GoalDO;
import persistence.exceptions.DeleteFailedException;
import persistence.exceptions.UpdateFailedException;
import services.GoalService;

/**
 * Controler for a goal
 */
public class GoalAnswerController extends BaseController {

    @FXML
    private AnchorPane anchorGoal;

    @FXML
    private Label goalTitleLabel;

    @FXML
    private Label goalDescriptionLabel;

    @FXML
    private TextArea goalAnswerField;

    private final GoalService goalService;
    private GoalDO goal;

    public GoalAnswerController() {
        goalService = new GoalService(getEntityManager());
    }

    /**
     * initializes the view
     */
    @FXML
    private void initialize() {
        //Todo initialize listener
    }

    /**
     * This method sets textfields to the actual goal content.
     *
     * @param goal
     */
    public void setGoal(GoalDO goal) {
        this.goal = goal;
        goalTitleLabel.setText(goal.getTitle());
        goalDescriptionLabel.setText(goal.getDescription());
    }

    /**
     * This Method updates the actual goal.
     */
    private void updateGoal() {
        //Todo to be implemented
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