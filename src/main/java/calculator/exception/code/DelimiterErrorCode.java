package calculator.exception.code;

import calculator.exception.BaseErrorCode;

public enum DelimiterErrorCode implements BaseErrorCode {
    CUSTOM_DELIMITER_NOT_NULL("커스텀 구분자는 null일 수 없습니다."),
    CUSTOM_DELIMITER_EMPTY("커스텀 구분자는 빈 값일 수 없습니다."),
    CUSTOM_DELIMITER_CONTAINS_SPACE("커스텀 구분자는 공백을 포함할 수 없습니다."),
    CUSTOM_DELIMITER_CONTAINS_NUMBER("커스텀 구분자는 숫자를 포함할 수 없습니다."),
    ;

    private final String message;

    DelimiterErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
