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

    public int sum() {
        int sum = 0;
        for (int token : tokens) {
            sum = Math.addExact(sum, token);
        }
        return sum;
    }

    private void validateArguments(List<Integer> tokens) {
        if (tokens.stream().anyMatch(t -> t < 0)) {
            throw new CalculatorException(TokensErrorCode.NEGATIVE_NUMBER_NOT_ALLOWED);
        }
    }
}
