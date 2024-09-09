package persistence.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.view.performancerating.ratingprocess.PerformanceRatingProcessController;
import common.H2Database;
import persistence.PerformanceRatingDO;
import persistence.QuestionDO;
import persistence.QuestionPositionDO;

class QuestionPositionDAOTest {
    H2Database database;
    QuestionPositionDAO dao;

    @BeforeEach
    void setup()
    {
        database = new H2Database();
        dao = new QuestionPositionDAO(database.getEntityManager());
    }


    @Test
    void test_findByPerformanceRatingId()
    {
        final String employeeAnswer = "employee's answer";
        final String employerAnswer = "employer's answer";
        final int performanceRatingId;
        {
            
            final PerformanceRatingDO rating ;
            final QuestionDO question;
            {
                rating = new PerformanceRatingDO();
                database.save(rating);
            }
            {
                question = new QuestionDO();
                question.setQuestionTitle("title");
                question.setQuestionDescription("description");
                question.setAnswerEmployee(employeeAnswer);
                question.setAnswerEmployer(employerAnswer);
                database.save(question);

            }
            {
                QuestionPositionDO position = new QuestionPositionDO();
                position.setPerformanceRatingDo(rating);
                position.setQuestionDo(question);

                database.save(position);
            }

            performanceRatingId = rating.getPerformanceRatingId();

        }
        
        List<QuestionDO> questions = dao.findByPerformanceRatingId(performanceRatingId);

        assertNotNull(questions);
        assertEquals(1, questions.size());

        QuestionDO question = questions.get(0);
        assertEquals(employeeAnswer, question.getAnswerEmployee());
        assertEquals(employerAnswer, question.getAnswerEmployer());

    }

    @Test
    void test_do_not_find_other_answers()
    {
        final String employeeAnswer = "employee's answer";
        final String employerAnswer = "employer's answer";
        final int performanceRatingId;
        {
            
            final PerformanceRatingDO rating ;
            final QuestionDO question;
            {
                rating = new PerformanceRatingDO();
                database.save(rating);
            }
            {
                question = new QuestionDO();
                question.setQuestionTitle("title");
                question.setQuestionDescription("description");
                question.setAnswerEmployee(employeeAnswer);
                question.setAnswerEmployer(employerAnswer);
                database.save(question);

            }
            {
                QuestionPositionDO position = new QuestionPositionDO();
                position.setPerformanceRatingDo(rating);
                position.setQuestionDo(question);

                database.save(position);
            }

            performanceRatingId = rating.getPerformanceRatingId();

        }

        List<QuestionDO> questions = dao.findByPerformanceRatingId(performanceRatingId+1);
        assertNotNull(questions);
        assertEquals(0, questions.size());
    }
}
