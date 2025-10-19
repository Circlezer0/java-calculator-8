package calculator.model;

import calculator.exception.CalculatorException;
import calculator.exception.code.TokensErrorCode;
import java.util.List;

public class Tokens {

    private final List<Integer> tokens;

    public Tokens(List<Integer> tokens) {
        validateArguments(tokens);
        this.tokens = List.copyOf(tokens);
    }

    public List<Integer> getTokens() {
        return tokens;
    }

    /**
     * 토큰 리스트의 합을 계산한다.
     * 계산 도중 정수 오버플로우가 발생하면 ArithmeticException을 발생시킨다
     * @return 토큰 리스트의 합
     */
    public int sum() {
        int sum = 0;
        for (int token : tokens) {
            sum = Math.addExact(sum, token);
        }
        return sum;
    }

    /**
     * 토큰 리스트의 유효성을 검사하는 자기방어적 메서드이다.
     * 음수 값이 포함된 경우 예외를 발생시킨다.
     * @param tokens 토큰화된 정수 리스트
     */
    private void validateArguments(List<Integer> tokens) {
        if (tokens.stream().anyMatch(t -> t < 0)) {
            throw new CalculatorException(TokensErrorCode.NEGATIVE_NUMBER_NOT_ALLOWED);
        }
    }
}
