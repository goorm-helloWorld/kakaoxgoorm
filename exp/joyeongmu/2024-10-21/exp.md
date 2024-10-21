최소 직사각형

```java
class Solution {
public int solution(int[][] sizes) {

        int wMax = 0, hMax = 0;

        for (int[] size : sizes) {
            wMax = Math.max(wMax, Math.max(size[0], size[1]));
            hMax = Math.max(hMax, Math.min(size[0], size[1]));
        }
        
        return wMax * hMax;
    }
}


```

모의고사
```java

class Solution {
public int[] solution(int[] answers) {

        // 문제를 찍는 패턴을 생성한다.
        int[][] pattern = {
            {1,2,3,4,5},
            {2,1,2,3,2,4,2,5},
            {3,3,1,1,2,2,4,4,5,5}
        };
        
        // 점수를 저장할 배열
        int[] scores = new int[3];
        
        // 각 수포자들의 패턴과 정답이 얼마나 일치하는지 확인
        for (int i = 0; i < answers.length; i++) {
            for(int j = 0; j < pattern.length; j++) {
                // 패턴과 답안이 일치하면 ++ 한다.
                // 정답 패턴이 길이가 수포자의 답안 길이보다 긴경우 처음 데이터와 다시 비교
                if(answers[i] == pattern[j][i % pattern[j].length]) { // answer가 더 긴경우 다시 처음 부터 비교
                    scores[j]++;
                }
            }
        }
        
        // 가장 높은 점수를 저장
        int maxScore = Arrays.stream(scores).max().getAsInt();
        
        ArrayList<Integer> answer = new ArrayList<>();
        
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == maxScore) {
                answer.add(i + 1);
            }
        }
        return answer.stream().mapToInt(x -> x.intValue()).toArray();
    }
}

```
