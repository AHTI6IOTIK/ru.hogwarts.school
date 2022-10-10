package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Faculty {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String color;

    @JsonBackReference
    @OneToMany(mappedBy = "faculty")
    private Set<Student> students;

    public Faculty() {}

    public String getName() {
        return name;
    }

    public Faculty setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Faculty setColor(String color) {
        this.color = color;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Faculty setId(Long id) {
        this.id = id;
        return this;
    }

    public Faculty fillByFaculty(Faculty faculty) {
        color = faculty.color;
        name = faculty.name;
        return this;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Faculty setStudents(Set<Student> students) {
        this.students = students;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;
        Faculty faculty = (Faculty) o;
        return id.equals(faculty.id) && name.equals(faculty.name) && color.equals(faculty.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }
}
