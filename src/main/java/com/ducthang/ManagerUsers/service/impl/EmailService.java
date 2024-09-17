package com.ducthang.ManagerUsers.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    // Method to generate a random 6-digit number
    public int genRandomNumber() {
        return 100000 + (int) (Math.random() * 900000);
    }

    // Method to send an HTML email
    public void sendHtmlEmail(String toEmail, String subject, int verificationCode) throws MessagingException {
        String htmlContent = "<html><body>" +
                "<p style='font-family: sans-serif; font-size: 16px;'>Mã xác thực chuyển tiền của bạn là:</p>" +
                "<p style='font-family: sans-serif; font-size: 24px; font-weight: bold; color: #007bff; " +
                "background-color: #e0f7fa; border-radius: 5px; padding: 10px; text-align: center; " +
                "width: 150px; margin: auto;'>" + verificationCode + "</p>" +
                "</body></html>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(htmlContent, true); // true indicates that the content is HTML
        helper.setFrom("ducthang7944@hotmail.com"); // Use your email here

        mailSender.send(message);
    }
}