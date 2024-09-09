package services;

import persistence.GoalDO;
import persistence.GoalPositionDO;
import persistence.PerformanceRatingDO;
import persistence.dao.GoalDAO;
import persistence.dao.GoalPositionDAO;
import persistence.exceptions.DeleteFailedException;
import persistence.exceptions.SaveFailedException;
import persistence.exceptions.UpdateFailedException;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Service for all actions regarding a goal
 */
public class GoalService {
    private final GoalDAO goalDAO;
    private final GoalPositionDAO goalPositionDAO;

    /**
     * @param entityManager
     */
    public GoalService(EntityManager entityManager) {
        goalDAO = new GoalDAO(entityManager);
        goalPositionDAO = new GoalPositionDAO(entityManager);
    }

    /**
     * Get goal by id
     *
     * @param id goal to update
     */
    public GoalDO findGoalById(int id) {
        return goalDAO.findByID(id);
    }

    /**
     * Find all goals by performencerating id
     *
     * @param id performancerating id
     * @return list with goal objects
     */
    public List<GoalDO> findByPerformenceRatingId(int id) {
        return goalPositionDAO.findByPerformenceRatingId(id);
    }


    /**
     * Create a new Goal
     *
     * @param performanceRating performancerating object
     * @param goalTitle title of the goal
     * @param goalDescription description of the goal
     * @return created goal
     * @throws SaveFailedException
     */
    public GoalDO createNewGoal(PerformanceRatingDO performanceRating, String goalTitle, String goalDescription) throws SaveFailedException {
        GoalDO goal = new GoalDO(goalTitle, goalDescription, "", "");

        // to get goal-id from autoincrement
        goal = goalDAO.save(goal);
        goalPositionDAO.save(new GoalPositionDO(performanceRating, goal));
        return goal;
    }

    /**
     * Updates a goal
     *
     * @param goal goal to update
     * @throws UpdateFailedException
     */
    public void updateGoal(GoalDO goal) throws UpdateFailedException {
        goalDAO.update(goal);
    }

    /**
     * Deletes a goal
     *
     * @param goal goal to delete
     * @throws DeleteFailedException
     */
    public void deleteGoal(GoalDO goal) throws DeleteFailedException {
        // todo: add transaction
        goalPositionDAO.delete(goalPositionDAO.findByGoal(goal));
        goalDAO.delete(goalDAO.findByID(goal.getId()));
    }


}
