import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        char[][] a = new char[5][3];

        for(int i=0; i<5; i++) {
            for(int j=0; j<3; j++) {
                a[i][j] = sc.next().charAt(0);
                System.out.print(Character.toUpperCase(a[i][j]) + " ");
            }
            System.out.println();
        }
    }
}