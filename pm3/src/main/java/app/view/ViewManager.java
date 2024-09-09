package app.view;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Manages all the views
 */
public class ViewManager {

    private static final Map<View, String> views = initMap();

    /**
     * Adds all views to a map
     * @return Map of all the views
     */
    private static Map<View, String> initMap() {
        Map<View, String> map = new EnumMap<>(View.class);
        map.put(View.MAIN_WINDOW, "MainWindow.fxml");
        map.put(View.PERFORMANCE_RATING_ANSWER, "performancerating/PerformanceRatingAnswer.fxml");
        map.put(View.PERFORMANCE_RATING_OVERVIEW, "performancerating/ratingprocess/PerformanceRatingProcess.fxml");
        map.put(View.PERFORMANCE_RATING_STEP_ONE, "PerformanceRatingStepOne.fxml");
        map.put(View.PERFORMANCE_RATING_STEP_TWO, "PerformanceRatingStepTwo.fxml");
        map.put(View.PERFORMANCE_RATING_STEP_THREE, "PerformanceRatingStepThree.fxml");
        map.put(View.PERFORMANCE_RATING_STEP_TWO_GOAL_ANSWER_ROW, "GoalAnswerRow.fxml");
        map.put(View.PERFORMANCE_RATING_STEP_TWO_QUESTION_ANSWER_ROW, "QuestionAnswerRow.fxml");
        map.put(View.PERFORMANCE_RATING_STEP_THREE_COMPARISON_ROW, "AnswerComparisonRow.fxml");
        map.put(View.PERFORMANCE_RATING_STEP_ONE_GOAL_ROW, "GoalRow.fxml");
        map.put(View.PERFORMANCE_RATING_STEP_ONE_QUESTION_ROW, "QuestionRow.fxml");
        map.put(View.LOGIN, "login/LoginWindow.fxml");
        map.put(View.QUESTION_TEMPLATE_MODAL, "questioncatalog/QuestionTemplateModal.fxml");
        map.put(View.QUESTION_CATALOG_MODAL, "questioncatalog/QuestionCatalogModal.fxml");
        map.put(View.CREATE_EMPLOYEE, "employee/EmployeeModal.fxml");

        return Collections.unmodifiableMap(map);
    }

    public static Map<View, String> getViews() {
        return views;
    }

    public enum View {
        MAIN_WINDOW,
        PERFORMANCE_RATING_OVERVIEW,
        PERFORMANCE_RATING_ANSWER,
        PERFORMANCE_RATING_STEP_ONE_GOAL_ROW,
        PERFORMANCE_RATING_STEP_ONE_QUESTION_ROW,
        PERFORMANCE_RATING_STEP_TWO_GOAL_ANSWER_ROW,
        PERFORMANCE_RATING_STEP_TWO_QUESTION_ANSWER_ROW,
        LOGIN,
        PERFORMANCE_RATING_STEP_ONE,
        PERFORMANCE_RATING_STEP_TWO,
        PERFORMANCE_RATING_STEP_THREE,
        PERFORMANCE_RATING_STEP_THREE_COMPARISON_ROW,
        QUESTION_TEMPLATE_MODAL,
        QUESTION_CATALOG_MODAL,
        CREATE_EMPLOYEE
    }

}
