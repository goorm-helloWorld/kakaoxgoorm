import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("정수를 입력하세요 : ");
        int a = sc.nextInt();
        sc.close();

        System.out.println((a%2 == 0) ? "짝수" : "홀수");
    }
}