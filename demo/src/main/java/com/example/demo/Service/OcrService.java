package com.example.demo.Service;

import com.example.demo.Entity.OcrEntity;
import com.example.demo.Repository.Repo;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OcrService {
@Autowired
    private final Repo repository;

    public OcrService(Repo repository) {
        this.repository = repository;
    }


    public void readOcr(MultipartFile multipartFile) throws IOException, TesseractException {
        Tesseract tesseract = new Tesseract();
//        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
        tesseract.setLanguage("eng+mar"); // Hindi + English for mixed text

        Path path = Paths.get("ocr.jpg");
        Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        String s = tesseract.doOCR(path.toFile());
        System.out.println("Extracted Text:\n" + s);

        Long aadharNo = extractAadharNumber(s);
        String name = extractName(s);
        String dob = extractDob(s);
        String gender = extractGender(s);
        String address = extractAddress(s);

        if (aadharNo != null) {
           OcrEntity card = new OcrEntity(aadharNo, name, dob, gender, address);
            repository.save(card);
            System.out.println("Saved: " + card);
        } else {
            System.out.println("No valid Aadhaar number found!");
        }
    }

    private Long extractAadharNumber(String text) {
        // Match: 4+ spaces 4+ spaces 4 OR 12 continuous digits
        Pattern pattern = Pattern.compile("(\\d{4}\\s\\d{4}\\s\\d{4})|(\\d{12})");
        Matcher matcher = pattern.matcher(text.replaceAll("[^0-9\\s]", "")); // keep digits + spaces only
     //   boolean b = matcher.find();
        if (matcher.find()) {
            String number = matcher.group().replaceAll("\\s", "");
            if (number.length() == 12) {
                return Long.parseLong(number);
            } else {
                System.out.println("Aadhaar number found but length != 12: " + number);
            }
        }
        return null;
    }

    private String extractName(String text) {
        // Look for English name line
        Pattern pattern = Pattern.compile("(?m)^[A-Za-z ]{3,}$");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group().trim();
        }
        return null;
    }

    private String extractDob(String text) {
        Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group() : null;
    }

    private String extractGender(String text) {
        if (text.toLowerCase().contains("male") || text.contains("पुरुष")) return "Male";
        if (text.toLowerCase().contains("female") || text.contains("महिला")) return "Female";
        return null;
    }

    private String extractAddress(String text) {
        // Everything before Aadhaar number
        String[] parts = text.split("\\d{4}\\s\\d{4}\\s\\d{4}|\\d{12}");
        return parts.length > 0 ? parts[0].trim() : null;
    }
}


