import java.io.*;
import java.util.*;

public class test {
    HashMap<String, Integer> h = new HashMap<>();  // 학생 이름과 점수 저장할 HashMap

    // 1. 학생 정보 추가
    public void add(String key, int value) {
        h.put(key, value);
    }

    // 2. 특정 학생 정보 조회
    public void get(String key) {
        System.out.println(h.get(key));
    }

    // 3. 특정 학생 정보 삭제
    public void remove(String key) {
        h.remove(key);
    }

    // 4. 전체 학생 정보 출력
    public void print() {
        for(int i = 0; i < h.size(); i++) {
            System.out.println("학생: " + h.keySet().toArray()[i] + ", 점수: " + h.values().toArray()[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        test test = new test();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("작업을 선택하세요 (1: 추가, 2: 조회, 3: 삭제, 4: 전체 출력) : ");
            switch (Integer.parseInt(br.readLine())) {
                case 1: // 학생 추가
                    System.out.print("학생 이름과 점수를 입력: ");
                    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                    test.add(st.nextToken(), Integer.parseInt(st.nextToken()));
                    break;
                case 2: // 학생 조회
                    System.out.print("조회할 학생 이름 입력: ");
                    test.get(br.readLine());
                    break;
                case 3: // 학생 삭제
                    System.out.print("삭제할 학생 이름 입력: ");
                    test.remove(br.readLine());
                    break;
                case 4: // 전체 출력
                    test.print();
                    break;
            }
        }
    }
}

//작업을 선택하세요 (1: 추가, 2: 조회, 3: 삭제, 4: 전체 출력) : 1
//학생 이름과 점수를 입력: giuk 100
//
//작업을 선택하세요 (1: 추가, 2: 조회, 3: 삭제, 4: 전체 출력) : 2
//조회할 학생 이름 입력: giuk
//100
//
//작업을 선택하세요 (1: 추가, 2: 조회, 3: 삭제, 4: 전체 출력) : 4
//학생: giuk, 점수: 100
