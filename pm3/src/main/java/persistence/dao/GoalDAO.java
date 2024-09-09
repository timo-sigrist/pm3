package persistence.dao;

import java.util.logging.Logger;

import javax.persistence.EntityManager;

import persistence.GoalDO;

/**
 * DAO for a goal
 */
public class GoalDAO extends BaseDAO<GoalDO> {
    
    private static final Logger LOGGER = Logger.getLogger( GoalDAO.class.getName() );

    /**
     * @param entityManager
     */
    public GoalDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Searches for a goal by its id
     * @param goalId the id to search for
     * @return the goal object if it exists
     */
    public GoalDO findByID(int goalId) {
        return entityManager.find(GoalDO.class, goalId);
    }

}
