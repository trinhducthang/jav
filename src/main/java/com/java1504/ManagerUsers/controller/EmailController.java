package com.java1504.ManagerUsers.controller;

import com.java1504.ManagerUsers.service.impl.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    private static int codeVerified;


    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String toEmail) {
        try {
            int verificationCode = emailService.genRandomNumber();
            codeVerified = verificationCode;
            emailService.sendHtmlEmail(toEmail, "Mã xác thực chuyển tiền", verificationCode);
            // Store the verification code temporarily (e.g., in a database or cache) to verify later
            // For demonstration purposes, this code is not stored
            System.out.println("Send successfully!");
            return "Send successfully!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send email";
        }
    }

    @PostMapping("/verify-code")
    public String verifyCode(@RequestBody VerificationRequest request) {
        // Verify the code here (you need to implement code storage and validation)
        // This is a placeholder for demonstration
        if (isValidCode(request.getCode())) {
            return "{\"valid\": true}";
        } else {
            return "{\"valid\": false}";
        }
    }

    private boolean isValidCode(int code) {
        if (codeVerified == code) {
            return true;
        }
        return false;
    }

    // Class for handling verification requests
    public static class VerificationRequest {
        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
