package com.example.studentreg.services;

import com.example.studentreg.models.Course;
import com.example.studentreg.models.Student;
import com.example.studentreg.repositories.CourseRepository;
import com.example.studentreg.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;


    //========================================================== STUDENT METHODS BELOW ==========================================================================
    //========================================================== STUDENT METHODS BELOW ==========================================================================


    //========================================================== Add Student ==========================================================================
    public Student addStudent(Student newStudent) {
        studentRepository.save(newStudent);
        return newStudent;
    }
    //========================================================== Add Student ==========================================================================


    //========================================================== Get All Students ==========================================================================
    public List<Student> getAllStudents() {
        return studentRepository.findAll().stream().filter(Student::isStatus).collect(Collectors.toList());
    }
    //========================================================== Get All Students ==========================================================================


    //========================================================== Get Student By ID==========================================================================
    public Student getStudentById(String studentID) {
        Optional<Student> existingStudent=studentRepository.findById(studentID);
        if(existingStudent.isPresent()&&existingStudent.get().isStatus()){
            return existingStudent.get();
        }
        throw new NullPointerException("Student ID: " + studentID + " is not found ");
    }
    //========================================================== Get Student By ID==========================================================================


    //========================================================== Change Student Status ==========================================================================
    public String changeStudentStatus(String studentID) {
        Student existingStudent = studentRepository.findById(studentID).get();
        if (existingStudent.getId() != null) {
            if (existingStudent.isStatus()) {
                existingStudent.setStatus(false);
                studentRepository.save(existingStudent);
                return String.format(existingStudent.getName() + " status has been changed to " + false);
            } else {
                existingStudent.setStatus(true);
                studentRepository.save(existingStudent);
                return String.format(existingStudent.getName() + " status has been changed to " + true);

            }
        }
        throw new NullPointerException("Student Does not exist");
    }
    //========================================================== Change Student Status ==========================================================================


    //========================================================== Get Students ==========================================================================
    public List<Student> getStudents(String studentID) {
        if (studentID == null) {
            return studentRepository.findAll().stream().filter(Student::isStatus).collect(Collectors.toList());
        } else {
            return Collections.singletonList(getStudentById(studentID));

        }
    }
    //========================================================== Get Students ==========================================================================


    //========================================================== Get Students By Course Type ==========================================================================
    public List<Student> getStudentsByCourseType( String courseType) {
            return getAllStudents().stream()
                    .map(student -> {
                        List<Course> listOfCoursesBasedOnType = student.getCourseList()
                                .stream().filter(course -> courseType.equalsIgnoreCase(course.getCourseType())).collect(Collectors.toList());
                        student.setCourseList(listOfCoursesBasedOnType);
                        return student;
                    })
                    .filter(student -> !student.getCourseList().isEmpty()).collect(Collectors.toList());

    }
    //========================================================== Get Students By Course Type ==========================================================================

    //========================================================== STUDENT METHODS ABOVE ==========================================================================
    //========================================================== STUDENT METHODS ABOVE ==========================================================================


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //========================================================== COURSE METHODS BELOW ==========================================================================
    //========================================================== COURSE METHODS BELOW ==========================================================================


    //========================================================== Add Course ==========================================================================
    public Course addCourse(Course newCourse) {
        if(courseRepository.findAll().stream().anyMatch(course -> newCourse.getName().equalsIgnoreCase(course.getName())&&newCourse.getDescription().equalsIgnoreCase(course.getDescription())&&!newCourse.getCourseType().equalsIgnoreCase(course.getCourseType()))){

        }
        courseRepository.save(newCourse);
        return newCourse;
    }
    //========================================================== Add Course ==========================================================================


    //========================================================== Add Course ==========================================================================


    public Course addCourseByType(Course courseData,String courseID){
        if(courseRepository.existsById(courseID)){
            Optional<Course> existingCourse=courseRepository.findById(courseID);
            if((courseData.getName().equals(existingCourse.get().getName()))&&(courseData.getDescription().equals(existingCourse.get().getDescription()))){
                if(courseData.getCourseType().equals(existingCourse.get().getCourseType())){
                    throw new ArrayStoreException("Cannot add course with the same type");
                }
                courseRepository.save(courseData);
                return courseData;
            }
            throw new ArrayStoreException("Course provided with details does not match to the course with the provided course ID");

        }
        throw new NullPointerException("Course does not exist by ID,Please check again!");
    }




    //========================================================== Add Course ==========================================================================




    //========================================================== Get All COURSES ==========================================================================
    public List<Course> getAllCourses() {
        return courseRepository.findAll().stream().filter(Course::isStatus).collect(Collectors.toList());
    }
    //========================================================== Get All COURSES ==========================================================================


    //========================================================== Get Course By ID==========================================================================
    public Course getCourseById(String courseID) {
        if (courseRepository.existsById(courseID)) {
            Optional<Course> existingCourse = courseRepository.findById(courseID);
            if (existingCourse.get().isStatus()) {
                return existingCourse.get();
            } else throw new NegativeArraySizeException("Course Status is " + false);
        }
        throw new NullPointerException("Course ID: " + courseID + " is Not Found ");
    }
    //========================================================== Get Course By ID==========================================================================


    //========================================================== Change Student Status ==========================================================================
    public String changeCourseStatus(String courseID) {
        Course existingCourse = courseRepository.findById(courseID).get();
        if (existingCourse != null) {
            if (existingCourse.isStatus()) {
                existingCourse.setStatus(false);
                courseRepository.save(existingCourse);
                return String.format(existingCourse.getName() + " status has been changed to " + false);
            } else {
                existingCourse.setStatus(true);
                courseRepository.save(existingCourse);
                return String.format(existingCourse.getName() + " status has been changed to " + true);

            }
        }
        throw new NullPointerException("Student Does not exist");
    }
    //========================================================== Change Student Status ==========================================================================


    //========================================================== Get Courses ==========================================================================
    public List<Course> getCourses(String courseID) {
        if (courseID == null) {
            return courseRepository.findAll().stream().filter(Course::isStatus).collect(Collectors.toList());
        } else {
            return Collections.singletonList(getCourseById(courseID));
        }
    }
    //========================================================== Get Courses ==========================================================================






    //========================================================== Get Courses By Course Type ==========================================================================
    public List<Course> getCoursesByCourseType(String courseType) {
        List<Course> courseTypedCourses = getAllCourses().stream().filter(course -> courseType.equalsIgnoreCase(course.getCourseType())).collect(Collectors.toList());
        if (courseTypedCourses.isEmpty()) {
            throw new NullPointerException("There are no courses with " + courseType + " in the list of courses.");
        }
        return courseTypedCourses;
    }
    //========================================================== Get Students By Course Type ==========================================================================


    //========================================================== COURSE METHODS ABOVE ==========================================================================
    //========================================================== COURSE METHODS ABOVE ==========================================================================




    //========================================================== ADD COURSE TO STUDENT METHODS BELOW ==========================================================================
    //========================================================== ADD COURSE TO STUDENT METHODS BELOW ==========================================================================



    //========================================================== ADD COURSE TO STUDENT METHOD BELOW ==========================================================================

    public Student addCourseToStudent(String studentID, String courseID){
        Student existingStudent=getStudentById(studentID);
        Course existingCourse=getCourseById(courseID);
        for(Course checkedCourse:existingStudent.getCourseList()){
            if(checkedCourse.getId().equals(existingCourse.getId())){
                throw new ArrayStoreException("Cannot Add the course as it is alreaady in List.");
            }
        }
        existingStudent.getCourseList().add(existingCourse);
        studentRepository.save(existingStudent);
        return existingStudent;
    }

    //========================================================== ADD COURSE TO STUDENT METHOD ABOVE ==========================================================================



    //========================================================== REMOVE COURSE FROM STUDENT METHOD BELOW ==========================================================================

    public Student removeCourseFromStudent(String studentID,String courseID){
        Student existingStudent=getStudentById(studentID);
        if(existingStudent==null){
            throw new NullPointerException("Student is not in the repository, please store student first before adding course");
        }
        if(getCourseById(courseID)==null){
            throw new NullPointerException("Course is not in the repository, please store the course first before adding it to the student");
        }
        List<Course>courseList=getStudentById(studentID).getCourseList();
        if(!courseList.stream().filter(course->courseID.equalsIgnoreCase(course.getId())).collect(Collectors.toList()).isEmpty()){
            courseList.remove(getCourseById(courseID));
        }
        else{
            throw new ArrayStoreException("Course not in the list of courses for the student");
        }
        return existingStudent;
    }


    //========================================================== REMOVE COURSE FROM STUDENT METHOD ABOVE ==========================================================================



    public List<Course> getCoursesFromStudentsByCourseType(String courseType) {
        List<Course> courseList = new ArrayList<>();
        getAllStudents().stream()
                .map(student -> {
                    courseList.addAll(student.getCourseList().stream()
                            .filter(course->courseType.equalsIgnoreCase(course.getCourseType())).collect(Collectors.toList()));
                    return null;
                }).collect(Collectors.toList());
        return courseList;
    }


    //========================================================== ADD COURSE TO STUDENT METHODS ABOVE ==========================================================================
    //========================================================== ADD COURSE TO STUDENT METHODS ABOVE ==========================================================================
}
