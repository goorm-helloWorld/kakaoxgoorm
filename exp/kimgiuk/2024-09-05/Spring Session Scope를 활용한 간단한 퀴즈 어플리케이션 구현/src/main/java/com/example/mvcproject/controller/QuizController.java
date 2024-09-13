package com.example.mvcproject.controller;

import com.example.mvcproject.domain.QuizSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {

    private final QuizSession quizSession;

    @Autowired
    public QuizController(QuizSession quizSession) {
        this.quizSession = quizSession;
    }

    @GetMapping("/quiz")
    public String startQuiz(Model model) {
        if (quizSession.isQuizFinished()) {
            return "redirect:/quiz-result";
        }

        model.addAttribute("question", quizSession.getCurrentQuestion());
        return "quiz";
    }

    @PostMapping("/quiz")
    public String submitAnswer(@RequestParam("answer") String answer) {
        quizSession.answerCurrentQuestion(answer);

        if (quizSession.isQuizFinished()) {
            return "redirect:/quiz-result";
        }

        return "redirect:/quiz";
    }

    @GetMapping("/quiz-result")
    public String quizResult(Model model) {
        model.addAttribute("score", quizSession.getScore());
        return "quiz-result";
    }

    @GetMapping("/restart")
    public String restartQuiz() {
        quizSession.restartQuiz();
        return "redirect:/quiz";
    }
}
