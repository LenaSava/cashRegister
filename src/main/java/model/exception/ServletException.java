package model.exception;

public class ServletException extends RuntimeException {

    public ServletException(String wrongData) {
        super(wrongData);
    }

    public ServletException() {}
}