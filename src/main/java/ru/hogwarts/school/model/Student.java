package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    @OneToOne
    private Avatar avatar;

    @ManyToOne
    @JsonManagedReference
    private Faculty faculty;

    public Student() {}

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Student setAge(int age) {
        this.age = age;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Student setId(Long id) {
        this.id = id;
        return this;
    }

    public Student fillByStudent(Student student) {
        name = student.getName();
        age = student.getAge();
        return this;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Student setFaculty(Faculty faculty) {
        this.faculty = faculty;
        return this;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Student setAvatar(Avatar avatar) {
        this.avatar = avatar;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return age == student.age && id.equals(student.id) && name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }
}
