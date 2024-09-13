package com.example.mvcproject.controller;

import com.example.mvcproject.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    // http://localhost:8081/user/signup
    @RequestMapping("/signup")
    public String signup(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody   // ViewResolver에서 View 를 반환하는게 아닌 순수 리턴값만 반환한다.
    public String signup(@ModelAttribute("user") User user) {
        System.out.println(user);

        return "redirect:/user/signup";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("user") User user, Model model) {
        if(User.auth(user.getEmail(), user.getPassword())) {
            model.addAttribute("user", user);
            return "loginSuccess";
        } else {
            return "redirect:/user/login";
        }
    }
}
