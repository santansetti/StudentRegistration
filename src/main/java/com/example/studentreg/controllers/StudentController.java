package com.example.studentreg.controllers;

import com.example.studentreg.models.Course;
import com.example.studentreg.models.Student;
import com.example.studentreg.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping({"/StudentRegistration"})
@RestController
public class StudentController {


    @Autowired
    private StudentService studentService;

    @PostMapping("/student.add") //checked
    public ResponseEntity<Student> addStudent(@RequestBody Student newStudent) {
        return ResponseEntity.ok(studentService.addStudent(newStudent));
    }

    @PostMapping("/student.remove/{studentID}")  //checked
    public ResponseEntity<String> removeStudent(@PathVariable String studentID) {
        return ResponseEntity.ok(studentService.changeStudentStatus(studentID));
    }


    @PostMapping("/course.add")//checked
    public ResponseEntity<Course> addCourse(@RequestBody Course newCourse) {
        return ResponseEntity.ok(studentService.addCourse(newCourse));
    }


    @PostMapping("/course.add/{courseID}")//checked
    public ResponseEntity<Course> addCourseByType(@RequestBody Course newCourse,@PathVariable String courseID) {
        return ResponseEntity.ok(studentService.addCourseByType(newCourse,courseID));
    }


    @PostMapping("/course.remove/{courseID}") //checked
    public ResponseEntity<String> removeCourse(@PathVariable String courseID) {
        return ResponseEntity.ok(studentService.changeCourseStatus(courseID));
    }



    @GetMapping("/studentList")//checked
    public ResponseEntity<List<Student>> getStudents(@RequestParam(required = false, value = "studentID") String studentID){
        return ResponseEntity.ok(studentService.getStudents(studentID));
    }


    @GetMapping("/courseList")//checked
    public ResponseEntity<List<Course>> getCourses(@RequestParam(required = false,value = "courseID") String courseID){
        return ResponseEntity.ok(studentService.getCourses(courseID));
    }


    @PostMapping("/studentList/{studentID}/{courseID}") //Checked
    public ResponseEntity<Student>addCourseToStudent(@PathVariable String studentID,@PathVariable String courseID){
        return ResponseEntity.ok(studentService.addCourseToStudent(studentID,courseID));
    }



    @GetMapping("/courseList/{courseType}") //checked
    public ResponseEntity<List<Course>>getAllCoursesByCourseType(@PathVariable String courseType){
        return ResponseEntity.ok(studentService.getCoursesByCourseType(courseType));
    }


    @GetMapping("/studentList/{courseType}") //checked
    public ResponseEntity<List<Student>>getStudentsByCourseType(@PathVariable String courseType){
        return ResponseEntity.ok(studentService.getStudentsByCourseType(courseType));
    }



    @GetMapping("/studentList/courseList/{courseType}")
    public ResponseEntity<List<Course>>getCoursesFromStudentByCourseType(@PathVariable String courseType){
        return ResponseEntity.ok(studentService.getCoursesFromStudentsByCourseType(courseType));
    }





}
