package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int fromAge, int toAge);

    @Query(value = "SELECT COUNT(1) as countItems FROM student WHERE faculty_id = ?1", nativeQuery = true)
    int getStudentsCountByFaculty(Long facultyId);

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    int getAvgAgeStudents();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT ?1", nativeQuery = true)
    List<Student> getLastFiveStudents(int count);

    List<Student> findByNameStartingWithIgnoreCase(String symbol);
}
