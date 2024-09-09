package services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.H2Database;
import persistence.QuestionCatalogDO;
import persistence.UserDO;

class QuestionCatalogServiceTest
{
    H2Database database;
    QuestionCatalogService service;

    @BeforeEach
    void setup()
    {
        database = new H2Database();
        service = new QuestionCatalogService(database.getEntityManager());
    }

    @Test
    void Test_FindByUser_Existing()
    {
        final UserDO user;
        {
            user = new UserDO("username", "","","","password");
            database.save(user);
        }

        {
            QuestionCatalogDO questionCatalog = new QuestionCatalogDO();
            questionCatalog.setUserDo(user);

            database.save(questionCatalog);
        }


        List<QuestionCatalogDO> questions = service.findByUser(user);

        assertEquals(1, questions.size());
    }


    @Test
    void Test_FindByUser_NotExisting()
    {
        final UserDO user;
        {
            user = new UserDO("username", "","","","password");
            database.save(user);
        }

        List<QuestionCatalogDO> questions = service.findByUser(user);

        assertEquals(0, questions.size());
    }
}
