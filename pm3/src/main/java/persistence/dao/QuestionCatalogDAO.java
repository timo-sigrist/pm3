package persistence.dao;

import persistence.QuestionCatalogDO;
import persistence.UserDO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * DAO for a question catalog
 */
public class QuestionCatalogDAO extends BaseDAO<QuestionCatalogDO> {
    /**
     * @param entityManager
     */
    public QuestionCatalogDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Finds question catalog by given id
     * @param id
     * @return found question catalog
     */
    public QuestionCatalogDO findById(Integer id) {
        return entityManager.find(QuestionCatalogDO.class, id);
    }

    /**
     * Finds all question catalogs for given user
     * @param userDo
     * @return all question catalogs for given user
     */
    public List<QuestionCatalogDO> findByUser(UserDO userDo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionCatalogDO> criteriaQuery = criteriaBuilder.createQuery(QuestionCatalogDO.class);
        Root<QuestionCatalogDO> root = criteriaQuery.from(QuestionCatalogDO.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("userDo"), userDo));
        TypedQuery<QuestionCatalogDO> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    /**
     * Finds all existing question catalogs
     * @return all existing question catalogs
     */
    public List<QuestionCatalogDO> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionCatalogDO> criteriaQuery = criteriaBuilder.createQuery(QuestionCatalogDO.class);
        Root<QuestionCatalogDO> root = criteriaQuery.from(QuestionCatalogDO.class);
        criteriaQuery.select(root);
        TypedQuery<QuestionCatalogDO> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
