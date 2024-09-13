package com.example.mvcproject.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class QuizSession {

    private List<QuizQuestion> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    public QuizSession() {
        questions = new ArrayList<>();
        questions.add(new QuizQuestion("프랑스의 수도는?", "파리"));
        questions.add(new QuizQuestion("5 + 3 = ?", "8"));
        questions.add(new QuizQuestion("한국의 수도는?", "서울"));
    }

    public QuizQuestion getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }

    public void answerCurrentQuestion(String answer) {
        if (questions.get(currentQuestionIndex).getAnswer().equalsIgnoreCase(answer)) {
            score++;
        }
        currentQuestionIndex++;
    }

    public boolean isQuizFinished() {
        return currentQuestionIndex >= questions.size();
    }

    public int getScore() {
        return score;
    }

    public void restartQuiz() {
        currentQuestionIndex = 0;
        score = 0;
    }
}
