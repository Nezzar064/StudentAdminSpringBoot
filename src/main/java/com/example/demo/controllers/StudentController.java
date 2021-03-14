package com.example.demo.controllers;

import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/students/")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("signup")
    public String showSignUpForm(Student student) {
        return "add-student";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "list-of-students";
    }

    @PostMapping("add")
    public String addStudent(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }
        model.addAttribute("successMessage", "Student has been registered successfully!");
        model.addAttribute("student", student);
        studentService.save(student);
        return "redirect:/students/list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = studentService.findStudentById(id);
        model.addAttribute("student", student);
        return "update-student";
    }

    @PostMapping("update/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }

        studentService.save(student);
        model.addAttribute("successMessage", "Student has been updated successfully!");
        model.addAttribute("students", studentService.findAll());
        return "redirect:/students/list";
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        Student student = studentService.findStudentById(id);
        studentService.delete(student);
        model.addAttribute("students", studentService.findAll());
        return "redirect:/students/list";
    }

}
