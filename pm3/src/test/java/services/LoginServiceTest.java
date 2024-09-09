package services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.H2Database;
import persistence.UserDO;
import services.exceptions.InvalidPasswordException;
import services.exceptions.UserNotFoundException;

class LoginServiceTest
{
    H2Database database;
    LoginService service;

    @BeforeEach
    void setup()
    {
        database = new H2Database();
        service = new LoginService(database.getEntityManager(), false);
    }

    @Test
    void Test_Login_ExpectUserNotFound()
    {
        assertThrows(UserNotFoundException.class, () -> {
            service.login("some username", "something else");
        });
    }

    @Test
    void Test_Login_ExpectInvalidPassword()
    {
        final String username = "someones-username";
        final String password = "someones-password";
        
        {
            UserDO user = new UserDO(username, "", "", "", password);
            database.save(user);
        }


        assertThrows(InvalidPasswordException.class, () -> {
            service.login(username, "invalid-password");
        });
    }

    @Test
    void Test_Login_Valid()
    {
        final String username = "someones-username";
        final String password = "someones-password";
        
        {
            UserDO user = new UserDO(username, "", "", "", password);
            user = database.save(user);
        }

        assertDoesNotThrow(() -> {
            UserDO loggedInUser = service.login(username, password);

            assertEquals(username, loggedInUser.getUsername());
        });
        
    }
}
