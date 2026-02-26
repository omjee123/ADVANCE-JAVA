package com.example.MimeMessageSending.Service;

import com.example.MimeMessageSending.Configuration.Config;
import jakarta.mail.*;
import jakarta.mail.search.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

@Service
public class ReadUnseenMail {

    private final Config config;

    public ReadUnseenMail(Config config) {
        this.config = config;
    }

    public Properties getPropertis() {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", config.getProtocol());
        properties.put("mail.imaps.host", config.getHost());
        properties.put("mail.imaps.port", String.valueOf(config.getPort()));
        properties.put("mail.imaps.ssl.enable", String.valueOf(config.isSslEnable()));
        return properties;
    }

    public Session getSession() {
        return Session.getInstance(getPropertis());
    }

    public Store getStore() {
        try {
            Store store = getSession().getStore(config.getProtocol());
            store.connect(
                    config.getHost(),
                    config.getUser(),
                    config.getPassword()
            );
            return store;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // Unseen Mail Method
    public void unSeen() {
        try {
            Store store = getStore();

            Folder folder = store.getFolder(config.getFolders().get(0));
            folder.open(Folder.READ_ONLY);

            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagterm = new FlagTerm(seen, false);

            Message[] unseenMessages = folder.search(unseenFlagterm);

            System.out.println("Total Unseen Mail: " + unseenMessages.length);

            for (Message message : unseenMessages) {
                printMail(message);
            }

            folder.close(false);
            store.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //  Read Mail By Date

    public void readMailByDate(String dateInput) {

        try {
            Store store = getStore();
            Folder folder = store.getFolder(config.getFolders().get(0));
            folder.open(Folder.READ_ONLY);

            LocalDate localDate = LocalDate.parse(dateInput.trim());

            Date startDate = Date.from(
                    localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
            );

            Date endDate = Date.from(
                    localDate.plusDays(1)
                            .atStartOfDay(ZoneId.systemDefault())
                            .toInstant()
            );

            ReceivedDateTerm after =
                    new ReceivedDateTerm(ComparisonTerm.GE, startDate);

            ReceivedDateTerm before =
                    new ReceivedDateTerm(ComparisonTerm.LT, endDate);

            AndTerm dateRange = new AndTerm(after, before);

            Message[] messages = folder.search(dateRange);

            System.out.println("Total Mail On That Date: " + messages.length);

            for (Message message : messages) {
                printMail(message);
            }

            folder.close(false);
            store.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





    // Reusable Print Method (Clean Code)

    private void printMail(Message message) throws Exception {

        System.out.println("Subject: " + message.getSubject());
        System.out.println("From: " + message.getFrom()[0]);
        System.out.println("Date: " + message.getReceivedDate());
        System.out.println("--------------------------------");
    }
}
