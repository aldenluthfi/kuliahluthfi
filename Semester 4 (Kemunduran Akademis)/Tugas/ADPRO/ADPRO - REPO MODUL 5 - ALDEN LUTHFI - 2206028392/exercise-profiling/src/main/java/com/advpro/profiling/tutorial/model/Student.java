package com.advpro.profiling.tutorial.model;

import jakarta.persistence.*;

/**
 * @author muhammad.khadafi
 */


@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_code", nullable = false)
    private String studentCode;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "faculty", nullable = false)
    private String faculty;

    @Column(name = "gpa", nullable = false)
    private Double gpa;

    // Constructors

    public Student() {
    }

    public Student(String studentCode, String name, String faculty, Double gpa) {
        this.studentCode = studentCode;
        this.name = name;
        this.faculty = faculty;
        this.gpa = gpa;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentCode='" + studentCode + '\'' +
                ", name='" + name + '\'' +
                ", faculty='" + faculty + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
