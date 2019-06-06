package model.exception;

public class DataBaseException extends Exception {
    /**
     * DatabaseException class
     *
     */

    public DataBaseException(String wrongData) {
        super(wrongData);
    }
    /**
     * {@inheritDoc}
     */

    public DataBaseException() {}
}