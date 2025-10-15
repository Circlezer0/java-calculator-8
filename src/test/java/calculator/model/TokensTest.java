package calculator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TokensTest {

    @Test
    void 빈_리스트면_sum은_0() {
        Tokens tokens = new Tokens(List.of());
        assertEquals(0, tokens.sum());
    }

    @Test
    void 정상_합산() {
        Tokens tokens = new Tokens(List.of(1, 2, 3));
        assertEquals(6, tokens.sum());
    }

    @Test
    void 불변성_보장() {
        List<Integer> inputArr = new ArrayList<>(List.of(1, 2, 3));
        Tokens tokens = new Tokens(inputArr);
        inputArr.add(4);
        assertEquals(6, tokens.sum());
    }

    @Test
    void 오버플로우면_ArithmeticException() {
        Tokens tokens = new Tokens(List.of(Integer.MAX_VALUE, 1));
        assertThrows(ArithmeticException.class, () -> tokens.sum());
    }

    @Test
    void 음수가_들어오면_객체_생성시_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Tokens(List.of(-1, 2, 3)));
    }
}
