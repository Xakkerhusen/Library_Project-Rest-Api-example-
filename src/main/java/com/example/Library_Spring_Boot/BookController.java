package com.example.Library_Spring_Boot;

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

    //    all lisi book GET
    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBook() {
        return ResponseEntity.ok(bookService.all());
    }


    //    get book by id GET
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") Integer id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (AppBadException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //delete book by id DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBookById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(bookService.deleteBookById(id));
    }

    //    update book by id UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookById(@RequestBody Book book, @PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(bookService.update(id, book));
        } catch (AppBadException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>>search(@RequestParam("name")String name,
                                            @RequestParam("title")String title){
        return ResponseEntity.ok(bookService.search(name,title));
    }


}
