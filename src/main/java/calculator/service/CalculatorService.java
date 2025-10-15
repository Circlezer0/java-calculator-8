package calculator.service;

import calculator.exception.CalculatorException;
import calculator.exception.code.CalculatorErrorCode;
import calculator.model.Tokens;

public class CalculatorService {

    public int calculateSum(Tokens tokens) {
        try {
            return tokens.sum();
        } catch (ArithmeticException e) {
            throw new CalculatorException(CalculatorErrorCode.INTEGER_OVERFLOW);
        }
    }
}
