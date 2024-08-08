package com.java1504.ManagerUsers.controller;

import com.java1504.ManagerUsers.service.impl.OAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2LoginController {

    @Autowired
    private OAuth2UserService oAuth2UserService;


    @GetMapping("/oauth2/loginSuccess")
    public String oauth2LoginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User, Model model) {
        String token =  oAuth2UserService.getUserAttributes(oAuth2User);
        model.addAttribute("name", oAuth2User.getAttribute("name"));
        model.addAttribute("email", oAuth2User.getAttribute("email"));
        model.addAttribute("picture", oAuth2User.getAttribute("picture"));
        model.addAttribute("token", token);
        return "oauth2LoginSuccess";
    }

    @GetMapping("/oauth2/loginFailure")
    public String oauth2LoginFailure() {
        return "oauth2LoginFailure";
    }
}
