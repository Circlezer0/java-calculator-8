package calculator.service;

import calculator.dto.ParseHeaderResult;
import calculator.model.Delimiter;
import calculator.model.Tokens;
import java.util.Arrays;
import java.util.List;

public class ParseService {

    public ParseHeaderResult parseDelimiter(String input) {
        input = input.trim();
        if (input.startsWith("//")) {
            int endIdx = input.indexOf("\\n");
            String delimiter = input.substring(2, endIdx).trim();
            String formula = input.substring(endIdx + 2).trim();
            return new ParseHeaderResult(delimiter, formula);
        }
        return new ParseHeaderResult(null, input);
    }

    public Tokens parseTokens(String formula, Delimiter delimiter) {
        if (formula == null || formula.isBlank()) {
            return new Tokens(List.of(0));
        }

        String[] tokenStrings = formula.split(delimiter.getDelimiter());
        List<Integer> parsedTokens = Arrays.stream(tokenStrings)
                .map(String::trim)
                .mapToInt(this::stoi)
                .boxed()
                .toList();
        return new Tokens(parsedTokens);
    }

    private Integer stoi(String token) {
        if (token.isEmpty()) {
            return 0;
        }

        if (!token.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("토큰에 숫자가 아닌 문자가 포함되어 있습니다");
        }

        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("토큰이 정수 범위를 벗어났습니다");
        }
    }
}
