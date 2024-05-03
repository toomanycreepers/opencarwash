package com.example.opencarwash.utils.customExceptions;

public class AbsentFromCollectionException extends Exception{
    public AbsentFromCollectionException(){super();}
    public AbsentFromCollectionException(String message) { super(message); }
    public AbsentFromCollectionException(String message, Throwable cause) { super(message, cause); }
    public AbsentFromCollectionException(Throwable cause) { super(cause); }
}
