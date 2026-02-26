package com.example.MailSending.Service;

import com.example.MailSending.Configuration.MailProperties;


import jakarta.mail.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class ReadEmailService {

    @Autowired
    private final MailProperties mailProperties;

    public ReadEmailService(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    public void readEmails() {

        try {
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");

            Session emailSession = Session.getDefaultInstance(properties);

            Store store = emailSession.getStore("imaps");
            store.connect(
                    mailProperties.getHost(),
                    mailProperties.getUsername(),
                    mailProperties.getPassword()
            );

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();

            System.out.println("Total Messages: " + messages.length);

            for (int i = messages.length - 1; i >= messages.length - 5 && i >= 0; i--) {
                Message message = messages[i];
                System.out.println("Subject: " + message.getSubject());
            }

            emailFolder.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
