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
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
        return new Tokens(parsedTokens);
    }

}
