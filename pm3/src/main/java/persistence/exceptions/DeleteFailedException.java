package persistence.exceptions;

/**
 * Custom exception if deleting an object failed
 */
public class DeleteFailedException extends Exception {
    public final Exception OriginalException;

    /**
     * @param original
     * @param className
     */
    public DeleteFailedException(Exception original, String className) {
        super(String.format("Failed to delete object in %s", className));

        OriginalException = original;
    }
}
