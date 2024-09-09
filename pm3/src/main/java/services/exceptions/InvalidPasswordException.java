package services.exceptions;

/**
 * Custom exception if an invalid password was entered
 */
public class InvalidPasswordException extends Exception {
    public final String username;

    /**
     * @param username
     */
    public InvalidPasswordException(String username) {
        super(String.format("Invalid password for username {0}", username));

        this.username = username;
    }
    
}
