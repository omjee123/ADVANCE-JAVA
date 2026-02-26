package com.example.LibraryMannagementSystem.RepositoryOracle;

import com.example.LibraryMannagementSystem.EntityOracle.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
