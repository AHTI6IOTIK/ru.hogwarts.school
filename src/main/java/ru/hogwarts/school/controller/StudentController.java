package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("{studentId}")
    public Student getStudent(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student.getId(), student);
    }

    @DeleteMapping("/delete/{studentId}")
    public Student delete(@PathVariable Long studentId) {
        return studentService.deleteStudent(studentId);
    }

    @GetMapping("/find")
    public List<Student> find(@RequestParam int age) {
        return studentService.findByAge(age);
    }
}
