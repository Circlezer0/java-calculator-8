package calculator.model;

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
            throw new IllegalArgumentException("커스텀 구분자는 null일 수 없습니다.");
        }

        customDelimiter = customDelimiter.trim();

        if (customDelimiter.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자는 빈 값일 수 없습니다.");
        }

        if (customDelimiter.contains(" ")) {
            throw new IllegalArgumentException("커스텀 구분자에 공백이 포함될 수 없습니다.");
        }

        if (customDelimiter.chars().anyMatch(Character::isDigit)) {
            throw new IllegalArgumentException("커스텀 구분자에 숫자가 포함될 수 없습니다.");
        }
    }
}
