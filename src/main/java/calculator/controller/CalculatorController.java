package calculator.controller;

import calculator.dto.ParseHeaderResult;
import calculator.model.Delimiter;
import calculator.model.Tokens;
import calculator.service.CalculatorService;
import calculator.service.ParseService;
import calculator.view.CalculatorView;

public class CalculatorController {

    private final CalculatorView calculatorView;
    private final CalculatorService calculatorService;
    private final ParseService parseService;

    public CalculatorController() {
        this.calculatorView = new CalculatorView();
        this.calculatorService = new CalculatorService();
        this.parseService = new ParseService();
    }

    public void run() {
        // 1. 사용자 입력 받기
        String input = calculatorView.getInput();

        // 2. 구분자 및 수식 파싱
        ParseHeaderResult parseHeaderResult = parseService.parseDelimiter(input);
        String formula = parseHeaderResult.formula();
        String delimiterString = parseHeaderResult.delimiter();

        // 3. 구분자 객체 생성
        Delimiter delimiter = (delimiterString == null) ?
                new Delimiter() :
                new Delimiter(delimiterString);

        Tokens tokens = parseService.parseTokens(formula, delimiter);

        int result = calculatorService.calculateSum(tokens);
        calculatorView.displayOutput(result);
    }
}
