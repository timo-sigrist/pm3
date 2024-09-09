package services;

import java.util.List;

import javax.persistence.EntityManager;

import persistence.QuestionCatalogDO;
import persistence.QuestionTemplateDO;
import persistence.UserDO;
import persistence.dao.QuestionTemplateDAO;
import persistence.exceptions.DeleteFailedException;
import persistence.exceptions.SaveFailedException;
import persistence.exceptions.UpdateFailedException;

/**
 * Service for all actions regarding a question template
 */
public class QuestionTemplateService {
    private final QuestionTemplateDAO questionTemplateDAO;

    /**
     * @param entityManager
     */
    public QuestionTemplateService(EntityManager entityManager)
    {
        questionTemplateDAO = new QuestionTemplateDAO(entityManager);
    }

    /**
     * Find all Question templates for a given user
     * @param user user to search for
     * @return list of all question templates assigned to user
     */
    public List<QuestionTemplateDO> findByUser(UserDO user)
    {
        return questionTemplateDAO.findByUser(user);
    }

    /**
     * Find all Question templates for a question catalog
     * @param questionCatalog question catalog to search for
     * @return list of all question templates assigned to the question catalog
     */
    public List<QuestionTemplateDO> findByCatalog(QuestionCatalogDO questionCatalog)
    {
        return questionTemplateDAO.findByCatalog(questionCatalog);
    }


    /**
     * Create a new Question Template 
     * @param questionTemplate question template to generate
     * @throws SaveFailedException is thrown if the save fails
     */
    public void createNewQuestionTemplate(QuestionTemplateDO questionTemplate)
    throws SaveFailedException
    {
        questionTemplateDAO.save(questionTemplate);
    }

    /**
     * Updates a given question template to the newer state
     * @param questionTemplate template to be updated
     * @throws UpdateFailedException is thrown when the update has failed
     */
    public void updateQuestionTemplate(QuestionTemplateDO questionTemplate)
    throws UpdateFailedException
    {
        questionTemplateDAO.update(questionTemplate);
    }

    /**
     * Deletes a Question template
     * @param questionTemplate template to be deleted
     * @throws DeleteFailedException is thrown if deletion failed
     */
    public void deleteQuestionTemplate(QuestionTemplateDO questionTemplate)
    throws DeleteFailedException
    {
        questionTemplateDAO.delete(questionTemplate);
    }
}
