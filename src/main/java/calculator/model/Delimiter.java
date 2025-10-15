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
