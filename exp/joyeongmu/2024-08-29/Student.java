package kakaoGoorm.exp.exp0829;

import java.util.HashMap;

public class Student {
    HashMap<String, Integer> scores = new HashMap<>();
    /**
     * 추가
     */
    public void addStudentWithScore(String name, Integer score) {
        this.scores.put(name, score);
    }
    /**
     * 조회
     */
    public void findStudentWithScore(String name) {
        System.out.println(name + "의 점수는" + scores.get(name));
    }
    /**
     * 모두 조회
     */
    public void findAll() {
        for (String s : scores.keySet()) {
            System.out.println(s + "의 점수는" + scores.get(s));

        }
    }
    /**
     * 삭제
     */
    public void deleteStudent(String name) {
        scores.remove(name);
    }
}
