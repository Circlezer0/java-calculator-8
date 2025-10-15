package calculator.view;

import calculator.view.handler.InputHandler;
import calculator.view.handler.OutputHandler;

public class CalculatorView {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    private static final String MESSAGE = "덧셈할 문자열을 입력해주세요.";
    private static final String RESULT_MESSAGE = "결과 : ";

    public CalculatorView() {
        this.inputHandler = new InputHandler();
        this.outputHandler = new OutputHandler();
    }

    public String getInput() {
        outputHandler.printLine(MESSAGE);
        return inputHandler.readLine().trim();
    }

    public void displayOutput(int result) {
        outputHandler.printLine(RESULT_MESSAGE + result);
    }
}
