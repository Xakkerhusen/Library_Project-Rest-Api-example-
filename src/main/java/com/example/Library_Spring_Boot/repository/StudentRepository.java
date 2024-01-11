package com.example.Library_Spring_Boot.repository;


import com.example.Library_Spring_Boot.dto.Student;
import com.example.Library_Spring_Boot.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends  CrudRepository<StudentEntity,Integer> {
    @Query(value = "select * from student e where upper( e.name) like upper(:name%) or upper(e.surname) like upper(:surname%)",nativeQuery = true)
//    @Query(value = "select e from Student e where lower(e.name) like lower(concat( '%',:name,'%')) or lower(e.surname) like lower(concat( '%',:surname,'%'))")
    List<Student>searchByNameOrSurname(@Param("name") String name , @Param("surname") String surname);

}
