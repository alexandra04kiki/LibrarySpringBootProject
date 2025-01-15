package com.example.LibrarySpringBootProject.service;

import com.example.LibrarySpringBootProject.dto.BookDto;
import com.example.LibrarySpringBootProject.dto.RoleDto;
import com.example.LibrarySpringBootProject.mapper.BookMapper;
import com.example.LibrarySpringBootProject.mapper.RoleMapper;
import com.example.LibrarySpringBootProject.model.Book;
import com.example.LibrarySpringBootProject.model.Role;
import com.example.LibrarySpringBootProject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public BookDto getBookById(Integer id){
        return bookMapper.bookEntityToDto(bookRepository.findById(id).orElse(null));
    }

    public List<BookDto> getAllBooks(){
        return bookMapper.bookListEntityToDto(bookRepository.findAll());
    }

    public BookDto createBook(Book book){
        return bookMapper.bookEntityToDto(bookRepository.save(book));
    }

    public BookDto updateBook(Book book){
        return bookMapper.bookEntityToDto(bookRepository.save(book));
    }

    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

    public void deleteBooksByTitle(List<String> titles){
        for(String title : titles){
            Book book = bookRepository.findByTitle(title);
            if(book != null){
                deleteBook(book);
            }
        }
    }
}
