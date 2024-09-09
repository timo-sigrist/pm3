package services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.H2Database;
import persistence.GoalDO;
import persistence.GoalPositionDO;
import persistence.PerformanceRatingDO;

class GoalServiceTest
{
    H2Database database;
    GoalService service;

    @BeforeEach
    void setup()
    {
        database = new H2Database();
        service = new GoalService(database.getEntityManager());
    }

    @Test
    void Test_FindByGoalId()
    {
        final String expectedText = "expected Text";
        final int id;

        {
            GoalDO goal = new GoalDO();
            goal.setDescription(expectedText);
            goal = database.save(goal);

            id = goal.getId();
        }

        GoalDO actualGoal = service.findGoalById(id);
        assertEquals(expectedText, actualGoal.getDescription());
    }

    @Test
    void Test_FindByNonExistentGoalId()
    {
        GoalDO actualGoal = service.findGoalById(5);
        assertNull(actualGoal);    
    }

    @Test
    void Test_FindByPerformanceRating_Valid()
    {
        final int ratingId;
        {
            PerformanceRatingDO rating = new PerformanceRatingDO();
            rating = database.save(rating);
            ratingId = rating.getPerformanceRatingId();

            GoalDO goal = new GoalDO();
            goal = database.save(goal);


            GoalPositionDO goalPosition = new GoalPositionDO();
            goalPosition.setGoalDo(goal);
            goalPosition.setPerformanceRatingDo(rating);

            goalPosition = database.save(goalPosition);
        }

        List<GoalDO> goals = service.findByPerformenceRatingId(ratingId);
        assertEquals(1, goals.size());
    }

    @Test
    void Test_FindByPerformanceRating_InValid()
    {

        assertDoesNotThrow(() -> {
            List<GoalDO> goals = service.findByPerformenceRatingId(56);
            assertEquals(0, goals.size());
        });
        
    }

    @Test
    void Test_Delete()
    {
        final int ratingId;
        final GoalDO goal = new GoalDO();
    
        {
            PerformanceRatingDO rating = new PerformanceRatingDO();
            rating = database.save(rating);
            ratingId = rating.getPerformanceRatingId();

            database.save(goal);


            GoalPositionDO goalPosition = new GoalPositionDO();
            goalPosition.setGoalDo(goal);
            goalPosition.setPerformanceRatingDo(rating);

            goalPosition = database.save(goalPosition);


        }

        assertDoesNotThrow(() -> {
            service.deleteGoal(goal);
        });
        
        List<GoalDO> goals = service.findByPerformenceRatingId(ratingId);
        assertEquals(0, goals.size());
    }

}
