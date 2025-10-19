package calculator.view;

import calculator.view.handler.InputHandler;
import calculator.view.handler.OutputHandler;

/**
 * CalculatorView는 사용자와의 입출력을 담당하는 클래스이다.
 * InputHandler와 OutputHandler를 사용하여 입력을 받고 출력을 한다.
 */
public class CalculatorView {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    private static final String MESSAGE = "덧셈할 문자열을 입력해주세요.";
    private static final String RESULT_MESSAGE = "결과 : ";

    public CalculatorView() {
        this.inputHandler = new InputHandler();
        this.outputHandler = new OutputHandler();
    }

    /**
     * 입력 안내 문구를 출력한 후, Console.readLine()을 통해 사용자 입력을 받는다.
     * EOF는 IllegalArgumentException 예외를 발생시킨다.
     *
     * @return 사용자 입력 문자열
     */
    public String getInput() {
        outputHandler.printLine(MESSAGE);
        return inputHandler.readLine().trim();
    }

    /**
     * 계산 결과를 출력한다.
     *
     * @param result 계산 결과 정수
     */
    public void displayOutput(int result) {
        outputHandler.printLine(RESULT_MESSAGE + result);
    }
}
