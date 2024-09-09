package persistence.dao;

import common.H2Database;
import persistence.EmployeeDO;
import persistence.dao.EmployeeDAO;
import persistence.exceptions.DeleteFailedException;
import persistence.exceptions.SaveFailedException;
import persistence.exceptions.UpdateFailedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BaseDAOTest {

    H2Database database;
    EmployeeDAO dao;

    @BeforeEach
    void setup()
    {
        database = new H2Database();
        dao = new EmployeeDAO(database.getEntityManager());
    }

    @Test
    void TestSave_Null()
    {
        assertThrows(SaveFailedException.class, () -> {
            dao.save(null);
        });
    }

    @Test
    void TestSave_NotNull()
    {
        final String testEmail = "some Test value";
        EmployeeDO data = new EmployeeDO();
        data.setTeamName("some name");
        data.setEmail(testEmail);

        assertDoesNotThrow(() -> {
            dao.save(data);
        });
    }

    @Test
    void TestUpdate_Null()
    {
        assertThrows(UpdateFailedException.class, () -> {
            dao.update(null);
        });
    }

    @Test
    void TestUpdate_NewDO()
    {
        final String testEmail = "some Test value"; 
        EmployeeDO data = new EmployeeDO();
        data.setTeamName("some name");
        data.setEmail(testEmail);

        assertDoesNotThrow(() -> {
            dao.update(data);
        });


        List<EmployeeDO> employees = findAll();

        assertEquals(1, employees.size());

        EmployeeDO employee = employees.get(0);

        assertEquals(testEmail, employee.getEmail());

    }

    @Test
    void TestUpdate_Valid()
    {
        final String updatedLastName = "new last name";

        EmployeeDO data = new EmployeeDO();
        data.setTeamName("some name");
        data.setEmail("some email");

        assertDoesNotThrow(() -> {
            dao.save(data);
        });


        data.setLastname(updatedLastName);

        assertDoesNotThrow(() -> {
            dao.update(data);
        });

        List<EmployeeDO> employees = findAll();

        assertEquals(1, employees.size());

        EmployeeDO employee = employees.get(0);

        assertEquals(updatedLastName, employee.getLastname());
    }


    @Test
    void TestDelete_Null()
    {
        assertThrows(DeleteFailedException.class, ()-> {
            dao.delete(null);
        });
    }

    @Test
    void TestDelete_NotNull()
    {
        EmployeeDO data = new EmployeeDO();
        data.setTeamName("some name");
        data.setEmail("some email");

        assertDoesNotThrow(() -> {
            dao.save(data);
        });

        assertDoesNotThrow(() -> {
            dao.delete(data);
        });
    }

    @Test
    void TestDelete_NewDO()
    {
        EmployeeDO data = new EmployeeDO();
        data.setTeamName("some name");
        data.setEmail("some email");


        assertDoesNotThrow(() -> {
            dao.delete(data);
        });
    }

    private List<EmployeeDO> findAll() {
        CriteriaBuilder criteriaBuilder = database.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EmployeeDO> criteriaQuery = criteriaBuilder.createQuery(EmployeeDO.class);
        Root<EmployeeDO> root = criteriaQuery.from(EmployeeDO.class);
        criteriaQuery.select(root);
        TypedQuery<EmployeeDO> query = database.getEntityManager().createQuery(criteriaQuery);

        return query.getResultList();
    }
}
