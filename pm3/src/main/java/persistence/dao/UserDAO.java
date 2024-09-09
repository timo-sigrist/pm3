package persistence.dao;


import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import persistence.UserDO;

/**
 * DAO for a user
 */
public class UserDAO extends BaseDAO<UserDO>
{
    private static final Logger LOGGER = Logger.getLogger( GoalDAO.class.getName() );

    /**
     * @param entityManager
     */
    public UserDAO(EntityManager entityManager)
    {
        super(entityManager);
    }


    /**
     * Find a User by its unique username
     * @param username the username to search for
     * @return the user if it exists
     */
    public UserDO findByUsername(String username)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserDO> criteriaQuery = criteriaBuilder.createQuery(UserDO.class);
        Root<UserDO> itemRoot = criteriaQuery.from(UserDO.class);

        Predicate predicateForUsername = criteriaBuilder.equal(itemRoot.get("username"), username);

        criteriaQuery.where(predicateForUsername);
        
        List<UserDO> users =entityManager.createQuery(criteriaQuery).getResultList();

        if (users.size() > 0) {
            return users.get(0);
        }
 
        return null;
    }
}
