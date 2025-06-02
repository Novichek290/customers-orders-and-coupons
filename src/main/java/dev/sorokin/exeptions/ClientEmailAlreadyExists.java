package dev.sorokin.exeptions;

public class ClientEmailAlreadyExists extends RuntimeException {
    private final String fieldName;
    public ClientEmailAlreadyExists (String fieldName, String massage){
        super(massage);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
