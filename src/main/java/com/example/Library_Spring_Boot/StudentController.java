package com.example.Library_Spring_Boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private StudentService studentService;

    //create student POST
    @PostMapping("")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try {
            studentService.create(student);
        } catch (AppBadException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(true);
    }

    //    all student GET
    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudent() {
        return ResponseEntity.ok(studentService.all());
    }


    //get student by id GET
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") String id) {
        try {
            Student dto = studentService.get(id);
            return ResponseEntity.ok(dto);
        } catch (AppBadException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //    delete student by id DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteStudentById(@PathVariable("id") String id) {
        return ResponseEntity.ok(studentService.delete(id));
    }

    //    update student by id PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudentById(@RequestBody Student student, @PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(studentService.update(id,student));
        }catch (AppBadException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>>search(@RequestParam("name") String name,
                                               @RequestParam("surname") String surname){
        return ResponseEntity.ok(studentService.search(name,surname));
    }
}
