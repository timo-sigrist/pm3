package persistence.dao;

import java.util.logging.Logger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import persistence.EmployeeDO;
import persistence.UserDO;

/**
 * DAO for an employee
 */
public class EmployeeDAO extends BaseDAO<EmployeeDO> {
    
    private static final Logger LOGGER = Logger.getLogger(EmployeeDAO.class.getName());
    public EmployeeDAO(EntityManager entityManager)
    {
        super(entityManager);
    }

    /**
     * Searches for a employee by its id
     * @param employeeId the id to search for
     * @return the employee object if it exists
     */
    public EmployeeDO findByID(int employeeId) {
        return entityManager.find(EmployeeDO.class, employeeId);
    }


    /**
     * Find all employees for a given user
     * @param userDo the user do to search for
     * @return list of all employees belonging to user
     */
    public List<EmployeeDO> findByUser(UserDO userDo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeDO> criteriaQuery = criteriaBuilder.createQuery(EmployeeDO.class);
        Root<EmployeeDO> root = criteriaQuery.from(EmployeeDO.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("userDo"), userDo));
        TypedQuery<EmployeeDO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }


}
