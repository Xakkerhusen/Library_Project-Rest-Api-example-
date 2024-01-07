package com.example.Library_Spring_Boot;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    private List<Student> studentList = new LinkedList<>();

    public StudentService() {
        Student student1 = new Student();
        student1.setId(UUID.randomUUID().toString());
        student1.setName("husen");
        student1.setSurname("tehayev");
        student1.setCreatedDate((LocalDate.now().minusYears(3)));
        studentList.add(student1);
        Student student2 = new Student();
        student2.setId(UUID.randomUUID().toString());
        student2.setName("ali");
        student2.setSurname("aliyev");
        student2.setCreatedDate((LocalDate.now().minusYears(6)));
        studentList.add(student2);
        Student student3 = new Student();
        student3.setId(UUID.randomUUID().toString());
        student3.setName("vali");
        student3.setSurname("valiyev");
        student3.setCreatedDate((LocalDate.now().minusYears(1)));
        studentList.add(student3);
        Student student4 = new Student();
        student4.setId(UUID.randomUUID().toString());
        student4.setName("ketmon");
        student4.setSurname("keymonov");
        student4.setCreatedDate((LocalDate.now().minusYears(5)));
        studentList.add(student4);
    }

    public Boolean create(Student dto) {
        if (dto.getName() == null || dto.getName().trim().length() < 3) {
            throw new AppBadException("Student name required");
        }
        if (dto.getSurname() == null || dto.getSurname().trim().length() < 3) {
            throw new AppBadException("Student surname required");
        }
        dto.setCreatedDate(LocalDate.now());
        dto.setId(UUID.randomUUID().toString());

        studentList.add(dto);
        return true;
    }

    public List<Student> all() {
        return studentList;
    }

    public Student get(String id) {
        for (Student dto : studentList) {
            if (dto.getId().equals(id)) {
                return dto;
            }
        }
        throw new AppBadException("Student not found");
    }

    public Student update(String id, Student dto) {
        if (dto.getName() == null || dto.getName().trim().length() < 3) {
            throw new AppBadException("Student name required");
        }
        if (dto.getSurname() == null || dto.getSurname().trim().length() < 3) {
            throw new AppBadException("Student surname required");
        }
        for (Student studentDTO : studentList) {
            if (studentDTO.getId().equals(id)) {
                studentDTO.setName(dto.getName());
                studentDTO.setSurname(dto.getSurname());
                return studentDTO;
            }
        }
        throw new AppBadException("Student not found");
    }


    public Boolean delete(String id) {
        return studentList.removeIf(dto -> dto.getId().equals(id));
    }

    public List<Student> search(String name, String surname) {
        List<Student> students = new LinkedList<>();
        for (Student student : studentList) {
            if (student.getSurname().toLowerCase().contains(name.toLowerCase()) ||
                    student.getSurname().toLowerCase().contains(surname.toLowerCase())) {
                students.add(student);
            }
        }
        return students;
    }



}
