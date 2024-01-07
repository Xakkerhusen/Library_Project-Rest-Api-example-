package com.example.Library_Spring_Boot;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.Transient;

@Setter
@Getter
@ToString
public class Book {
    private Integer id;
    private String title;
    private String name;
    private String publishYear;
}
