package com.spring.quiz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizSession quizSession;

    @Autowired
    private HttpSession httpSession;

    @GetMapping
    public String quizStart(Model model) {
        if (quizSession.isFinish()) {
            return "redirect:/quiz/result";
        }
        model.addAttribute("quiz", quizSession.getCurrentQuestion().getQuiz());
        return "quiz";
    }

    @PostMapping
    public String quizAnswer(@RequestParam String answer) {
        quizSession.checkAnswer(answer);
        return "redirect:/quiz";
    }

    @GetMapping("/result")
    public String quizResult(Model model) {
        model.addAttribute("status", quizSession.getQuizStatus().getValue());
        model.addAttribute("totalScore", quizSession.getTotalScore());
        return "quizResult";
    }

    @GetMapping("/restart")
    public String restartQuiz() {
        httpSession.invalidate();
        return "redirect:/quiz";
    }
}
