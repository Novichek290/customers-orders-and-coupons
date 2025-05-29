package dev.sorokin.service;

public class ServiceExeption extends RuntimeException{
    public ServiceExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
