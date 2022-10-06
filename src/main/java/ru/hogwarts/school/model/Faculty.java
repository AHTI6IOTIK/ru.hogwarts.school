package ru.hogwarts.school.model;

import java.util.Objects;

public class Faculty {
    private final Long id;
    private String name;
    private String color;

    public Faculty(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

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

    public Faculty fillByFaculty(Faculty faculty) {
        color = faculty.color;
        name = faculty.name;
        return this;
    }
}
