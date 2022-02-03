package ua.lviv.iot.view.exceptions;

public class WrongParamNumException extends ViewException {
    public WrongParamNumException(Integer providedParamNum, Integer expectedParamNum) {
        super(String.format(
                "Wrong parameters number (%s) provided during function call (%s expected)",
                providedParamNum, expectedParamNum
        ));
    }
}
