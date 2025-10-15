package calculator.model;

import java.util.List;

public class Tokens {

    private final List<Integer> tokens;

    public Tokens(List<Integer> tokens) {
        validateArguments(tokens);
        this.tokens = List.copyOf(tokens);
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
            throw new IllegalArgumentException("음수는 허용되지 않습니다.");
        }
    }
}
