package com.example.DetailFetching.Service;


import com.example.DetailFetching.Repository.AdharRepository;
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
public class AdharService {

    @Autowired
    private final AdharRepository repo;

    public AdharService(AdharRepository repo) {
        this.repo = repo;
    }
public void readOcr(MultipartFile multipartFile ) throws IOException, TesseractException {
    Tesseract tesseract = new Tesseract();
    BufferedImage bufferedImage= ImageIO.read(multipartFile.getInputStream());
    tesseract.setDatapath("");
    tesseract.setLanguage("eng");

    Path path= Paths.get("");
    Files.copy(multipartFile.getInputStream() , path, StandardCopyOption.REPLACE_EXISTING);
    String s=tesseract.doOCR(bufferedImage);
    System.out.println("EXTRACTED TEXT"+s);

    Long aadharNo = extractAadharNumber(s);
    String name=extractename(s);
    String  Gender=extracteGender(s);
    String Dob=extractDob(s);
}
    private Long extractAadharNumber(String text) {

        Pattern pattern=Pattern.compile( "(\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4})(\\d{12})");
        Matcher matcher=pattern.matcher(text.replaceAll("[\\^0-9\\s]",""));
        if(matcher.find()){
            String number=matcher.group().replaceAll("\\s","");
            if(number.length() == 12){
              return  Long.parseLong(number);
            }
            else{
                System.out.println("Aadhar nummber is Invalid "+number);
            }

        }
        return  null;
    }

    private String extractename(String text) {
Pattern pattern=Pattern.compile("(?m)^[A-Za-z]{3,}$");
Matcher matcher= pattern.matcher(text);
if(matcher.find()){
    return matcher.group().trim();

}
return null;
     }


    private String extracteGender(String text) {
       if(text.toLowerCase().contains("male")||text.contains("पुरुष")) return "MALE";
        if (text.toLowerCase().contains("female") || text.contains("महिला")) return "Female";
        return null;
    }


    private String extractDob(String text) {
        Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group() : null;
    }


}
