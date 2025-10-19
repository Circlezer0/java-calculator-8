package calculator.controller;

import calculator.dto.ParseHeaderResult;
import calculator.model.Delimiter;
import calculator.model.Tokens;
import calculator.service.CalculatorService;
import calculator.service.ParseService;
import calculator.view.CalculatorView;

/**
 * CalculatorController는 View와 Service를 활용하여
 * 덧셈 계산기 애플리케이션의 흐름을 제어하는 클래스이다.
 */
public class CalculatorController {

    private final CalculatorView calculatorView;        // 사용자 입출력 담당
    private final CalculatorService calculatorService;  // 덧셈 계산 로직 담당
    private final ParseService parseService;            // 구분자, 수식 파싱 담당

    public CalculatorController() {
        this.calculatorView = new CalculatorView();
        this.calculatorService = new CalculatorService();
        this.parseService = new ParseService();
    }

    /**
     * 덧셈 계산기 애플리케이션의 주요 흐름을 실행하는 메서드이다.
     */
    public void run() {
        // 1. 사용자 입력 받기
        String input = calculatorView.getInput();

        // 2. 입력으로 받은 수식을 (커스텀 구분자, 수식) 두 부분으로 파싱
        ParseHeaderResult parseHeaderResult = parseService.parseDelimiter(input);
        String formula = parseHeaderResult.formula();
        String delimiterString = parseHeaderResult.delimiter();

        // 3. 구분자 객체 생성
        Delimiter delimiter = (delimiterString == null) ?
                new Delimiter() :                   // 커스텀 구분자가 없는 경우
                new Delimiter(delimiterString);     // 커스텀 구분자가 있는 경우

        // 4. 구분자 객체를 활용하여 나머지 수식에서 숫자들을 파싱
        Tokens tokens = parseService.parseTokens(formula, delimiter);

        // 5. 파싱된 숫자들에 대해 덧셈 계산 수행
        int result = calculatorService.calculateSum(tokens);

        // 6. 계산 결과 출력
        calculatorView.displayOutput(result);
    }
}
