package model.exception;

public class WrongDataException extends RuntimeException {

    /**
     * WrongDataException class
     *
     */

    public  WrongDataException(String wrongData)
    {
        super(wrongData);
    }
    /**
     * {@inheritDoc}
     */

    public WrongDataException() {} {

    }
}