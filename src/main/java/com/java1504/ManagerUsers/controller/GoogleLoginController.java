package com.java1504.ManagerUsers.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoogleLoginController {

    @GetMapping("/login/oauth2/code/google")
    public String getLoginInfo(@AuthenticationPrincipal OidcUser principal, Model model) {
        if (principal != null) {
            model.addAttribute("name", principal.getName());
            model.addAttribute("email", principal.getEmail());
            model.addAttribute("profilePicture", principal.getPicture());
            // Lấy các thông tin khác nếu cần
        }
        return "home2"; // Chuyển hướng đến trang home hoặc trang bạn muốn hiển thị thông tin
    }
}

