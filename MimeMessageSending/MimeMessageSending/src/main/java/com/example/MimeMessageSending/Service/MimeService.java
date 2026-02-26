package com.example.MimeMessageSending.Service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class MimeService {

    @Autowired
   private JavaMailSender mailSender;

public void sendHtmlWithAttachment(
        String to,
        String subject,
        String HtmlBody,
        MultipartFile attachment
) throws MessagingException , IOException{

    MimeMessage message=mailSender.createMimeMessage();

    MimeMessageHelper helper=new MimeMessageHelper(message,true,"UTF 8");

    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(HtmlBody,true);

    File tempfile=File.createTempFile("upload",attachment.getOriginalFilename());
    attachment.transferTo(tempfile);


    helper.addAttachment(attachment.getOriginalFilename(),tempfile);

    mailSender.send(message);

    tempfile.delete();


}

}
