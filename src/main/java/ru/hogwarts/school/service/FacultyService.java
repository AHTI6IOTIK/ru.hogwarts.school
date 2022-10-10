package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.InvalidId;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow(EntityNotFoundException::new);
    }

    public Faculty updateFaculty(Faculty faculty) {
        Faculty oldFaculty = facultyRepository.findById(faculty.getId()).orElseThrow(InvalidId::new);
        return facultyRepository.save(oldFaculty.fillByFaculty(faculty));
    }

    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    public List<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> findByColorContainingIgnoreCase(String color) {
        return facultyRepository.findByColorContainingIgnoreCase(color);
    }

    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public Set<Student> getFacultyStudents(Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(InvalidId::new);
        return faculty.getStudents();
    }
}
