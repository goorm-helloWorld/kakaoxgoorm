import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};

        System.out.println("배열의 평균 : " + Arrays.stream(a).average().getAsDouble());

        a[0] = 10;
        a[1] = 20;

        System.out.println("변경된 배열의 평균 : " + Arrays.stream(a).average().getAsDouble());
        System.out.println("변경된 배열 : " + Arrays.toString(a));
    }
}