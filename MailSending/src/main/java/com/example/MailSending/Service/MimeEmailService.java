package com.example.MailSending.Service;

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
public class MimeEmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendHtmlWithAttachment(
            String to,
            String subject,
            String htmlBody,
            MultipartFile attachment)
            throws MessagingException, IOException {

        // Create MimeMessage
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true); // true = HTML

        // Convert MultipartFile to File
        File tempFile = File.createTempFile("upload-", attachment.getOriginalFilename());
        attachment.transferTo(tempFile);

        // Add attachment
        helper.addAttachment(
                attachment.getOriginalFilename(),
                tempFile
        );

        // Send mail
        mailSender.send(message);

        // Delete temp file
        tempFile.delete();
    }
}
