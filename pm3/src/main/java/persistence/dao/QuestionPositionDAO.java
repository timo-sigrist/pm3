package persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

import persistence.*;

/**
 * DAO for a question position in a performance rating
 */
public class QuestionPositionDAO extends BaseDAO<QuestionPositionDO> {

    /**
     * @param entityManager
     */
    public QuestionPositionDAO(EntityManager entityManager)
    {
        super(entityManager);
    }

    /**
     * Searches for a Question Position by its id
     * @param id the id to search for
     * @return the question position if found
     */
    public QuestionPositionDO findById(int id) {
        return entityManager.find(QuestionPositionDO.class, id);
    }
 
    /**
     * find all Question Positions
     * @return list of ALL Positions
     */
    public List<QuestionPositionDO> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionPositionDO> criteriaQuery = criteriaBuilder.createQuery(QuestionPositionDO.class);
        Root<QuestionPositionDO> root = criteriaQuery.from(QuestionPositionDO.class);
        criteriaQuery.select(root);
        TypedQuery<QuestionPositionDO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * Find all question positions linked to a question
     * @param questionDo the question object to search questions for
     * @return list of all positions
     */
    public List<QuestionPositionDO> findByQuestion(QuestionDO questionDo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionPositionDO> criteriaQuery = criteriaBuilder.createQuery(QuestionPositionDO.class);
        Root<QuestionPositionDO> root = criteriaQuery.from(QuestionPositionDO.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("questionDo"), questionDo));
        TypedQuery<QuestionPositionDO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }


    /**
     * Find a specific QuestionPosition located by the pair
     * @param questionDo the question object
     * @param performanceRatingDO performance rating object
     * @return questionPosition object
     */
    public QuestionPositionDO findByPair(QuestionDO questionDo, PerformanceRatingDO performanceRatingDO) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionPositionDO> criteriaQuery = criteriaBuilder.createQuery(QuestionPositionDO.class);
        Root<QuestionPositionDO> root = criteriaQuery.from(QuestionPositionDO.class);

        Predicate BothSame = criteriaBuilder.and(criteriaBuilder.equal(root.get("questionDo"), questionDo), criteriaBuilder.equal(root.get("performanceRatingDO"), performanceRatingDO));
        criteriaQuery.where(BothSame);

        TypedQuery<QuestionPositionDO> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    /**
     * Find all question positions linked to a performance rating
     * @param id id from performance rating
     * @return list of all questions belonging to the rating
     */
    public List<QuestionDO> findByPerformanceRatingId(int id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionDO> criteriaQuery = criteriaBuilder.createQuery(QuestionDO.class);
        Root<QuestionPositionDO> root = criteriaQuery.from(QuestionPositionDO.class);
        Join<QuestionPositionDO, QuestionDO> question = root.join("questionDo");
        Join<QuestionPositionDO, PerformanceRatingDO> pr = root.join("performanceRatingDO");

        criteriaQuery.select(question).distinct(true).where(criteriaBuilder.equal(pr.get("performanceRatingId"), id));
        TypedQuery<QuestionDO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
