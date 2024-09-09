package persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import persistence.QuestionDO;

/**
 * DAO for a question
 */
public class QuestionDAO extends BaseDAO<QuestionDO> {

    /**
     * @param entityManager
     */
    public QuestionDAO(EntityManager entityManager)
    {
        super(entityManager);
    }


    /**
     * Finds a QuestionDO by its id
     * @param id the id to search for
     * @return the question DO if it exists
     */
    public QuestionDO findById(int id) {
        return entityManager.find(QuestionDO.class, id);
    }

    /**
     * Find all Question DO's
     * @return list of all existsing questions
     */
    public List<QuestionDO> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionDO> criteriaQuery = criteriaBuilder.createQuery(QuestionDO.class);
        Root<QuestionDO> root = criteriaQuery.from(QuestionDO.class);
        criteriaQuery.select(root);
        TypedQuery<QuestionDO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
