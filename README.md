# java-calculator-precourse

# 모호한 부분 정의

1. 맨 처음 만나는 “//”와 “\n”을 기준으로 내부의 ‘문자열’을 커스텀 구분자로 정의한다.
2. 구분자는 공백, 숫자가 포함될 수 없다.
3. 입출력을 포함한 모든 정수들은 2^31 - 1 이하이다. (벗어나면 오류 반환)
4. 정수들과 구분자들 사이에는 공백이 올 수 있다.
5. 구분자의 양 끝에 공백이 있는 경우는 공백을 제거한다.
6. “//”와 “\n”이 포함된 경우 구분자는 공백일 수 없다.

---

# 구현해야 할 사항

## 기능

- [x]  입력 담당 객체
- [x]  출력 담당 객체
- [x]  수식에서 커스텀 구분자를 추출하는 기능
- [x]  구분자를 통해 수식에서 정수를 분리하는 기능
- [x]  정수들을 모두 더하는 기능

## 고려해야 할 예외 사항들

### 구분자

- [x]  구분자가 빈 문자열인 경우
- [x]  구분자가 공백을 포함한 경우
- [x]  구분자가 숫자를 포함한 경우

### 계산

- [x]  입력으로 받은 정수들 중 int 범위를 벗어나는 값이 있는 경우
- [x]  정수들 사이에 구분자가 아닌 공백을 포함한 문자가 포함된 경우
- [x]  정수들의 합이 int 범위를 벗어나는 경우

## 고려해야 할 테스트 케이스들

- [x]  “//”와 “\n” 사이에 문자가 없거나 공백만 있는 경우
- [x]  “//”와 “\n”의 위치가 잘못된 경우
- [x]  구분자의 길이가 1 이상인 경우
- [x]  수식의 앞, 뒤에 공백이 포함된 경우
- [x]  정수들과 구분자들 사이에 공백이 포함된 경우
- [x]  구분자가 여러개 연달아 나오는 경우
- [x]  숫자가 없는 경우

---

# 프로젝트 구조 설계

```markdown
java
└── calculator
    ├── Application.class
    ├── controller
    │     └── CalculatorController.class
    ├── service
    │     ├── CalculatorService.class
    │     └── ParseService.class
    ├── model
    │     ├── Delimiter.class
    │     └── Tokens.class
    ├── view
    │     ├── CalculatorView.class
    │     └── handler
    │          ├── InputHandler.class
    │          └── OutputHandler.class
    └── exception
         ├── BaseErrorCode.interface
         ├── BaseException.class (IllegalArgumentException extend)
         ├── code
         │    └── [각 상황에 따른 에러코드].enum (BaseErrorCode implement)
         └── handler
              └── [각 상황에 따른 예외객체].class (BaseException extend)
```
