package calculator.model;

import calculator.exception.CalculatorException;
import calculator.exception.code.DelimiterErrorCode;
import java.util.regex.Pattern;

public class Delimiter {

    private static final String DEFAULT_DELIMITER = "[,:]";

    private final String delimiter;

    public Delimiter() {
        this.delimiter = DEFAULT_DELIMITER;
    }

    public Delimiter(String customDelimiter) {
        validateCustomDelimiter(customDelimiter);
        this.delimiter = DEFAULT_DELIMITER + "|" + Pattern.quote(customDelimiter);
    }

    public String getDelimiter() {
        return delimiter;
    }

    /**
     * 커스텀 구분자의 유효성을 검사한다.
     * 커스텀 구분자가 null, 빈 문자열, 공백 포함, 숫자 포함인 경우 예외를 발생시킨다.
     * @param customDelimiter 커스텀 구분자 문자열
     */
    private void validateCustomDelimiter(String customDelimiter) {
        if (customDelimiter == null) {
            throw new CalculatorException(DelimiterErrorCode.CUSTOM_DELIMITER_NOT_NULL);
        }

        customDelimiter = customDelimiter.trim();

        if (customDelimiter.isEmpty()) {
            throw new CalculatorException(DelimiterErrorCode.CUSTOM_DELIMITER_EMPTY);
        }

        if (customDelimiter.contains(" ")) {
            throw new CalculatorException(DelimiterErrorCode.CUSTOM_DELIMITER_CONTAINS_SPACE);
        }

        if (customDelimiter.chars().anyMatch(Character::isDigit)) {
            throw new CalculatorException(DelimiterErrorCode.CUSTOM_DELIMITER_CONTAINS_NUMBER);
        }
    }
}
