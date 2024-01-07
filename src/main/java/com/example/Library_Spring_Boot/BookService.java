package com.example.Library_Spring_Boot;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    private List<Book> bookList = new LinkedList<>();

    public BookService() {
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

    public Boolean create(Book book) {
        if (book.getName() == null || book.getName().trim().length() < 5) {
            throw new AppBadException("Book name required");
        }
        if (book.getTitle() == null || book.getTitle().trim().length() < 5) {
            throw new AppBadException("Book title required");
        }
        book.setId(6);
        for (Book book1 : bookList) {
            if (Objects.equals(book1.getId(), book.getId())) {
                throw new AppBadException("Book id required");
            }
        }
        book.setPublishYear(String.valueOf(LocalDate.now()));
        bookList.add(book);
        return true;
    }

    public List<Book> all() {
        return bookList;
    }

    public Book getBookById(Integer id) {
        for (Book book : bookList) {
            if (Objects.equals(book.getId(), id)) {
                return book;
            }
        }
        throw new AppBadException("Book not found");
    }

    public Book update(Integer id, Book book) {
        if (book.getName() == null || book.getName().trim().length() < 5) {
            throw new AppBadException("Book name required");
        }
        if (book.getTitle() == null || book.getTitle().trim().length() < 5) {
            throw new AppBadException("Book title required");
        }
        for (Book book1 : bookList) {
            if (book1.getId().equals(id)) {
                book1.setName(book.getName());
                book1.setTitle(book.getTitle());
                book1.setPublishYear(String.valueOf(LocalDate.now()));
                return book1;
            }
        }
        throw new AppBadException("Book not found");
    }

    public Boolean deleteBookById(Integer id) {
        return bookList.removeIf(book -> Objects.equals(book.getId(), id));
    }

    public List<Book> search(String name, String title) {
        List<Book> books = new LinkedList<>();
        for (Book book : bookList) {
            if (book.getName().toLowerCase().contains(name.toLowerCase()) ||
                    book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                books.add(book);
            }
        }
        return books;
    }

}
