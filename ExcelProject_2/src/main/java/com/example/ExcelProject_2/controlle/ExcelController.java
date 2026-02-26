package com.example.ExcelProject_2.controlle;

import com.example.ExcelProject_2.dto.UserDto;
import com.example.ExcelProject_2.service.ExcelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    private final ExcelService service;

    public ExcelController(ExcelService service) {
        this.service = service;
    }

    @PostMapping("/detail")
    public void readExcel(@RequestParam MultipartFile file) throws IOException {
        service.readExcel(file);
    }

    @PostMapping("/create")
    public void createFile() throws IOException {
        service.createExcel();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable int id) {
        UserDto userInfo = service.getUserInfo(id);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }


    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody UserDto userDto) {
        return "User registered successfully!";
    }
}
