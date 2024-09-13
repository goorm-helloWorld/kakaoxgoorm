import java.util.Scanner;

@FunctionalInterface
interface MathOperation {
    double apply(double a, double b);
}

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 사용자로부터 숫자와 연산자 입력 받기
        System.out.print("첫 번째 숫자를 입력하세요: ");
        double num1 = scanner.nextDouble();

        System.out.print("두 번째 숫자를 입력하세요: ");
        double num2 = scanner.nextDouble();

        System.out.print("연산자를 입력하세요 (+, -, *, /): ");
        String operator = scanner.next();

        MathOperation operation;

        // 2. 연산자에 따라 람다식을 사용하여 연산 수행
        switch (operator) {
            case "+":
                operation = (a, b) -> a + b;
                break;
            case "-":
                operation = (a, b) -> a - b;
                break;
            case "*":
                operation = (a, b) -> a * b;
                break;
            case "/":
                if (num2 == 0) {
                    System.out.println("0으로 나눌 수 없습니다.");
                    return;
                }
                operation = (a, b) -> a / b;
                break;
            default:
                System.out.println("잘못된 연산자입니다.");
                return;
        }

        // 3. 연산 수행 및 결과 출력
        double result = operation.apply(num1, num2);
        System.out.println("결과: " + result);

        scanner.close();
    }
}

//첫 번째 숫자를 입력하세요: 5
//두 번째 숫자를 입력하세요: 10
//연산자를 입력하세요 (+, -, *, /): +
//결과: 15.0
