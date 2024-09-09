package persistence.exceptions;

/**
 * Custom exception if saving an object failed
 */
public class SaveFailedException extends Exception {
    public final Exception OriginalException;

    /**
     * @param original
     * @param className
     */
    public SaveFailedException(Exception original, String className) {
        super(String.format("Failed to save object in %s", className));

        OriginalException = original;
    }
}
