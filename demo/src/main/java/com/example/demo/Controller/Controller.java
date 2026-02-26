package com.example.demo.Controller;

import com.example.demo.Service.OcrService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/data")
public class Controller {
    private final OcrService service;

    public Controller(OcrService service) {
        this.service = service;
}
    @PostMapping("/addfile")
    public ResponseEntity<String> readData(@RequestParam("file") MultipartFile file) {
        try {
            service.readOcr(file);
            return ResponseEntity.ok("File processed & data saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while processing file: " + e.getMessage());
        }
    }

}
