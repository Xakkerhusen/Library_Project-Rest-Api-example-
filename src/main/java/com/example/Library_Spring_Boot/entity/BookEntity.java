package com.example.Library_Spring_Boot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "title")
    private String title;
    @Column(name = "publishYear")
    private String publishYear;
    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
