package com.training360.mentortools.registration;

import com.training360.mentortools.student.CreateStudentCommand;
import com.training360.mentortools.student.StudentService;
import com.training360.mentortools.trainingclass.CreateTrainingClassCommand;
import com.training360.mentortools.trainingclass.TrainingClassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements ={"delete from registrations", "delete from training_classes", "delete from students"})
class RegistrationControllerIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    StudentService studentService;

    @Autowired
    TrainingClassService trainingClassService;

    Long firstClassId;
    Long secondClassId;
    Long thirdClassId;
    Long firstStudentId;
    Long secondStudentId;
    Long thirdStudentId;


    @BeforeEach
    void setUp() {

        CreateTrainingClassCommand firstClass = new CreateTrainingClassCommand("1. Class", null);
        CreateTrainingClassCommand secondClass = new CreateTrainingClassCommand("2. Class", null);
        CreateTrainingClassCommand thirdClass = new CreateTrainingClassCommand("3. Class", null);

        firstClassId = trainingClassService.addTrainingClass(firstClass).getId();
        secondClassId = trainingClassService.addTrainingClass(secondClass).getId();
        thirdClassId = trainingClassService.addTrainingClass(thirdClass).getId();

        CreateStudentCommand firstStudent = new CreateStudentCommand("Kiss Béla", "kiss.bela@gmail.com");
        CreateStudentCommand secondStudent = new CreateStudentCommand("Nagy Lajos", "nagy.lajos@gmail.com");
        CreateStudentCommand thirdStudent = new CreateStudentCommand("Gipsz Jakab", "gipsz.jakab@gmail.com");

        firstStudentId = studentService.createStudent(firstStudent).getId();
        secondStudentId = studentService.createStudent(secondStudent).getId();
        thirdStudentId = studentService.createStudent(thirdStudent).getId();


    }

    @Test
    void createAndGetStudentRegistrationsTest() {

        StudentRegistrationDto studentRegistrationDto1 = template.postForObject("/api/trainingclasses/" + firstClassId + "/registrations",
                new CreateRegistrationCommand(secondStudentId), StudentRegistrationDto.class);

        StudentRegistrationDto studentRegistrationDto2 = template.postForObject("/api/trainingclasses/" + firstClassId + "/registrations",
                new CreateRegistrationCommand(thirdStudentId), StudentRegistrationDto.class);

        List<RegisteredStudentDto> registeredStudents = template.exchange
                        ("/api/trainingclasses/" + firstClassId + "/registrations",
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<List<RegisteredStudentDto>>() {})
                .getBody();

        assertThat(registeredStudents)
                .hasSize(2)
                .extracting(RegisteredStudentDto::getStudent)
                .extracting(StudentRegistrationDto::getName)
                .containsExactly("Nagy Lajos", "Gipsz Jakab");


    }

    @Test
    void getClassRegistrationTest(){

        StudentRegistrationDto studentRegistrationDto1 = template.postForObject("/api/trainingclasses/" + firstClassId + "/registrations",
                new CreateRegistrationCommand(secondStudentId), StudentRegistrationDto.class);

        StudentRegistrationDto studentRegistrationDto2 = template.postForObject("/api/trainingclasses/" + secondClassId + "/registrations",
                new CreateRegistrationCommand(secondStudentId), StudentRegistrationDto.class);

        List<RegisteredClassDto> registeredStudents = template.exchange
                        ("/api/students/" + secondStudentId + "/registrations",
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<List<RegisteredClassDto>>() {})
                .getBody();

        assertThat(registeredStudents)
                .hasSize(2)
                .extracting(RegisteredClassDto::getName)
                .containsExactly("1. Class", "2. Class");

    }

    @Test
    void updateRegistrationTest(){

        StudentRegistrationDto studentRegistrationDto1 = template.postForObject("/api/trainingclasses/" + firstClassId + "/registrations",
                new CreateRegistrationCommand(secondStudentId), StudentRegistrationDto.class);

        StudentRegistrationDto studentRegistrationDto2 = template.postForObject("/api/trainingclasses/" + firstClassId + "/registrations",
                new CreateRegistrationCommand(thirdStudentId), StudentRegistrationDto.class);

        template.put("/api/trainingclasses/" + firstClassId + "/registrations", new UpdateRegistrationCommand(secondStudentId, RegistrationStatus.EXITED));

        List<RegisteredStudentDto> registeredStudents = template.exchange
                        ("/api/trainingclasses/" + firstClassId + "/registrations",
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<List<RegisteredStudentDto>>() {})
                .getBody();

        assertThat(registeredStudents)
                .hasSize(2)
                .extracting(RegisteredStudentDto::getRegistrationStatus)
                .containsExactly(RegistrationStatus.EXITED, RegistrationStatus.ACTIVE);

    }

    @Test
    void createRegistrationWithExistingIds(){
        StudentRegistrationDto studentRegistrationDto1 = template.postForObject("/api/trainingclasses/" + firstClassId + "/registrations",
                new CreateRegistrationCommand(secondStudentId), StudentRegistrationDto.class);

        Problem problem = template.postForObject("/api/trainingclasses/" + firstClassId + "/registrations",
                new CreateRegistrationCommand(secondStudentId), Problem.class);

        assertEquals("Bad Request", problem.getStatus().getReasonPhrase());
        assertEquals(URI.create("registration/exists"), problem.getType());

    }

    @Test
    void createRegistrationWithWrongClassId() {
        Problem problem = template.postForObject("/api/trainingclasses/" + (thirdClassId + 10) + "/registrations",
                new CreateRegistrationCommand(thirdStudentId), Problem.class);

        assertEquals("Not Found", problem.getStatus().getReasonPhrase());
        assertEquals(URI.create("trainingclass/not-found"), problem.getType());
    }

    @Test
    void createRegistrationWithWrongStudentId(){
        Problem problem = template.postForObject("/api/trainingclasses/" + thirdClassId + "/registrations",
                new CreateRegistrationCommand(thirdStudentId + 10), Problem.class);

        assertEquals("Not Found", problem.getStatus().getReasonPhrase());
        assertEquals(URI.create("student/not-found"), problem.getType());
    }
}
