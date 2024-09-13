class NumberThread extends Thread {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("NumberThread: " + i);
            try {
                Thread.sleep(100); // 100ms 동안 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class LetterThread extends Thread {
    public void run() {
        for (char c = 'A'; c <= 'J'; c++) {
            System.out.println("LetterThread: " + c);
            try {
                Thread.sleep(100); // 100ms 동안 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Test {
    public static void main(String[] args) {
        // 숫자 출력 스레드 생성
        NumberThread numberThread = new NumberThread();

        // 문자 출력 스레드 생성
        LetterThread letterThread = new LetterThread();

        // 스레드 시작
        numberThread.start(); // 숫자 출력 스레드 시작
        letterThread.start(); // 문자 출력 스레드 시작
    }
}

//LetterThread: A
//NumberThread: 1
//LetterThread: B
//NumberThread: 2
//LetterThread: C
//NumberThread: 3
//        ...
