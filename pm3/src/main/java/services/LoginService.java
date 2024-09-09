package services;

import javax.persistence.EntityManager;

import persistence.UserDO;
import persistence.dao.UserDAO;
import persistence.exceptions.SaveFailedException;
import services.exceptions.InvalidPasswordException;
import services.exceptions.UserNotFoundException;

/**
 * Service for all actions regarding the login
 */
public class LoginService {
    private final boolean IS_DEMO_MODE;
    private final UserDAO userDAO;

    /**
     * @param entityManager
     */
    public LoginService(EntityManager entityManager, boolean isInDemoMode) {
        IS_DEMO_MODE = isInDemoMode;
        userDAO = new UserDAO(entityManager);
    }

    /**
     * Login the user with a given username and password
     * @param username username to log in
     * @param password password to log in
     * @return User
     * @throws UserNotFoundException is thrown if a username is not found
     * @throws InvalidPasswordException is thrown if the given password is wrong
     */
    public UserDO login(String username, String password) throws UserNotFoundException, InvalidPasswordException {
        if (IS_DEMO_MODE) {
            return handleDemoMode(username, password);
        }

        UserDO user = userDAO.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }

        if (!user.verifyPassword(password)) {
            throw new InvalidPasswordException(username);
        }

        return user;
    }

    // TODO: Remove later
    private UserDO handleDemoMode(String username, String password) {
        // check if user exists
        UserDO user = userDAO.findByUsername(username);
        if (user == null)
        {
            user = new UserDO(username, "Max", "Mustermann", "vorgesetzter@pm3.ch", password);
            
            try {
                userDAO.save(user);
            } catch (SaveFailedException e) {
                // TODO: handle exception
            }
        }

        return user;
    }
}
