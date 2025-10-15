package calculator.exception.code;

import calculator.exception.BaseErrorCode;

public enum TokensErrorCode implements BaseErrorCode {
    NEGATIVE_NUMBER_NOT_ALLOWED("음수는 허용되지 않습니다.")
    ;

    private final String message;

    TokensErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
