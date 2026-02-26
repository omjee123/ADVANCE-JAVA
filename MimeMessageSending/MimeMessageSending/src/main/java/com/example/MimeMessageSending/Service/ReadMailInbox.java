//package com.example.MimeMessageSending.Service;
//
//import com.example.MimeMessageSending.Configuration.Config;
//import jakarta.mail.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Properties;
//
//@Service
//
//public class ReadMailInbox {
//
//
//
//    public List<String> readEmail(){
//
//        try{
//            Properties properties=new Properties();
//
//            properties.put("mail.store.protocol","imaps");
//
//            Session emailSession=Session.getDefaultInstance(properties);
//
//            Store store=emailSession.getStore(Config.imaps);
//
//            store.connect(
//                    con.host,
//                    con.user,
//                     con.password);
//            Folder emaolfolder=store.getFolder("INBOX");
//
//            emaolfolder.open(Folder.READ_ONLY);
//
//            Message[] messages=emaolfolder.getMessages();
//
//            System.out.println("TotalMessage:"+messages.length);
//
//            for (int i= messages.length-1;i>=messages.length - 5 && i>=0;i--){
//                Message message=messages[i];
//
//
//                System.out.println("Subject: "+ message.getSubject());
//            }
//            emaolfolder.close(false);
//            store.close();
//
//        } catch (NoSuchProviderException e) {
//            throw new RuntimeException(e);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//}
