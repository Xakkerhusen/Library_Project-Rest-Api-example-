package com.example.Library_Spring_Boot.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Student {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String phone;
    private LocalDateTime createdDate;

}
