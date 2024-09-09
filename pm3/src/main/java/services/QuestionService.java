package services;

import persistence.*;
import persistence.dao.QuestionDAO;
import persistence.dao.QuestionPositionDAO;
import persistence.exceptions.DeleteFailedException;
import persistence.exceptions.SaveFailedException;
import persistence.exceptions.UpdateFailedException;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Service for all actions regarding a question
 */
public class QuestionService {
    private final QuestionDAO questionDAO;
    private final QuestionPositionDAO questionPositionDAO;

    /**
     * @param entityManager
     */
    public QuestionService(EntityManager entityManager) {
        questionDAO = new QuestionDAO(entityManager);
        questionPositionDAO = new QuestionPositionDAO(entityManager);
    }


    /**
     * Get question by id
     * @param id question-id
     */
    public QuestionDO findQuestionById(int id) {
        return questionDAO.findById(id);
    }


    /**
     * Find all questions by performencerating id
     * @param id performancerating id
     * @return list with question objects
     */
    public List<QuestionDO> findByPerformenceRatingId(int id) {
        return questionPositionDAO.findByPerformanceRatingId(id);
    }


    /**
     * Create a new Question
     * @param performanceRating performancerating object
     * @param questionTitle Text to set
     * @param questionDescription Text to set
     * @return created question
     * @throws SaveFailedException
     */
    public QuestionDO createNewQuestion(PerformanceRatingDO performanceRating, String questionTitle, String questionDescription) throws SaveFailedException {
        QuestionDO question = new QuestionDO(questionTitle, questionDescription, "");

        //object receives question-id from autoincrement
        question = questionDAO.save(question);
        questionPositionDAO.save(new QuestionPositionDO(question, performanceRating));
        return question;
    }


    /**
     * Updates a question
     * @param question question to update
     * @throws UpdateFailedException
     */
    public void updateQuestion(QuestionDO question) throws UpdateFailedException {
        questionDAO.update(question);
    }

    /**
     * Deletes a question and its question position
     * @param question question object
     * @param performanceRating performance rating object
     * @throws DeleteFailedException
     */
    public void deleteQuestion(QuestionDO question, PerformanceRatingDO performanceRating) throws DeleteFailedException {
        // todo: add transaction
        questionPositionDAO.delete(questionPositionDAO.findByPair(question, performanceRating));
        questionDAO.delete(questionDAO.findById(question.getQuestionId()));
    }

}
