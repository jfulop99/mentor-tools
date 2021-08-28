package com.training360.mentortools.student;

import com.training360.mentortools.trainingclass.TrainingClassDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;
import org.zalando.problem.Problem;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements ={"delete from registrations", "delete from students"})
class StudentControllerIT {

    @Autowired
    TestRestTemplate template;

    @Test
    void getStudentsTest() {

        template.postForObject("/api/students",
                new CreateStudentCommand("John Doe", "john.doe@email.com"), StudentDto.class);

        template.postForObject("/api/students",
                new CreateStudentCommand("Jane Doe", "jane.doe@email.com"), StudentDto.class);

        template.postForObject("/api/students",
                new CreateStudentCommand("Jack Doe", "jack.doe@email.com"), StudentDto.class);

        List<StudentDto> students = template.exchange
                        ("/api/students",
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<List<StudentDto>>() {})
                .getBody();

        assertThat(students).hasSize(3)
                .extracting(StudentDto::getName)
                .containsExactly("John Doe", "Jane Doe", "Jack Doe");
    }

    @Test
    void getStudentByIdTest(){

        StudentDto studentDto = template.postForObject("/api/students",
                new CreateStudentCommand("John Doe", "john.doe@email.com"), StudentDto.class);

        Long id = studentDto.getId();

        StudentDto otherStudentDto = template.getForObject("/api/students/" + id, StudentDto.class);

        assertEquals("John Doe", otherStudentDto.getName());
    }

    @Test
    void deleteStudentByIdTest(){
        StudentDto studentDto = template.postForObject("/api/students",
                new CreateStudentCommand("John Doe", "john.doe@email.com"), StudentDto.class);

        Long id = studentDto.getId();

        template.delete("/api/students/" + id);

        Problem problem = template.getForObject("/api/students/" + id, Problem.class);

        assertEquals("404 Not Found", problem.getStatus().toString());
        assertEquals("Cannot find student id = "+ id, problem.getDetail());

    }
}