package calculator.exception.code;

import calculator.exception.BaseErrorCode;

public enum ParseErrorCode implements BaseErrorCode {
    CONTAINS_NON_DIGIT("숫자가 아닌 문자가 포함되어 있습니다."),
    INTEGER_OVERFLOW("정수 범위를 벗어나는 숫자가 포함되어 있습니다.");

    private final String message;

    ParseErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
