package com.example.Library_Spring_Boot.controller;

import com.example.Library_Spring_Boot.dto.Book;
import com.example.Library_Spring_Boot.entity.BookEntity;
import com.example.Library_Spring_Boot.exp.AppBadException;
import com.example.Library_Spring_Boot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController {
    private List<Book> bookList = new LinkedList<>();

    @Autowired
    private BookService bookService;

    //created book POST
    @PostMapping("")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        try {
            bookService.create(book);
        } catch (AppBadException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(true);
    }

//        all lisi book GET
    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBook() {
        return ResponseEntity.ok(bookService.getAll());
    }


    //    get book by id GET
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") Integer id) {
            BookEntity book = bookService.getById(id);
            return ResponseEntity.ok(book);
    }

    //delete book by id DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") Integer id) {
        bookService.delete(id);
        return ResponseEntity.ok(true);
    }


    //    update book by id UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookById(@RequestBody Book book, @PathVariable("id") Integer id) {
            return ResponseEntity.ok(bookService.update(id, book));
    }
//
//    @GetMapping("/search")
//    public ResponseEntity<List<Book>>search(@RequestParam("name")String name,
//                                            @RequestParam("title")String title){
//        return ResponseEntity.ok(bookService.search(name,title));
//    }


}
