package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class GoalPositionDOTest {

    @Test
    void Test_performanceRating()
    {
        PerformanceRatingDO rating = new PerformanceRatingDO();

        GoalPositionDO goalPosition = new GoalPositionDO();
        goalPosition.setPerformanceRatingDo(rating);

        assertEquals(rating, goalPosition.getPerformanceRatingDo());   
    }

    @Test
    void Test_goal()
    {
        GoalDO goal = new GoalDO();

        GoalPositionDO goalPosition = new GoalPositionDO();
        goalPosition.setGoalDo(goal);

        assertEquals(goal, goalPosition.getGoalDo());   
    }
}
