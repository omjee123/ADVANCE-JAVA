package com.example.MailSending.Configuration;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "/mail.imap")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailConfig {

    private String host;
    private String username;
    private String password;

}
