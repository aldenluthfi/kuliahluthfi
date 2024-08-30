package com.advpro.profiling.tutorial.controller;

import com.advpro.profiling.tutorial.model.Student;
import com.advpro.profiling.tutorial.model.StudentCourse;
import com.advpro.profiling.tutorial.service.DataSeedService;
import com.advpro.profiling.tutorial.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author muhammad.khadafi
 */

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all-student")
    public ResponseEntity<String> seedStudents() {
        List<StudentCourse> studentCourses = studentService.getAllStudentsWithCourses();
        return ResponseEntity.ok(studentCourses.toString());
    }
    @GetMapping("/highest-gpa")
    public ResponseEntity<String> highestGpa() {
        Optional<Student> studentWithHighestGpa = studentService.findStudentWithHighestGpa();
        return ResponseEntity.ok(studentWithHighestGpa.get().toString());
    }
    @GetMapping("/all-student-name")
    public ResponseEntity<String> allStudentName() {
        String joinedStudentNames = studentService.joinStudentNames();
        return ResponseEntity.ok(joinedStudentNames);
    }
}

