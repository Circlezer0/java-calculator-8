package calculator.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DelimiterTest {

    @Test
    void 구분자에_공백_포함_시_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Delimiter("a a"));
    }

    @Test
    void 구분자_빈값_시_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Delimiter(" "));
        assertThrows(IllegalArgumentException.class, () -> new Delimiter(""));
    }

    @Test
    void 구분자_null_시_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Delimiter(null));
    }

    @Test
    void 구분자에_숫자_포함_시_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Delimiter("1"));
        assertThrows(IllegalArgumentException.class, () -> new Delimiter("a1"));
        assertThrows(IllegalArgumentException.class, () -> new Delimiter("1a"));
        assertThrows(IllegalArgumentException.class, () -> new Delimiter("a1a"));
    }
}
