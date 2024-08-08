package com.java1504.ManagerUsers.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Chào mừng đến với trang chủ!");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }

    @GetMapping("/transfer")
    public String transfer(Model model) {
        return "transfer";
    }

    @GetMapping("/createBank")
    public String createBank(Model model) {
        return "createbank";
    }

    @GetMapping("/home2")
    public String home2(Model model) {
        return "home2";
    }

}
