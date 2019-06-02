package model.exception;

public class ServletException extends RuntimeException {
    /**
     * ServiceException class
     *
     */

    public ServletException(String wrongData) {
        super(wrongData);
    }
    /**
     * {@inheritDoc}
     */

    public ServletException() {}
}