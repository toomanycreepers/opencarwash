package com.example.opencarwash.utils.customExceptions;

public class AlreadyPresentException extends Exception{
    public AlreadyPresentException(){super();}
    public AlreadyPresentException(String message) { super(message); }
    public AlreadyPresentException(String message, Throwable cause) { super(message, cause); }
    public AlreadyPresentException(Throwable cause) { super(cause); }
}
