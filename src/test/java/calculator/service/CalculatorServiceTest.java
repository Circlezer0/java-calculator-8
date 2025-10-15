package calculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import calculator.model.Tokens;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {

    @Test
    void test() {
        Tokens tokens = new Tokens(List.of(1,2,3));
        CalculatorService calculatorService = new CalculatorService();
        int result = calculatorService.calculateSum(tokens);
        assertEquals(6, result);
    }

    @Test
    void 오버플로우_발생_시_IllegalArgumentException() {
        Tokens tokens = new Tokens(List.of(Integer.MAX_VALUE, 1));
        CalculatorService calculatorService = new CalculatorService();
        assertThrows(IllegalArgumentException.class, () -> calculatorService.calculateSum(tokens));
    }
}
