package services.exceptions;

/**
 * Custom exception if no user with given username exists
 */
public class UserNotFoundException extends Exception {
    public final String username;

    /**
     * @param username
     */
    public UserNotFoundException(String username) {
        super(String.format("Username {0} not found", username));

        this.username = username;
    }
}
