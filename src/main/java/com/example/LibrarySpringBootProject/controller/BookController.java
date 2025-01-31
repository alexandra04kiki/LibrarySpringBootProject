package com.example.LibrarySpringBootProject.controller;

import com.example.LibrarySpringBootProject.dto.BookDto;
import com.example.LibrarySpringBootProject.dto.UserDto;
import com.example.LibrarySpringBootProject.model.Book;
import com.example.LibrarySpringBootProject.service.BookService;
import com.example.LibrarySpringBootProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/actionBook")
    public String deleteOrUpdateBook(@RequestParam(required = false) List<String> selectedBooks,
                             @RequestParam(required = false) String sellBook,
                             @RequestParam(required = false) Integer sellQuantity) {

        if(selectedBooks != null){
            for(String selectedBook : selectedBooks){
                bookService.deleteBookByTitle(selectedBook);
            }
        }

        if(sellBook != null){
            if(sellQuantity != null){
                bookService.updateBook(sellBook, sellQuantity);
            }
        }

        return "redirect:/books";
    }
}
