package persistence.exceptions;

/**
 * Custom exception if updating an object faild
 */
public class UpdateFailedException extends Exception {
    public final Exception OriginalException;

    /**
     * @param original
     * @param className
     */
    public UpdateFailedException(Exception original, String className) {
        super(String.format("Failed to update object in %s", className));

        OriginalException = original;
    }
}
