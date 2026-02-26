package com.example.MailSending.Controller;

import com.example.MailSending.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendMail() {
        emailService.SendEmail(
                "user@gmail.com",
                "Account Created",
                "Your bank account has been successfully created."
        );
        return "Email Sent Successfully";
    }
}
