package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.InvalidId;
import ru.hogwarts.school.model.Faculty;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final Logger logger;

    public StudentService(StudentRepository studentRepository) {
        this.logger = LoggerFactory.getLogger(FacultyService.class);
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for student createFaculty");
        return studentRepository.save(student);
    }

    public Student getStudentById(Long studentId) {
        logger.info("Was invoked method for student getStudentById");
        return studentRepository.findById(studentId).orElseThrow(() -> {
            logger.error("There is not student with id = " + studentId);
            return new EntityNotFoundException();
        });
    }

    public Student updateStudent(Student student) {
        logger.info("Was invoked method for student updateStudent");
        Student oldStudent = studentRepository.findById(student.getId()).orElseThrow(() -> {
            logger.error("There is not student with id = " + student.getId());
            return new InvalidId();
        });
        return studentRepository.save(oldStudent.fillByStudent(student));
    }

    public void deleteStudent(Long studentId) {
        logger.info("Was invoked method for student deleteStudent");
        studentRepository.deleteById(studentId);
    }

    public List<Student> findByAge(int age) {
        logger.info("Was invoked method for student findByAge");
        return studentRepository.findByAge(age);
    }

    public List<Student> getAll() {
        logger.info("Was invoked method for student getAll");
        return studentRepository.findAll();
    }

    public List<Student> findByAgeBetween(int fromAge, int toAge) {
        logger.info("Was invoked method for student findByAgeBetween");
        return studentRepository.findByAgeBetween(fromAge, toAge);
    }

    public Faculty getStudentsFaculty(Long studentId) {
        logger.info("Was invoked method for student getStudentsFaculty");
        Student student = studentRepository.findById(studentId).orElseThrow(() -> {
            logger.error("There is not student with id = " + studentId);
            return new EntityNotFoundException();
        });
        return student.getFaculty();
    }

    public int getAvgAgeStudents() {
        logger.info("Was invoked method for student getAvgAgeStudents");
        return studentRepository.getAvgAgeStudents();
    }

    public List<Student> getLastStudentBySize(int size) {
        logger.info("Was invoked method for student getLastStudentBySize");
        return studentRepository.getLastFiveStudents(size);
    }

    public List<String> findStudentsStartNameBySymbol(String symbol) {
        return studentRepository.findByNameStartingWithIgnoreCase(symbol)
            .stream()
            .map(s -> s.getName().toUpperCase())
            .sorted()
            .collect(Collectors.toList());
    }

    public Double getStudentsAvgAge() {
        return studentRepository.findAll().stream()
            .mapToInt(Student::getAge)
            .average()
            .orElse(0)
        ;
    }

    public void printStudentsFromStream() {
        List<List<Student>> partitionedStudents = getPartitionedStudents();
        for (int i = 0; i < partitionedStudents.size(); i++) {
            List<Student> studentsBatch = partitionedStudents.get(i);
            if (i < 1) {
                print(studentsBatch);
            } else {
                new Thread(() -> print(studentsBatch)).start();
            }
        }
    }

    public void printStudentsFromStreamSynchronize() {
        List<List<Student>> partitionedStudents = getPartitionedStudents();
        for (int i = 0; i < partitionedStudents.size(); i++) {
            List<Student> studentsBatch = partitionedStudents.get(i);
            if (i < 1) {
                synchronizedPrint(studentsBatch);
            } else {
                new Thread(() -> synchronizedPrint(studentsBatch)).start();
            }
        }
    }

    private List<List<Student>> getPartitionedStudents() {
        List<Student> students = studentRepository.findAll();
        return CollectionsService.split(students, 3);
    }

    private void print(List<Student> students) {
        for (Student student : students) {
            System.out.println(student.getName());
        }

        String s = "";
        for (int i = 0; i < 100_000; i++) {
            s += i;
        }
    }

    private synchronized void synchronizedPrint(List<Student> students) {
        for (Student student : students) {
            System.out.println(student.getName());
        }

        String s = "";
        for (int i = 0; i < 100_000; i++) {
            s += i;
        }
    }
}
