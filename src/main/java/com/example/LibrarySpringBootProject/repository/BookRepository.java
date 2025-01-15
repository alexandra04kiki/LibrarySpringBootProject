package com.example.LibrarySpringBootProject.repository;

import com.example.LibrarySpringBootProject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepository extends JpaRepository<Book, Integer> {

    void deleteByTitle(String title);
}
