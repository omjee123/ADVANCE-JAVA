package com.example.MimeMessageSending.Controller;

import com.example.MimeMessageSending.Service.ReadUnseenMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController

public class ReadController {

    @Autowired
    private  final ReadUnseenMail readMail;

    public ReadController(ReadUnseenMail readMail) {
        this.readMail = readMail;
    }

    @GetMapping("/read")
    public void readEmails() {
      readMail.unSeen();
    }
    @GetMapping("/by-date")
    public String getMailByDate(@RequestParam String date) {

            readMail.readMailByDate(date);

        return "Mail fetched successfully";
    }
//    @GetMapping("/by-date-range")
//    public String getMailByDateRange(
//            @RequestParam
//            @DateTimeFormat(pattern = "yyyy-MM-dd")
//            LocalDate start,
//
//            @RequestParam
//            @DateTimeFormat(pattern = "yyyy-MM-dd")
//            LocalDate end) {
//
//        readMail.readMailByDateRange(start, end);
//        return "Mail fetched successfully";
    }


