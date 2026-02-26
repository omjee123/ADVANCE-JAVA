package com.example.ExcelProject_2.service;

import com.example.ExcelProject_2.dto.UserDto;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    //  ...........Read Pdf.............

    public void readExcel(MultipartFile file) throws IOException {

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {

                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.println(cell.getStringCellValue() + "|");
                            break;
                        case NUMERIC:
                            System.out.println(cell.getNumericCellValue()+"|");
                            break;
                        case BOOLEAN:
                            System.out.println(cell.getBooleanCellValue()+"|");
                            break;
                        case BLANK:
                            System.out.println("UKNOWN \t");

                    }
                }
                System.out.println();

            }
        }
catch (IOException e){
            throw new RuntimeException("failed to read excel file ");
}
    }


    // *****Create Excel********

    public void createExcel() throws IOException {
        try (SXSSFWorkbook sheets = new SXSSFWorkbook()) {
            SXSSFSheet sheet = sheets.createSheet("Employees");

            // Create Row
            Row headerRow=sheet.createRow(0);


            //Create cells*************

            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Salary");

            // ********Sample Data Row **************

            Row row=sheet.createRow(1);
            row.createCell(0).setCellValue(1);
            row.createCell(1).setCellValue("Shubham Thokre ");
            row.createCell(2).setCellValue(800000);

            // ...............write to file ................

            try(FileOutputStream fileOut=new FileOutputStream("company.xlsx")){
                sheets.write(fileOut);
            }
            catch (IOException e){
                System.out.println("data not found");
            }
        }
    }

    //---------------User Dto --------------

public UserDto getUserInfo(int id){
        List<UserDto> list=new ArrayList<>();
        list.add(new UserDto("shubham",11,20));
        list.add(new UserDto("Rahul",2,19));
        list.add(new UserDto("Thokre",3,35));

        UserDto user=list.stream().filter(i->i.getId()==id).findFirst().get();
    return new UserDto(user.getUsername(),user.getId(),user.getAge());
}
}