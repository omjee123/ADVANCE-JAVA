package com.example.demo.Repository;

import com.example.demo.Entity.OcrEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<OcrEntity, Long> {
}
