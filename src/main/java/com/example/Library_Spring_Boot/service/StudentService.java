package com.example.Library_Spring_Boot.service;


import com.example.Library_Spring_Boot.dto.Student;
import com.example.Library_Spring_Boot.entity.StudentEntity;
import com.example.Library_Spring_Boot.exp.AppBadException;
import com.example.Library_Spring_Boot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student create(Student dto) {
        StudentEntity entity = new StudentEntity();

        if (dto.getName() == null || dto.getName().trim().length() < 3) {
            throw new AppBadException("Student name required");
        }
        if (dto.getSurname() == null || dto.getSurname().trim().length() < 3) {
            throw new AppBadException("Student surname required");
        }
        if (dto.getAge() == null || dto.getAge() < 0) {
            throw new AppBadException("Student age required");
        }
        if (dto.getPhone() == null || dto.getPhone().trim().length() < 3) {
            throw new AppBadException("Student phone required");
        }
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setPhone(dto.getPhone());
        entity.setCreatedDate(LocalDateTime.now());
        studentRepository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(LocalDateTime.now().minusDays(5L));
        return dto;
    }

    public List<Student> getAll() {
        Iterable<StudentEntity> entityList = studentRepository.findAll();
        List<Student> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            Student dto = new Student();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setPhone(entity.getPhone());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public StudentEntity getById(Integer id) {
        Optional<StudentEntity> byId = studentRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadException("Student not found");
        }
        return byId.get();
    }


    public Student update(Integer id, Student dto) {
        if (dto.getName() == null || dto.getName().trim().length() < 3) {
            throw new AppBadException("Student name required");
        }
        if (dto.getSurname() == null || dto.getSurname().trim().length() < 3) {
            throw new AppBadException("Student surname required");
        }
        if (dto.getAge() == null || dto.getAge() < 0) {
            throw new AppBadException("Student age required");
        }
        if (dto.getPhone() == null || dto.getPhone().trim().length() < 3) {
            throw new AppBadException("Student phone required");
        }

        Iterable<StudentEntity> all = studentRepository.findAll();
        for (StudentEntity studentEntity : all) {
            if (studentEntity.getId().equals(id)) {
                studentEntity.setName(dto.getName());
                studentEntity.setSurname(dto.getSurname());
                studentEntity.setPhone(dto.getPhone());
                studentEntity.setAge(dto.getAge());
                dto.setId(studentEntity.getId());
                dto.setCreatedDate(studentEntity.getCreatedDate());
                studentRepository.save(studentEntity);
                return dto;
            }
        }
        throw new AppBadException("Student not found");
    }

    public List<Student> search(String name, String surname) {
        return studentRepository.searchByNameOrSurname(name, surname);
    }
    public Boolean delete(Integer id) {
        Iterable<StudentEntity> all = studentRepository.findAll();
        for (StudentEntity studentEntity : all) {
            if (studentEntity.getId().equals(id)) {
                studentRepository.delete(studentEntity);
                return true;
            }
        }
        throw new AppBadException("Student not found");
    }

}
