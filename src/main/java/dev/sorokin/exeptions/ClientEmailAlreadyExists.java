package dev.sorokin.exeptions;

import lombok.Getter;

@Getter
public class ClientEmailAlreadyExists extends RuntimeException {
    private final String fieldName;
    public ClientEmailAlreadyExists (String fieldName, String massage){
        super(massage);
        this.fieldName = fieldName;
    }
}
