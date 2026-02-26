package com.example.MailSending.Controller;

import com.example.MailSending.Service.MimeEmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/MimeEmail")
public class MimeEmailController {
    @Autowired
    private final MimeEmailService mime;

    public MimeEmailController(MimeEmailService mime) {
        this.mime = mime;
    }
 @PostMapping("/sendMime")
    public String SendMime(@RequestParam MultipartFile file,@RequestParam String to,
                           @RequestParam String subject,
                           @RequestParam String htmlBody) throws Exception{

        mime.sendHtmlWithAttachment(
                to,subject,htmlBody,file
        );
     return "Email Send Success Email";
 }
}
