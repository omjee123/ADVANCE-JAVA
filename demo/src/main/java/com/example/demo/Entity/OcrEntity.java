package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "OCR_DATA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OcrEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ocr_seq_gen")
    @SequenceGenerator(
            name = "ocr_seq_gen",
            sequenceName = "OCR_SEQ",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "AADHAR_NO", nullable = false, unique = true)
    private Long aadharNo;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DOB")
    private String dob;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "ADDRESS", length = 1000)
    private String address;

    @Column(name = "FILE_NAME")
    private String fileName;

    public OcrEntity(Long aadharNo, String name, String dob, String gender, String address) {
        this.aadharNo=aadharNo;
        this.name=name;
        this.dob=dob;
        this.address=address;
        this.gender=gender;
        this.fileName=fileName;
    }
}