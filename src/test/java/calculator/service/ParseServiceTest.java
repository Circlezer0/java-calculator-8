package calculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import calculator.dto.ParseHeaderResult;
import calculator.model.Delimiter;
import calculator.model.Tokens;
import org.junit.jupiter.api.Test;

public class ParseServiceTest {

    @Test
    void 커스텀_구분자_없을때_파싱() {
        ParseService parseService = new ParseService();
        String input = "1,2,3";
        ParseHeaderResult parsedDelimiter = parseService.parseDelimiter(input);
        String formula = parsedDelimiter.formula();
        String delimiter = parsedDelimiter.delimiter();

        assertEquals("1,2,3", formula);
        assertNull(delimiter);
    }

    @Test
    void 커스텀_구분자_있을때_파싱() {
        ParseService parseService = new ParseService();
        String input = "//;\\n1;2;3";
        ParseHeaderResult parsedDelimiter = parseService.parseDelimiter(input);
        String formula = parsedDelimiter.formula();
        String delimiter = parsedDelimiter.delimiter();
        assertEquals("1;2;3", formula);
        assertEquals(";", delimiter);
    }

    @Test
    void 커스텀_구분자_여러글자_있을때_파싱() {
        ParseService parseService = new ParseService();
        String input = "//***\\n1***2***3";
        ParseHeaderResult parsedDelimiter = parseService.parseDelimiter(input);
        String formula = parsedDelimiter.formula();
        String delimiter = parsedDelimiter.delimiter();
        assertEquals("1***2***3", formula);
        assertEquals("***", delimiter);
    }

    @Test
    void 공백을_포함한_구분자_파싱_테스트() {
        ParseService parseService = new ParseService();
        String input = "// ; ;\\n 1; 2; 3 ";
        ParseHeaderResult parsedResult = parseService.parseDelimiter(input);
        String formula = parsedResult.formula();
        String delimiter = parsedResult.delimiter();
        assertEquals("1; 2; 3", formula);
        assertEquals("; ;", delimiter);
    }

    @Test
    void 기본_구분자로_수식_파싱_테스트() {
        ParseService parseService = new ParseService();
        String formula = "1,2:3";
        Delimiter delimiter = new Delimiter();
        Tokens tokens = parseService.parseTokens(formula, delimiter);

        assertEquals(3, tokens.getTokens().size());
        assertEquals(1, tokens.getTokens().get(0));
        assertEquals(2, tokens.getTokens().get(1));
        assertEquals(3, tokens.getTokens().get(2));
    }

    @Test
    void 커스텀_구분자로_수식_파싱_테스트() {
        ParseService parseService = new ParseService();
        String formula = "1,2:3;4";
        Delimiter delimiter = new Delimiter(";");
        Tokens tokens = parseService.parseTokens(formula, delimiter);
        assertEquals(4, tokens.getTokens().size());
        assertEquals(1, tokens.getTokens().get(0));
        assertEquals(2, tokens.getTokens().get(1));
        assertEquals(3, tokens.getTokens().get(2));
        assertEquals(4, tokens.getTokens().get(3));
    }

    @Test
    void 구분자_연달아_나오는_경우(){
        ParseService parseService = new ParseService();
        String formula = ":1,, 2 : :3,"; // split은 마지막 빈 토큰은 버리도록 동작함
        Delimiter delimiter = new Delimiter();
        Tokens tokens = parseService.parseTokens(formula, delimiter);
        assertEquals(6, tokens.getTokens().size());

        assertEquals(0, tokens.getTokens().get(0));
        assertEquals(1, tokens.getTokens().get(1));
        assertEquals(0, tokens.getTokens().get(2));
        assertEquals(2, tokens.getTokens().get(3));
        assertEquals(0, tokens.getTokens().get(4));
        assertEquals(3, tokens.getTokens().get(5));
    }


    @Test
    void 문자_포함_수식_입력시_IllegalArgumentException() {
        ParseService parseService = new ParseService();
        String formula = "1,2:A";
        Delimiter delimiter = new Delimiter();
        assertThrows(IllegalArgumentException.class, () -> parseService.parseTokens(formula, delimiter));
    }

    @Test
    void int_범위를_벗어난_수식_입력시_IllegalArgumentException() {
        ParseService parseService = new ParseService();
        String formula = "1,2:2147483648";
        Delimiter delimiter = new Delimiter();
        assertThrows(IllegalArgumentException.class, () -> parseService.parseTokens(formula, delimiter));
    }


}
