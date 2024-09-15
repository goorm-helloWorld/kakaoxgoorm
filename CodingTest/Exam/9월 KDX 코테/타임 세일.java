//문제
//구름이는 그토록 기다려왔던 쇼핑몰 타임 세일이 내일 C시부터 30분 동안 진행된다는 것을 알게 되었다. 하지만 구름이는 규칙적인 수면이 가장 중요하다고 생각해서, 반드시 매일 A시부터 B시까지 수면을 한다. 이때, 24시간 미만으로 수면을 한다.
//구름이의 수면 시간과 타임 세일 시각이 주어질 때, 구름이가 타임 세일에 참가할 수 있는지 알아보자. 이때, 하루는 24시간으로 이루어져 있다.
//
//입력
//첫째 줄에 A, B, C가 공백을 두고 주어진다.
//• 0 ≤ A, B, C <24
//• A, B, C는 서로 다르다.
//• 입력으로 주어지는 모든 수는 정수이다.
//출력
//구름이가 타임세일에 참가할 수 있으면 Yes, 없다면 No 를 출력한다.
//
//예시 1
//입력
//0 8 12
//출력
//Yes
//
//예시 2
//입력
//16 14 12
//출력
//No

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // b < a 일때
        boolean isSleep = false;

        if(a<b) {
            // 수면시간 하루 이내 (00~24시)
            if(c >= a && c < b) {
                isSleep = true;
            }
        } else {
            // 수면시간 하루 넘김
            if(c >= a || c < b) {
                isSleep = true;
            }
        }

        System.out.println(isSleep ? "No" : "Yes");
    }
}