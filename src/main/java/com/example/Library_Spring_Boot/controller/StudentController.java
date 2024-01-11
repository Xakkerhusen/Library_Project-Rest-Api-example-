package com.example.Library_Spring_Boot.controller;

import com.example.Library_Spring_Boot.dto.Student;
import com.example.Library_Spring_Boot.entity.StudentEntity;
import com.example.Library_Spring_Boot.exp.AppBadException;
import com.example.Library_Spring_Boot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //create student POST
    @PostMapping("")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        studentService.create(student);
        return ResponseEntity.ok(true);
    }

    //    all student GET
    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAll());
    }


//    get student by id GET
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Integer id) {
            StudentEntity dto = studentService.getById(id);
            return ResponseEntity.ok(dto);
    }

//        delete student by id DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("id") Integer id) {
        studentService.delete(id);
        return ResponseEntity.ok(true);
    }

//        update student by id PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudentById(@RequestBody Student student, @PathVariable("id") Integer id) {
            return ResponseEntity.ok(studentService.update(id,student));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> search(@RequestParam("name") String name,
                                                @RequestParam("surname") String surname){
        List<Student> search = studentService.search(name, surname);
        return ResponseEntity.ok(search);

    }
}
