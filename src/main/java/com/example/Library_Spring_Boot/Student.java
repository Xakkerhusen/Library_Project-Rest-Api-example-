package com.example.Library_Spring_Boot;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class Student {
    private String id;
    private String name;
    private String surname;
    private LocalDate createdDate;

}
