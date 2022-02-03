package ua.lviv.iot.view.exceptions;

public class WrongTableNameException extends ViewException {
    public WrongTableNameException(String passedName) {
        super(String.format("Cannot find table with name '%s'", passedName));
    }
}
