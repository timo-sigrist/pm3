package persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import persistence.QuestionCatalogDO;
import persistence.QuestionTemplateDO;
import persistence.UserDO;

/**
 * DAO for a question template
 */
public class QuestionTemplateDAO extends BaseDAO<QuestionTemplateDO> {

    /**
     * @param entityManager
     */
    public QuestionTemplateDAO(EntityManager entityManager)
    {
        super(entityManager);
    }


    /**
     * Find a question by its id
     * @param id the id to search for
     * @return the question template if it exists
     */
    public QuestionTemplateDO findById(Integer id) {
        return entityManager.find(QuestionTemplateDO.class, id);
    }

    /**
     * Find questions linked to a user
     * @param userDo the user to search for
     * @return List of all linked questions to a user
     */
    public List<QuestionTemplateDO> findByUser(UserDO userDo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionTemplateDO> criteriaQuery = criteriaBuilder.createQuery(QuestionTemplateDO.class);
        Root<QuestionTemplateDO> root = criteriaQuery.from(QuestionTemplateDO.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("userDo"), userDo));
        TypedQuery<QuestionTemplateDO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * Find questions linked to a catalog
     * @param questionCatalogDo the catalog to search for
     * @return List of all linked questions to a catalog
     */
    public List<QuestionTemplateDO> findByCatalog(QuestionCatalogDO questionCatalogDo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionTemplateDO> criteriaQuery = criteriaBuilder.createQuery(QuestionTemplateDO.class);
        Root<QuestionTemplateDO> root = criteriaQuery.from(QuestionTemplateDO.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("questionCatalogDo"), questionCatalogDo));
        TypedQuery<QuestionTemplateDO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * Get all question templates
     * @return List of ALL question templates
     */
    public List<QuestionTemplateDO> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionTemplateDO> criteriaQuery = criteriaBuilder.createQuery(QuestionTemplateDO.class);
        Root<QuestionTemplateDO> root = criteriaQuery.from(QuestionTemplateDO.class);
        criteriaQuery.select(root);
        TypedQuery<QuestionTemplateDO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
    
}
