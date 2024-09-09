package persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import persistence.GoalDO;
import persistence.GoalPositionDO;
import persistence.PerformanceRatingDO;

import java.util.List;

/**
 * DAO for a goal position in a performance rating
 */
public class GoalPositionDAO extends BaseDAO<GoalPositionDO> {

    public GoalPositionDAO(EntityManager entityManager)
    {
        super(entityManager);
    }

    /**
     * Find a goal position by its id
     * @param id id to search for
     * @return the position if found
     */
    public GoalPositionDO findById(int id)
    {
        return entityManager.find(GoalPositionDO.class, id);
    }

    /**
     * Searches for all goals from a performencerating.
     * @param id performencerating-id
     * @return List of GoalDos
     */
    public List<GoalDO> findByPerformenceRatingId(int id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GoalDO> criteriaQuery = criteriaBuilder.createQuery(GoalDO.class);

        Root<GoalPositionDO> root = criteriaQuery.from(GoalPositionDO.class);
        Join<GoalPositionDO, GoalDO> goal = root.join("goalDo");
        Join<GoalPositionDO, PerformanceRatingDO> pr = root.join("performanceRatingDo");

        criteriaQuery.select(goal).distinct(true).where(criteriaBuilder.equal(pr.get("performanceRatingId"), id));

        TypedQuery<GoalDO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * Find goalpositon object by goal object
     * @param goaldo goal object
     * @return goalposition object
     */
    public GoalPositionDO findByGoal(GoalDO goaldo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GoalPositionDO> criteriaQuery = criteriaBuilder.createQuery(GoalPositionDO.class);

        Root<GoalPositionDO> root = criteriaQuery.from(GoalPositionDO.class);
        Join<GoalPositionDO, GoalDO> goal = root.join("goalDo");

        criteriaQuery.select(root).where(criteriaBuilder.equal(goal.get("id"), goaldo.getId()));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

}
