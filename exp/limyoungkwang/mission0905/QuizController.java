package com.spring.exp.mission0905;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {
    private final QuizSession quizSession;

    public QuizController(QuizSession quizSession) {
        this.quizSession = quizSession;
    }
    // 퀴즈 시작
    @GetMapping("/quiz")
    public String showQuiz(Model model) {
        QuizQuestion currentQuestion =  quizSession.getCurrentQuestion();
        if (currentQuestion == null) {
            return "redirect:/quiz-result";
        }
        model.addAttribute("question", currentQuestion.getQuestion());
        return "quiz";
    }
    // 퀴즈 제출
    @PostMapping("/quiz")
    public String quizSubmit(@RequestParam String answer) {
        quizSession.submitAnswer(answer);
        return "redirect:/quiz";
    }
    //퀴즈 결과
    @GetMapping("/quiz-result")
    public String quizResult(Model model) {
        model.addAttribute("score", quizSession.getScore());
        return "quizResult";
    }
    //퀴즈 재시작
    @GetMapping("/restart")
    public String restart() {
        quizSession.restartQuiz();
        return "redirect:/quiz";
    }
}
