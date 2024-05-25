package com.example.opencarwash.utils.customExceptions;

public class IllegalStatusMutationException extends Exception{
    public IllegalStatusMutationException(){super();}
    public IllegalStatusMutationException(String message) { super(message); }
    public IllegalStatusMutationException(String message, Throwable cause) { super(message, cause); }
    public IllegalStatusMutationException(Throwable cause) { super(cause); }
}
