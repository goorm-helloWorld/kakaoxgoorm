public class Main {
    public static void main(String[] args) {
        int[] a = new int[10];
        for(int i=1; i<=10; i++) {
            a[i-1] = i;
            System.out.print(a[i-1] + ", ");
        }
    }
}