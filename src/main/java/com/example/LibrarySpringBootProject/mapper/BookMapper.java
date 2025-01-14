package com.example.LibrarySpringBootProject.mapper;

import com.example.LibrarySpringBootProject.dto.BookDto;
import com.example.LibrarySpringBootProject.dto.RoleDto;
import com.example.LibrarySpringBootProject.model.Book;
import com.example.LibrarySpringBootProject.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapper {

    public BookDto bookEntityToDto(Book book){
        return BookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .stock(book.getStock())
                .build();
    }

    public List<BookDto> bookListEntityToDto(List<Book> books){
        return books.stream()
                .map(book -> bookEntityToDto(book))
                .toList();
    }

    public Book bookDtoToEntity(BookDto bookDto){
        return Book.builder()
                .title(bookDto.title())
                .author(bookDto.author())
                .price(bookDto.price())
                .stock(bookDto.stock())
                .build();
    }

    public List<Book> bookListDtoToEntity(List<BookDto> bookDtos){
        return bookDtos.stream()
                .map(bookDto -> bookDtoToEntity(bookDto))
                .toList();
    }
}
