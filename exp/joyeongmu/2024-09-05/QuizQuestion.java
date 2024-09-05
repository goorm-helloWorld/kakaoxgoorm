package com.spring.quiz;

/**
 * 퀴즈와 문제를 정의한 문제지 + 답안지
 */
public class QuizQuestion {

    private String quiz;

    private String answer;

    // 퀴즈 생성
    public QuizQuestion(String quiz, String answer) {
        this.quiz = quiz;
        this.answer = answer;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
