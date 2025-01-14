package com.example.LibrarySpringBootProject.controller;

import com.example.LibrarySpringBootProject.dto.BookDto;
import com.example.LibrarySpringBootProject.dto.UserDto;
import com.example.LibrarySpringBootProject.model.Book;
import com.example.LibrarySpringBootProject.service.BookService;
import com.example.LibrarySpringBootProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public String getBooks(Model model){
        List<BookDto> bookDtos = bookService.getAllBooks();
        model.addAttribute("title", "Books");
        model.addAttribute("books", bookDtos);
        model.addAttribute("book", new Book());
        return "books";
    }

    @PostMapping("/createBook")
    public String createBook(@ModelAttribute("book") Book book){
        bookService.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public BookDto getBookById(@PathVariable Integer id){
        return bookService.getBookById(id);
    }
}
