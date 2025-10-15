package calculator.exception.code;

import calculator.exception.BaseErrorCode;
import calculator.exception.CalculatorException;

public enum CalculatorErrorCode implements BaseErrorCode {
    INTEGER_OVERFLOW("계산 결과가 정수 범위를 초과했습니다.");

    private final String message;

    CalculatorErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
