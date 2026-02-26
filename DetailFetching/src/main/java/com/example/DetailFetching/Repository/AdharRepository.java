package com.example.DetailFetching.Repository;

import com.example.DetailFetching.Entity.AdharEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdharRepository extends JpaRepository<AdharEntity, Long> {
}
