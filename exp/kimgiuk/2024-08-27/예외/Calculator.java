import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // 사용자로부터 두 숫자 입력받기
            System.out.print("첫 번째 숫자를 입력하세요: ");
            double num1 = scanner.nextDouble();

            System.out.print("두 번째 숫자를 입력하세요: ");
            double num2 = scanner.nextDouble();

            // 사용자로부터 연산자 입력받기
            System.out.print("연산자를 입력하세요 (+, -, *, /): ");
            char operator = scanner.next().charAt(0);

            // 계산 결과 출력
            double result = calculate(num1, num2, operator);
            System.out.println("결과: " + result);

        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력해 주세요.");
        } catch (ArithmeticException e) {
            System.out.println("0으로 나눌 수 없습니다.");
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다: " + e.getMessage());
        } finally {
            scanner.close(); // 스캐너 닫기
        }
    }

    // 사칙연산 계산 함수
    public static double calculate(double num1, double num2, char operator) throws ArithmeticException {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException(); // 0으로 나누는 경우 예외 처리
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("유효한 연산자가 아닙니다."); // 잘못된 연산자 처리
        }
    }
}

//첫 번째 숫자를 입력하세요: 10
//두 번째 숫자를 입력하세요: 0
//연산자를 입력하세요 (+, -, *, /): /
//0으로 나눌 수 없습니다.
//
//첫 번째 숫자를 입력하세요: 15
//두 번째 숫자를 입력하세요: 5
//연산자를 입력하세요 (+, -, *, /): +
//결과: 20.0
//
//첫 번째 숫자를 입력하세요: 20
//두 번째 숫자를 입력하세요: 4
//연산자를 입력하세요 (+, -, *, /): %
//오류가 발생했습니다: 유효한 연산자가 아닙니다.
