package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.InvalidId;
import ru.hogwarts.school.exception.NotFoundException;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private static Long counter = 0L;
    private final Map<Long, Student> students;

    public StudentService() {
        students = new HashMap<>();
    }

    public StudentService(Map<Long, Student> students) {
        this.students = students;
    }

    public Student createStudent(Student student) {
        if (student.getId() <= counter) {
            throw new InvalidId();
        }
        students.put(++counter, student);
        return student;
    }

    public Student getStudentById(Long studentId) {
        if (!students.containsKey(studentId)) {
            throw new NotFoundException();
        }
        return students.get(studentId);
    }

    public Student updateStudent(Long studentId, Student student) {
        Student innerStudent = getStudentById(studentId);
        return innerStudent.fillByStudent(student);
    }

    public Student deleteStudent(Long studentId) {
        return students.remove(getStudentById(studentId).getId());
    }

    public List<Student> findByAge(int age) {
        return students
            .values()
            .stream()
            .filter(s -> s.getAge() == age)
            .collect(Collectors.toList());
    }

    public List<Student> getAll() {
        return new ArrayList<>(students.values());
    }
}
