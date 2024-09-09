package services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.H2Database;
import persistence.EmployeeDO;
import persistence.PerformanceRatingDO;
import persistence.UserDO;
import persistence.dao.PerformanceRatingDAO;
import persistence.dao.UserDAO;
import persistence.exceptions.SaveFailedException;

class PerformanceRatingServiceTest {

    H2Database database;
    PerformanceRatingService service;

    @BeforeEach
    void setup()
    {
        database = new H2Database();
        service = new PerformanceRatingService(database.getEntityManager());
    }

    @Test
    void TestFindByEmployer_Existent() 
    {
        UserDO employer;
        
        
        UserDAO userDao = new UserDAO(database.getEntityManager());
        // Create employer
        {
            
            employer = new UserDO("employer", "i", "am", "your", "father");

            assertDoesNotThrow(() -> {
                userDao.save(employer);
            });

            
        }

        // Create dao 
        {
            Set<PerformanceRatingDO> set = new HashSet<>();
            PerformanceRatingDAO dao = new PerformanceRatingDAO(database.getEntityManager());

            {
                PerformanceRatingDO rating = new PerformanceRatingDO();
                rating.setUserDo(employer);

                assertDoesNotThrow(() -> {
                    dao.save(rating);
                });
                
                set.add(rating);
                
            }

            assertDoesNotThrow(() -> {
                userDao.update(employer);
            });

        }

        List<PerformanceRatingDO> ratings = service.findByEmployer(employer);

        assertNotNull(ratings);

        assertEquals(1, ratings.size());

        assertEquals(ratings.get(0).getUserDo().getUsername(), employer.getUsername());   
    }

    @Test
    void TestFindByEmployer_NonExistent()
    {
        UserDO employer = new UserDO("employer", "i", "am", "your", "father");


        assertThrows(IllegalStateException.class, () -> {
            List<PerformanceRatingDO> ratings = service.findByEmployer(employer);
        });        
    }

    @Test
    void TestFindByEmployee_NonExistent()
    {
        EmployeeDO employer = new EmployeeDO(
            "firstname",
            "lastname",
            "email",
            "birthay",
            "teamname",
            "socialSecurityNumber"
        );


        assertThrows(IllegalStateException.class, () -> {
            List<PerformanceRatingDO> ratings = service.findByEmployee(employer);
        });   
    }
}
