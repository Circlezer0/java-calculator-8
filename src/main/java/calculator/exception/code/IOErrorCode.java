package calculator.exception.code;

import calculator.exception.BaseErrorCode;

public enum IOErrorCode implements BaseErrorCode {
    INPUT_ERROR("입력값이 올바르지 않습니다.")
    ;

    private final String message;

    IOErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
