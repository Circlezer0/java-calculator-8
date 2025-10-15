package calculator.service;

import calculator.model.Tokens;

public class CalculatorService {

    public int calculateSum(Tokens tokens) {
        try {
            return tokens.sum();
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("계산 결과가 정수 범위를 초과했습니다.");
        }
    }
}
