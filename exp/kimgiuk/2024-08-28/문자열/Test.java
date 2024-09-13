import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        // 1. 사용자로부터 문자열 입력 받기
        Scanner scanner = new Scanner(System.in);
        System.out.print("문자열을 입력하세요: ");
        String input = scanner.nextLine();

        // 2. 입력받은 문자열 뒤집기
        String reversedString = new StringBuilder(input).reverse().toString();

        // 3. 뒤집은 문자열의 대소문자 변환
        StringBuilder convertedString = new StringBuilder();
        for (char c : reversedString.toCharArray()) {
            if (Character.isUpperCase(c)) {
                convertedString.append(Character.toLowerCase(c));
            } else if (Character.isLowerCase(c)) {
                convertedString.append(Character.toUpperCase(c));
            } else {
                convertedString.append(c); // 알파벳이 아니면 그대로 추가
            }
        }

        // 4. 변환된 문자열 출력
        System.out.println("변환된 문자열: " + convertedString.toString());
    }
}

//문자열을 입력하세요: Hello World!
//변환된 문자열: !DLROw OLLEh
