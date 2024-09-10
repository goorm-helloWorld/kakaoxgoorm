package com.spring.exp.mission0905;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class QuizSession {
    private List<QuizQuestion> quizQuestions;
    private int nowQuizIndex;
    private int score;

    public QuizSession() {
        quizQuestions = new ArrayList<>();
        this.nowQuizIndex = 0;
        this.score = 0;
        quizQuestions.add(new QuizQuestion("살라의 등번호는?", "11"));
        quizQuestions.add(new QuizQuestion("최근 리버풀 대 맨유의 승자는?", "리버풀"));
        quizQuestions.add(new QuizQuestion("23-24시즌 리버풀의 리그 순위는?", "3"));
    }

    public QuizQuestion getCurrentQuestion() {
        if (nowQuizIndex < quizQuestions.size()) {
            return quizQuestions.get(nowQuizIndex);
        }
        return null;
    }

    public void submitAnswer(String answer) {
        QuizQuestion currentQuestion = getCurrentQuestion();
        if (currentQuestion != null && currentQuestion.getAnswer().equalsIgnoreCase(answer)) {
            score++;
        }
        nowQuizIndex++;
    }

    public int getScore() {
        return score;
    }

    public void restartQuiz() {
        this.nowQuizIndex = 0;
        this.score = 0;
    }
}
