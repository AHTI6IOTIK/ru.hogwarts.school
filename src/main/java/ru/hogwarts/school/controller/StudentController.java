package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
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
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity delete(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public List<Student> find(@RequestParam int age) {
        return studentService.findByAge(age);
    }

    @GetMapping("/findByAgeBetween")
    public List<Student> findByAgeBetween(@RequestParam int fromAge, @RequestParam int toAge) {
        return studentService.findByAgeBetween(fromAge, toAge);
    }

    @GetMapping("/{studentId}/faculties")
    public Faculty getStudentsFaculties(@PathVariable Long studentId) {
        return studentService.getStudentsFaculty(studentId);
    }

    @GetMapping("/avgAge")
    public int getAvgAgeStudents() {
        return studentService.getAvgAgeStudents();
    }

    @GetMapping("/last/{size}")
    public List<Student> getAvgAgeStudents(@PathVariable int size) {
        return studentService.getLastStudentBySize(size);
    }

    @GetMapping("/age/avg")
    public ResponseEntity<Double> getStudentsAvgAge() {
        return ResponseEntity.ok(studentService.getStudentsAvgAge());
    }

    @GetMapping("/name/start")
    public List<String> getStudentsStartNameBySymbol(@RequestParam String symbol) {
        symbol = symbol.substring(0, 1);
        return studentService.findStudentsStartNameBySymbol(symbol);
    }

    @GetMapping("/stream")
    public void testStreams() {
        studentService.printStudentsFromStream();
    }

    @GetMapping("/stream/synchronize")
    public void testStreamsSynchronize() {
        studentService.printStudentsFromStreamSynchronize();
    }
}
