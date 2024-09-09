package persistence.dao;

import persistence.PerformanceRatingDO;
import persistence.EmployeeDO;
import persistence.UserDO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.logging.Logger;

/**
 * DAO for a performance rating
 */
public class PerformanceRatingDAO extends BaseDAO<PerformanceRatingDO> {
    private static final Logger LOGGER = Logger.getLogger(PerformanceRatingDAO.class.getName());

    /**
     * @param entityManager
     */
    public PerformanceRatingDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Find performance rating by id in db
     * @param id
     * @return found performance rating
     */
    public PerformanceRatingDO findById(int id) {
        return entityManager.find(PerformanceRatingDO.class, id);
    }

    /**
     * Find all performance ratings for an employer
     * @param employer the employer to search for
     * @return all ratings for the employer
     */
    public List<PerformanceRatingDO> findByEmployer(UserDO employer)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PerformanceRatingDO> criteriaQuery = criteriaBuilder.createQuery(PerformanceRatingDO.class);
        Root<PerformanceRatingDO> root = criteriaQuery.from(PerformanceRatingDO.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("userDo"), employer));
        TypedQuery<PerformanceRatingDO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * Find all performance ratings for an employee
     * @param employeeDo the employee to search for
     * @return all ratings belonging to the employee, no matter the employer
     */
    public List<PerformanceRatingDO> findByEmployee(EmployeeDO employeeDo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PerformanceRatingDO> criteriaQuery = criteriaBuilder.createQuery(PerformanceRatingDO.class);
        Root<PerformanceRatingDO> root = criteriaQuery.from(PerformanceRatingDO.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("employeeDo"), employeeDo));
        TypedQuery<PerformanceRatingDO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
