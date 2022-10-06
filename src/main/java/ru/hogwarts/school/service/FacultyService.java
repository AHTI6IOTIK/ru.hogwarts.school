package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.InvalidId;
import ru.hogwarts.school.exception.NotFoundException;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private static Long counter = 0L;
    private final Map<Long, Faculty> faculties;

    public FacultyService() {
        faculties = new HashMap<>();
    }

    public FacultyService(Map<Long, Faculty> faculties) {
        this.faculties = faculties;
    }

    public Faculty createFaculty(Faculty faculty) {
        if (faculty.getId() <= counter) {
            throw new InvalidId();
        }
        faculties.put(++counter, faculty);
        return faculty;
    }

    public Faculty getFacultyById(Long facultyId) {
        if (!faculties.containsKey(facultyId)) {
            throw new NotFoundException();
        }
        return faculties.get(facultyId);
    }

    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        Faculty innerFaculty = getFacultyById(facultyId);
        return innerFaculty.fillByFaculty(faculty);
    }

    public Faculty deleteFaculty(Long facultyId) {
        return faculties.remove(getFacultyById(facultyId).getId());
    }

    public List<Faculty> findByColor(String color) {
        return faculties
            .values()
            .stream()
            .filter(f -> f.getColor().equals(color))
            .collect(Collectors.toList());
    }

    public List<Faculty> getAll() {
        return new ArrayList<>(faculties.values());
    }
}
