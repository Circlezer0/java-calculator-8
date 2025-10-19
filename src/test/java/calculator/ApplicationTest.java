package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    // 정상 로직 테스트

    @Test
    void 빈_수식_테스트() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 덧셈_테스트() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 커스텀_구분자_사용_1() {
        assertSimpleTest(() -> {
            run("//;\\n1;2:3,4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 여러_글자_커스텀_구분자() {
        assertSimpleTest(() -> {
            run("//***\\n1***2***3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 공백을_포함한_수식_사용() {
        assertSimpleTest(() -> {
            run("// ; \\n 1; 2; 3 ");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 구분자가_동시에_여러개_입력된_경우(){
        assertSimpleTest(() -> {
            run("//;\\n1;;2 ; : 3,4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 수식_양끝에_구분자가_오는_경우(){
        assertSimpleTest(() -> {
            run(",1:2,3:4,");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 구분자만_오는_경우(){
        assertSimpleTest(() -> {
            run(",,,::,,");
            assertThat(output()).contains("결과 : 0");
        });
    }

    // 예외 테스트

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 공백_구분자_예외_테스트(){
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("// \\n1;2;3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 문자_사이_공백_구분자_예외_테스트(){
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("//; ;\\n1; 2; 3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 정수_범위를_벗어난_입력_예외_테스트(){
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("1,2,2147483648"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 계산_결과가_정수_범위를_벗어나는_예외_테스트(){
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("2000000000,2000000000"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 숫자_사이에_공백이_포함된_경우_예외_테스트(){
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("1,2 3,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 수식에_구분자가_아닌_문자가_오는_경우_예외_테스트(){
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("1,2:A"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
