package calculator.service;

import calculator.dto.ParseHeaderResult;
import calculator.exception.CalculatorException;
import calculator.exception.code.ParseErrorCode;
import calculator.model.Delimiter;
import calculator.model.Tokens;
import java.util.Arrays;
import java.util.List;

/**
 * ParseService는 입력된 문자열을 파싱하는 기능을 제공하는 클래스이다.
 */
public class ParseService {

    private static final String DELIMITER_PREFIX = "//";
    private static final String DELIMITER_SUFFIX = "\\n";

    /**
     * 입력된 문자열에서 커스텀 구분자와 수식을 분리하여 반환한다.
     * 구분자가 없는 경우, delimiter는 null로 반환된다.
     * @param input 입력된 문자열
     * @return (커스텀 구분자, 수식) 형태의 ParseHeaderResult 객체
     */
    public ParseHeaderResult parseDelimiter(String input) {
        input = input.trim();

        // 수식이 //으로 시작하고 \n을 포함하는 경우, 커스텀 구분자가 존재한다고 판단
        if (input.startsWith(DELIMITER_PREFIX) && input.contains(DELIMITER_SUFFIX)) {
            // 맨 처음으로 오는 \n을 기준으로 커스텀 구분자의 범위를 설정
            int suffixIdx = input.indexOf(DELIMITER_SUFFIX);

            // 커스텀 구분자와 수식 부분을 분리
            String delimiter = input.substring(DELIMITER_PREFIX.length(), suffixIdx).trim();
            String formula = input.substring(suffixIdx + DELIMITER_SUFFIX.length()).trim();
            return new ParseHeaderResult(delimiter, formula);
        }
        return new ParseHeaderResult(null, input);
    }

    /**
     * 주어진 수식을 구분자 객체를 기준으로 토큰화하여 반환한다.
     * @param formula 수식 문자열
     * @param delimiter 구분자 객체
     * @return 토큰화된 정수 리스트를 포함하는 Tokens 객체
     */
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

    /**
     * 문자열 토큰을 정수로 변환한다.
     * 빈 문자열의 경우 0으로 변환한다.
     * 토큰에 숫자가 아닌 문자가 포함된 경우, 또는 정수 범위를 초과하는 경우 예외를 발생시킨다.
     * @param token 문자열 토큰
     * @return 정수로 변환된 값
     */
    private Integer stoi(String token) {
        if (token.isEmpty()) {
            return 0;
        }

        if (!token.chars().allMatch(Character::isDigit)) {
            throw new CalculatorException(ParseErrorCode.CONTAINS_NON_DIGIT);
        }

        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new CalculatorException(ParseErrorCode.INTEGER_OVERFLOW);
        }
    }
}
