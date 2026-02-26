package com.example.MailSending.Service;


import com.example.MailSending.Configuration.MailConfig;
import jakarta.mail.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class ExamplaReadMail {

    @Autowired
    private  final MailConfig mailConfig;

    public <MailConfig> ExamplaReadMail(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
    }

    public void readEmail() throws NoSuchProviderException {
        try{
            Properties properties=new Properties();
            properties.put("mail.store.protocol","imaps");

            Session emailSession= Session.getDefaultInstance(properties);

            Store store=emailSession.getStore("imaps");

            store.connect(
                    mailConfig.gethost(),
                    mailConfig.getusername(),
                    mailConfig.getPassword());

            Folder emailFolder=store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages=emailFolder.getMessages();
            System.out.println("Total Message:"+ messages.length);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
