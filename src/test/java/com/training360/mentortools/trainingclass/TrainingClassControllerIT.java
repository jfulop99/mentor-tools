package com.training360.mentortools.trainingclass;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements ={"delete from training_classes"})
class TrainingClassControllerIT {

    @Autowired
    TestRestTemplate template;

    @Test
    void getAllTrainingClassTest(){
        template.postForObject("/api/trainingclasses",
                new CreateTrainingClassCommand("1. Course"), TrainingClassDto.class);

        template.postForObject("/api/trainingclasses",
                new CreateTrainingClassCommand("2. Course"), TrainingClassDto.class);

        template.postForObject("/api/trainingclasses",
                new CreateTrainingClassCommand("3. Course"), TrainingClassDto.class);


        List<TrainingClassDto> trainingClasses = template.exchange
                ("/api/trainingclasses",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<TrainingClassDto>>() {})
                .getBody();

        assertThat(trainingClasses).hasSize(3)
                .extracting(TrainingClassDto::getName)
                .containsExactly("1. Course", "2. Course", "3. Course");

    }

    @Test
    void getTrainingClassByIdTest(){
        TrainingClassDto trainingClassDto = template.postForObject("/api/trainingclasses",
                new CreateTrainingClassCommand("1. Course"), TrainingClassDto.class);

        Long id = trainingClassDto.getId();

        TrainingClassDto otherTrainingClassDto = template.getForObject("/api/trainingclasses/"+id, TrainingClassDto.class);

        assertEquals("1. Course", otherTrainingClassDto.getName());
    }

    @Test
    void updateTrainingClassByIdTest(){
        TrainingClassDto trainingClassDto = template.postForObject("/api/trainingclasses",
                new CreateTrainingClassCommand("1. Course"), TrainingClassDto.class);

        Long id = trainingClassDto.getId();

        template.put("/api/trainingclasses/"+id, new UpdateTrainingClassCommand("1. Course updated",
                new CourseInterval(LocalDate.of(2021,2,1), LocalDate.of(2021, 8, 1))));

        trainingClassDto = template.getForObject("/api/trainingclasses/"+id, TrainingClassDto.class);

        assertEquals("1. Course updated", trainingClassDto.getName());
    }

    @Test
    void deleteTrainingClassByIdTest(){
        TrainingClassDto trainingClassDto = template.postForObject("/api/trainingclasses",
                new CreateTrainingClassCommand("1. Course"), TrainingClassDto.class);

        Long id = trainingClassDto.getId();

        template.delete("/api/trainingclasses/"+id);

        Problem problem = template.getForObject("/api/trainingclasses/"+id, Problem.class);
        assertEquals("500 Internal Server Error", problem.getStatus().toString());
        assertEquals("Trainingclass not found id = "+ id, problem.getDetail());

    }

    @Test
    void createTrainingClassWithWrongDate(){

        Problem problem = template.postForObject("/api/trainingclasses",
                new CreateTrainingClassCommand("1. Course",
                        new CourseInterval(LocalDate.of(2021, 6, 1),
                                LocalDate.of(2021, 5, 1))), Problem.class);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());

        problem = template.postForObject("/api/trainingclasses",
                new CreateTrainingClassCommand("1. Course",
                        new CourseInterval(null, LocalDate.of(2021, 5, 1))), Problem.class);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());
    }

    @Test
    void createTrainingClassWirthWrongName(){

        Problem problem = template.postForObject("/api/trainingclasses",
                new CreateTrainingClassCommand("",
                        new CourseInterval(LocalDate.of(2021, 6, 1),
                                LocalDate.of(2021, 7, 1))), Problem.class);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());

    }

    @Test
    void updateTrainingClassWithWrongDate(){
        TrainingClassDto trainingClassDto = template.postForObject("/api/trainingclasses",
                new CreateTrainingClassCommand("1. Course"), TrainingClassDto.class);

        Long id = trainingClassDto.getId();

        Problem problem = template.exchange
                ("/api/trainingclasses/"+id,
                        HttpMethod.PUT,
                        null,
                        new ParameterizedTypeReference<Problem>() {
                        }).getBody();

        assertEquals(Status.BAD_REQUEST, problem.getStatus());

    }
}