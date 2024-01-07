package com.example.Library_Spring_Boot;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/book")
public class BookController {
    private List<Book> bookList = new LinkedList<>();

    public BookController() {
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("aaaa");
        book1.setTitle("bbbb");
        book1.setPublishYear(String.valueOf(LocalDate.now().minusYears(3)));
        bookList.add(book1);
        Book book2 = new Book();
        book2.setId(2);
        book2.setName("ccc");
        book2.setTitle("ddd");
        book2.setPublishYear(String.valueOf(LocalDate.now().minusYears(6)));
        bookList.add(book2);
        Book book3 = new Book();
        book3.setId(3);
        book3.setName("eee");
        book3.setTitle("fff");
        book3.setPublishYear(String.valueOf(LocalDate.now().minusYears(1)));
        bookList.add(book3);
        Book book4 = new Book();
        book4.setId(4);
        book4.setName("ggg");
        book4.setTitle("hhh");
        book4.setPublishYear(String.valueOf(LocalDate.now()));
        bookList.add(book4);
    }

    @PostMapping("/create")
    public boolean createBook(@RequestBody Book book) {
        book.setId(6);
        book.setPublishYear(String.valueOf(LocalDate.now()));
        return bookList.add(book);
    }

    @GetMapping("/all")
    public List<Book> getAllBook() {
        return bookList;
    }

    @GetMapping("/{id}")
    public Book getAllBook(@PathVariable("id") Integer id) {
        return bookList.stream()
                .filter(book -> book.getId().equals(id))
                .findAny()
                .orElse(null);
        //
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteBookById(@PathVariable("id") Integer id) {
        return bookList.removeIf(book -> Objects.equals(book.getId(), id));
    }

    @PutMapping("/update/{id}")
    public boolean updateBookById(@RequestBody Book book, @PathVariable("id") Integer id) {
        for (Book book1 : bookList) {
            if (book1.getId().equals(id)) {
                book1.setName(book.getName());
                book1.setTitle(book.getTitle());
                book1.setPublishYear(book.getPublishYear());
                return true;
            }
        }
        return false;
    }


}
