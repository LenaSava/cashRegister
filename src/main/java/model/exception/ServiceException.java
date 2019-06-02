package model.exception;

public class ServiceException extends RuntimeException {

    /**
     * ServiceException class
     *
     */

    public ServiceException(String wrongData) {
        super(wrongData);
    }
    /**
     * {@inheritDoc}
     */

    public ServiceException() {}
}