package services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.H2Database;
import persistence.EmployeeDO;
import persistence.UserDO;

class EmployeeServiceTest {

    H2Database database;
    EmployeeService service;

    @BeforeEach
    void setup()
    {
        database = new H2Database();
        service = new EmployeeService(database.getEntityManager());
    }


    @Test
    void TestFindByUser_Valid()
    {
        {
            UserDO user = new UserDO("username", "firstname", "lastname", "email", "password");

            user = database.save(user);
    
            EmployeeDO employeeDO = new EmployeeDO();
            employeeDO.setEmail("user-email");
            employeeDO.setUserDo(user);
    
            database.save(employeeDO);
        }


        UserDO user = new UserDO("another-user", "firstname", "lastname", "email", "password");

        user = database.save(user);

        EmployeeDO employeeDO = new EmployeeDO();
        employeeDO.setEmail("user-email");
        employeeDO.setUserDo(user);

        database.save(employeeDO);


        List<EmployeeDO> employees = service.findByUser(user);

        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals(employeeDO.getEmail(), employees.get(0).getEmail());
    }

    @Test
    void TestFindByUser_Null()
    {
        assertDoesNotThrow(() -> {
            service.findByUser(null);
        });
    }

    @Test
    void TestFindByUser_NoEmployees()
    {
        {
            UserDO user = new UserDO("username", "firstname", "lastname", "email", "password");

            user = database.save(user);
    
            EmployeeDO employeeDO = new EmployeeDO();
            employeeDO.setEmail("user-email");
            employeeDO.setUserDo(user);
    
            database.save(employeeDO);
        }
       
        UserDO user = new UserDO("another-user", "firstname", "lastname", "email", "password");

        user = database.save(user);

        List<EmployeeDO> employees = service.findByUser(user);

        assertNotNull(employees);
        assertEquals(0, employees.size());
    }

}
