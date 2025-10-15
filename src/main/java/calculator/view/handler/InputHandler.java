package calculator.view.handler;

import calculator.exception.CalculatorException;
import calculator.exception.code.IOErrorCode;
import camp.nextstep.edu.missionutils.Console;

public class InputHandler {
    public String readLine() {
        try {
            return Console.readLine();
        } catch (Exception e) {
            throw new CalculatorException(IOErrorCode.INPUT_ERROR);
        }
    }
}
