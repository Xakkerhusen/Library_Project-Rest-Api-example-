package com.example.Library_Spring_Boot;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {

    private List<Student> studentList = new LinkedList<>();

    public StudentController() {
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

    @PostMapping("/create")
    public boolean createStudent(@RequestBody Student student) {
        student.setId(UUID.randomUUID().toString());
        student.setCreatedDate(LocalDate.now());

        return studentList.add(student);
    }

    @GetMapping("/all")
    public List<Student> getAllStudent() {
        return studentList;
    }

    @GetMapping("/getById/{id}")
    public Student getStudentById(@PathVariable("id") String id) {
        return studentList.stream()
                .filter(student -> student.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteStudentById(@PathVariable("id") String id) {
        return studentList.removeIf(book -> Objects.equals(book.getId(), id));
    }

    @PutMapping("/update/{id}")
    public boolean updateStudentById(@RequestBody Student student, @PathVariable("id") String id) {
        for (Student student1 : studentList) {
            if (student1.getId().equals(id)) {
                student1.setName(student.getName());
                student1.setSurname(student.getSurname());
                student1.setCreatedDate(LocalDate.now());
                return true;
            }
        }
        return false;

    }
}
