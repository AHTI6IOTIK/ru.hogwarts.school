package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.InvalidId;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    private final Logger logger;

    public FacultyService(
        FacultyRepository facultyRepository,
        StudentRepository studentRepository
    ) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
        this.logger = LoggerFactory.getLogger(FacultyService.class);
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for faculty createFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long facultyId) {
        logger.info("Was invoked method for faculty getFacultyById");
        return facultyRepository.findById(facultyId).orElseThrow(EntityNotFoundException::new);
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Was invoked method for faculty updateFaculty");
        Faculty oldFaculty = facultyRepository.findById(faculty.getId()).orElseThrow(InvalidId::new);
        return facultyRepository.save(oldFaculty.fillByFaculty(faculty));
    }

    public void deleteFaculty(Long facultyId) {
        logger.info("Was invoked method for faculty deleteFaculty");
        facultyRepository.deleteById(facultyId);
    }

    public List<Faculty> findByColor(String color) {
        logger.info("Was invoked method for faculty findByColor");
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> findByColorContainingIgnoreCase(String color) {
        logger.info("Was invoked method for faculty findByColorContainingIgnoreCase");
        return facultyRepository.findByColorContainingIgnoreCase(color);
    }

    public List<Faculty> getAll() {
        logger.info("Was invoked method for faculty getAll");
        return facultyRepository.findAll();
    }

    public Set<Student> getFacultyStudents(Long facultyId) {
        logger.info("Was invoked method for faculty getFacultyStudents");
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(InvalidId::new);
        return faculty.getStudents();
    }

    public int getFacultyStudentsCount(Long facultyId) {
        logger.info("Was invoked method for faculty getFacultyStudentsCount");
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(InvalidId::new);
        return studentRepository.getStudentsCountByFaculty(faculty.getId());
    }

    public String getFacultyWithLongName() {
        return facultyRepository.findAll().stream()
            .max((f1, f2) -> {
                int len1 = f1.getName().length();
                int len2 = f2.getName().length();
                if (len1 < len2) {
                    return -1;
                } else if (len1 > len2) {
                    return 1;
                }

                return 0;
            })
            .map(Faculty::getName)
            .orElse("")
        ;
    }
}
