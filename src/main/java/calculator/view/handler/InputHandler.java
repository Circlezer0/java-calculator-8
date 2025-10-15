package calculator.view.handler;

import camp.nextstep.edu.missionutils.Console;

public class InputHandler {
    public String readLine() {
        try {
            return Console.readLine();
        } catch (Exception e) {
            throw new IllegalArgumentException("입력값이 올바르지 않습니다.");
        }
    }
}
