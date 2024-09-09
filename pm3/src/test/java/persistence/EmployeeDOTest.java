package persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EmployeeDOTest {
    
    @Test
    void Test_EmptyInit()
    {
        assertDoesNotThrow(()-> {
            EmployeeDO employee = new EmployeeDO();
        });
    }

    @Test
    void Test_Firstname()
    {
        final String expectedValue = "i am great";
        EmployeeDO employee = new EmployeeDO();
        employee.setFirstname(expectedValue);

        assertEquals(expectedValue, employee.getFirstname());
    }

    @Test
    void Test_Lastname()
    {
        final String expectedValue = "i am great";
        EmployeeDO employee = new EmployeeDO();
        employee.setLastname(expectedValue);

        assertEquals(expectedValue, employee.getLastname());
    }


    @Test
    void Test_Email()
    {
        final String expectedValue = "i am great";
        EmployeeDO employee = new EmployeeDO();
        employee.setEmail(expectedValue);

        assertEquals(expectedValue, employee.getEmail());
    }

    @Test
    void Test_Birthday()
    {
        final String expectedValue = "i am great";
        EmployeeDO employee = new EmployeeDO();
        employee.setBirthdate(expectedValue);

        assertEquals(expectedValue, employee.getBirthdate());
    }

    @Test
    void Test_Teamname()
    {
        final String expectedValue = "i am great";
        EmployeeDO employee = new EmployeeDO();
        employee.setTeamName(expectedValue);

        assertEquals(expectedValue, employee.getTeamName());
    }

    @Test
    void Test_SicoalSecurityNumber()
    {
        final String expectedValue = "i am great";
        EmployeeDO employee = new EmployeeDO();
        employee.setSocialSecurityNumber(expectedValue);

        assertEquals(expectedValue, employee.getSocialSecurityNumber());
    }


    @Test
    void Test_Fullname()
    {
        final String expectedValue = "i am great";
        EmployeeDO employee = new EmployeeDO();
        employee.setFirstname("i am");
        employee.setLastname("great");

        assertEquals(expectedValue, employee.getFullName());
    }


    
    @Test
    void Test_UserDO()
    {
        UserDO user = new UserDO();
        user.setEmail("email");

        EmployeeDO employee = new EmployeeDO();
        employee.setUserDo(user);

        assertEquals(user, employee.getUserDo());
    }
}
