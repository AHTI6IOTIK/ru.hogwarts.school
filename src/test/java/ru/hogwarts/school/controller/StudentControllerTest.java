package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController userController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @Test
    public void getAllStudentsTest() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
            "http://localhost:" + port + "/student",
            String.class
        );
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void createStudentsTest() {
        Student expected = (new Student())
            .setAge(50)
            .setName("Student Test");

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
            "http://localhost:" + port + "/student/create",
            expected,
            String.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getStudentTest() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
            "http://localhost:" + port + "/student/1",
            String.class
        );
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}