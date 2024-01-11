package com.example.Library_Spring_Boot.repository;


import com.example.Library_Spring_Boot.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends JpaRepository<BookEntity,Integer> {


}
