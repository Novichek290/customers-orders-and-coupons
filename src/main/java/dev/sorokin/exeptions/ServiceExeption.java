package dev.sorokin.exeptions;

public class ServiceExeption extends RuntimeException{
    public ServiceExeption(String message, Throwable cause) {
        super(message, cause);
    }
    public ServiceExeption(String message){super(message);}
}
