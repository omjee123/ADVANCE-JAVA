package com.example.MimeMessageSending.Configuration;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;


@Configuration
@ConfigurationProperties(prefix = "mail.imap")
@Data
public class Config {


    private String host;
    private String user;
    private String password;
    private String protocol;
    private boolean sslEnable;
    private int port;
    private List<String> folders;


}

