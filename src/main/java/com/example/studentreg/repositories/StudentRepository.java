package com.example.studentreg.repositories;

import com.example.studentreg.models.Course;
import com.example.studentreg.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}
