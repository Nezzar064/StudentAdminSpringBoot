package com.example.demo.service;

import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student findStudentById(long id) { return studentRepository.findStudentById(id); }

    public Student findStudentByName(String name) { return studentRepository.findStudentByName(name); }

    public List<Student> getAllStudents() { return studentRepository.findAll(); }

    public void deleteStudent(Student student) { studentRepository.delete(student);}
}
