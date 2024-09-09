package services;

import persistence.QuestionCatalogDO;
import persistence.UserDO;
import persistence.dao.QuestionCatalogDAO;
import persistence.exceptions.DeleteFailedException;
import persistence.exceptions.SaveFailedException;
import persistence.exceptions.UpdateFailedException;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Service for all actions regarding a question catalog
 */
public class QuestionCatalogService {
    private final QuestionCatalogDAO questionCatalogDAO;

    /**
     * @param entityManager
     */
    public QuestionCatalogService(EntityManager entityManager) {
        questionCatalogDAO = new QuestionCatalogDAO(entityManager);
    }

    /**
     * Find all Question catalogs for a given user
     * @param user user to search for
     * @return list of all question catalogs assigned to user
     */
    public List<QuestionCatalogDO> findByUser(UserDO user)
    {
        return questionCatalogDAO.findByUser(user);
    }


    /**
     * Create a new Question Catalog
     * @param questionCatalog question catalog to generate
     * @throws SaveFailedException is thrown if the save fails
     */
    public void createNewQuestionCatalog(QuestionCatalogDO questionCatalog) throws SaveFailedException {
        questionCatalogDAO.save(questionCatalog);
    }

    /**
     * Updates a given question catalog to the newer state
     * @param questionCatalog catalog to be updated
     * @throws UpdateFailedException is thrown when the update has failed
     */
    public void updateQuestionCatalog(QuestionCatalogDO questionCatalog) throws UpdateFailedException {
        questionCatalogDAO.update(questionCatalog);
    }

    /**
     * Deletes a Question catalog
     * @param questionCatalog catalog to be deleted
     * @throws DeleteFailedException is thrown if deletion failed
     */
    public void deleteQuestionCatalog(QuestionCatalogDO questionCatalog) throws DeleteFailedException {
        questionCatalogDAO.delete(questionCatalog);
    }
}
