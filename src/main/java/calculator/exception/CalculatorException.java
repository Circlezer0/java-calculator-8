package calculator.exception;

public class CalculatorException extends IllegalArgumentException {
    public CalculatorException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
