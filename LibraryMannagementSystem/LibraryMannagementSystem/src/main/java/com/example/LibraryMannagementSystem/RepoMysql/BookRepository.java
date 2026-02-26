package com.example.LibraryMannagementSystem.RepoMysql;

import com.example.LibraryMannagementSystem.EntityMysql.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
