package com.spring.quiz;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@SessionScope
public class QuizSession {

    private List<QuizQuestion> quizQuestions;

    private QuizStatus quizStatus; // 진행 상태

    private int currentQuestion; // 최근 문제 번호

    private int totalScore; // 총 점수

    // 퀴즈 진행 전 퀴즈 생성
    public QuizSession() {
        this.quizQuestions = new ArrayList<>(Arrays.asList(
                new QuizQuestion("1. 3 + 3 = ?", "6"),
                new QuizQuestion("2. 딥다이브 백엔드 강사님 성함", "이지영"),
                new QuizQuestion("3. 2024 올림픽 개최국", "파리"),
                new QuizQuestion("4. 6.25 전쟁이 일어난 연도는?", "1950"),
                new QuizQuestion("5. 한국의 수도는 ?" , "서울")
        )
        );
        this.quizStatus = QuizStatus.BEFORE;
        this.currentQuestion = 0;
        this.totalScore = 0;
    }

    // 퀴즈 세션
    public QuizQuestion getCurrentQuestion() {
        /* 퀴즈 시작 시작 */
        if (currentQuestion == 0) {
            quizStatus= QuizStatus.PROGRESS;
            return quizQuestions.get(0);
        }
        return quizQuestions.get(currentQuestion);
    }

    // 정답 체크
    public void checkAnswer(String answer) {
        // 정답 일 경우
        if (answer.equals(getCurrentQuestion().getAnswer())) {
            totalScore++;
        }
        // 다음 문제 진행
        currentQuestion++;
    }

    // 퀴즈 유지 상태
    public boolean isFinish() {
        if (quizQuestions.size() <= currentQuestion) {
            quizStatus = QuizStatus.COMPLETED;
            return true;
        }
        return false;
    }

    public QuizStatus getQuizStatus() {
        return quizStatus;
    }

    public Integer getTotalScore() {
        return totalScore;
    }
}
