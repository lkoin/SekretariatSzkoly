package pl.gornik.exceptions;

public class PersonIsAlreadyDefinedToClassException extends Exception{
    public PersonIsAlreadyDefinedToClassException(String message) {
        super(message);
    }
}
