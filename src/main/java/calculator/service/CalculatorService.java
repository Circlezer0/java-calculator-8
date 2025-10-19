package calculator.service;

import calculator.exception.CalculatorException;
import calculator.exception.code.CalculatorErrorCode;
import calculator.model.Tokens;

/**
 * CalculatorService는 토큰화된 정수 리스트에 대한 계산 기능을 제공하는 클래스이다.
 */
public class CalculatorService {

    /**
     * 토큰화된 정수 리스트의 합을 계산한다.
     * Tokens.sum() 메서드에서 발생하는 정수 오버플로우 예외를
     * CalculatorException으로 변환하여 던진다.
     * @param tokens 토큰화된 정수 리스트
     * @return 정수 리스트의 합
     */
    public int calculateSum(Tokens tokens) {
        try {
            return tokens.sum();
        } catch (ArithmeticException e) {
            throw new CalculatorException(CalculatorErrorCode.INTEGER_OVERFLOW);
        }
    }
}
