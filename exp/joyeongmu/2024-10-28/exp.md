### 1 K 번째 수

```java
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        // commands 길이만큼 실행
        for (int s = 0; s < commands.length; s++) {
            
            int i = commands[s][0];
            int j = commands[s][1];
            int k = commands[s][2];
            
            int[] subArray = Arrays.copyOfRange(array, i - 1, j);
            
            Arrays.sort(subArray);
            
            answer[s] = subArray[k - 1];
        }
        
        return answer;
    }
}
```

### 2 가장 큰수

- compareTo 자세히 몰라서 다시 학습 후 풀이

```java
import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        
         String[] strs = Arrays.stream(numbers)
             .mapToObj(String::valueOf)
             .toArray(String[]::new);

        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
        
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            answer.append(strs[i]);
        }
        
        return answer.toString().startsWith("0") ? "0" : answer.toString();
    }
}
```

### 3 H-Index

1차 실패 → H-index를 잘못이해

2차 실패 → stream filter로 처리하려고 하니 내부 반복 돌리기가 애매했음

3차 실패 → 인용수 최대를 고려못하고 == 으로 처리했음 테스트케이스 16 한개 통과

4차 성공

```java
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++) {
            int h = citations.length - i;
            if (citations[i] >= h) {
                answer = h;
                break;
            }
        }
        return answer;
    }
}
```