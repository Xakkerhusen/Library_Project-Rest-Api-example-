package com.example.Library_Spring_Boot.service;


import com.example.Library_Spring_Boot.dto.Book;
import com.example.Library_Spring_Boot.dto.Student;
import com.example.Library_Spring_Boot.entity.BookEntity;
import com.example.Library_Spring_Boot.entity.StudentEntity;
import com.example.Library_Spring_Boot.exp.AppBadException;
import com.example.Library_Spring_Boot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book create(Book book) {
        BookEntity bookEntity = new BookEntity();
        if (book.getName() == null || book.getName().trim().length() < 5) {
            throw new AppBadException("Book name required");
        }
        if (book.getTitle() == null || book.getTitle().trim().length() < 5) {
            throw new AppBadException("Book title required");
        }
        if (book.getPublishYear() == null || !book.getPublishYear().matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new AppBadException("Book publish year required");
        }

        bookEntity.setName(book.getName());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setPublishYear(book.getPublishYear());
        bookEntity.setCreatedDate(LocalDateTime.now());
        bookRepository.save(bookEntity);
        return book;
    }

    public List<Book> getAll() {
        Iterable<BookEntity> bookEntities = bookRepository.findAll();
        List<Book> books = new LinkedList<>();
        for (BookEntity bookEntity : bookEntities) {
            Book book = new Book();
            book.setId(bookEntity.getId());
            book.setName(bookEntity.getName());
            book.setTitle(bookEntity.getTitle());
            book.setPublishYear(bookEntity.getPublishYear());
            books.add(book);
        }
        return books;
    }

    public BookEntity getById(Integer id) {
        Optional<BookEntity> byId = bookRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadException("Book not found");
        }
        return byId.get();
    }

    public Boolean delete(Integer id) {
        Iterable<BookEntity> all = bookRepository.findAll();
        for (BookEntity booktEntity : all) {
            if (booktEntity.getId().equals(id)) {
                bookRepository.delete(booktEntity);
                return true;
            }
        }
        throw new AppBadException("Book not found");
    }

    public Book update(Integer id, Book book) {
        BookEntity entity = new BookEntity();

        if (book.getName() == null || book.getName().trim().length() < 5) {
            throw new AppBadException("Book name required");
        }
        if (book.getTitle() == null || book.getTitle().trim().length() < 5) {
            throw new AppBadException("Book title required");
        }
        if (book.getPublishYear() == null || !book.getPublishYear().matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new AppBadException("Book publish year required");
        }

        Iterable<BookEntity> all = bookRepository.findAll();
        for (BookEntity booktEntity : all) {
            if (booktEntity.getId().equals(id)) {
                entity.setId(booktEntity.getId());
                entity.setName(book.getName());
                entity.setTitle(book.getTitle());
                entity.setPublishYear(book.getPublishYear());
                entity.setCreatedDate(LocalDateTime.now());
                bookRepository.save(entity);
                return book;
            }
        }
        throw new AppBadException("Book not found");
    }


//    public Book getBookById(Integer id) {
//        for (Book book : bookList) {
//            if (Objects.equals(book.getId(), id)) {
//                return book;
//            }
//        }
//        throw new AppBadException("Book not found");
//    }
//
//    public Book update(Integer id, Book book) {
//        if (book.getName() == null || book.getName().trim().length() < 5) {
//            throw new AppBadException("Book name required");
//        }
//        if (book.getTitle() == null || book.getTitle().trim().length() < 5) {
//            throw new AppBadException("Book title required");
//        }
//        for (Book book1 : bookList) {
//            if (book1.getId().equals(id)) {
//                book1.setName(book.getName());
//                book1.setTitle(book.getTitle());
//                book1.setPublishYear(String.valueOf(LocalDate.now()));
//                return book1;
//            }
//        }
//        throw new AppBadException("Book not found");
//    }
//
//    public Boolean deleteBookById(Integer id) {
//        return bookList.removeIf(book -> Objects.equals(book.getId(), id));
//    }
//
//    public List<Book> search(String name, String title) {
//        List<Book> books = new LinkedList<>();
//        for (Book book : bookList) {
//            if (book.getName().toLowerCase().contains(name.toLowerCase()) ||
//                    book.getTitle().toLowerCase().contains(title.toLowerCase())) {
//                books.add(book);
//            }
//        }
//        return books;
//    }


}
