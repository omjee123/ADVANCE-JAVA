package com.example.LibraryMannagementSystem.Service;

import com.example.LibraryMannagementSystem.EntityMysql.Book;
import com.example.LibraryMannagementSystem.RepoMysql.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional("getMysqlTransaction")
public class BookServiceImpl  {

    @Autowired
    private BookRepository bookRepository;


    public Book saveBook(Book book) {

        return bookRepository.save(book);
    }

        public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
}

