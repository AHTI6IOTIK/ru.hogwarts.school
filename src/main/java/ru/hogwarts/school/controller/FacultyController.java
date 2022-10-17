package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public List<Faculty> getAll() {
        return facultyService.getAll();
    }

    @PostMapping("/create")
    public Faculty createFaculty(@RequestBody Faculty student) {
        return facultyService.createFaculty(student);
    }

    @GetMapping("{studentId}")
    public Faculty getFaculty(@PathVariable Long studentId) {
        return facultyService.getFacultyById(studentId);
    }

    @PutMapping("/update")
    public Faculty updateFaculty(@RequestBody Faculty student) {
        return facultyService.updateFaculty(student);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity delete(@PathVariable Long studentId) {
        facultyService.deleteFaculty(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{facultyId}/students/count")
    public int getCountStudents(@PathVariable Long facultyId) {
        return facultyService.getFacultyStudentsCount(facultyId);
    }

    @GetMapping("/find")
    public List<Faculty> find(@RequestParam String color) {
        return facultyService.findByColor(color);
    }

    @GetMapping("/findByColorContainingIgnoreCase")
    public List<Faculty> findByColorContainingIgnoreCase(@RequestParam String color) {
        return facultyService.findByColorContainingIgnoreCase(color);
    }

    @GetMapping("/{facultyId}/students")
    public Set<Student> getStudentsFaculties(@PathVariable Long facultyId) {
        return facultyService.getFacultyStudents(facultyId);
    }
}
