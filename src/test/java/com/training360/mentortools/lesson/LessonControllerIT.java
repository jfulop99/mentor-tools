package com.training360.mentortools.lesson;

import com.training360.mentortools.module.CreateModuleCommand;
import com.training360.mentortools.module.ModuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from lesson_completions", "delete from lessons", "delete from registrations", "delete from training_classes",
        "delete from syllabuses_modules", "delete from modules", "delete from syllabuses", "delete from students"})
class LessonControllerIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    ModuleService moduleService;

    @Test
    void createAndListLessonTest() {

        Long id = moduleService.createModule(new CreateModuleCommand("1. module", "url")).getId();

        template.postForObject("/api/modules/" + id + "/lessons",
                new CreateLessonCommand("1. lecke", "http://training360.com/fisrtLesson.html"), LessonDto.class);
        template.postForObject("/api/modules/" + id + "/lessons",
                new CreateLessonCommand("2. lecke", "http://training360.com/secondLesson.html"), LessonDto.class);
        template.postForObject("/api/modules/" + id + "/lessons",
                new CreateLessonCommand("3. lecke", "http://training360.com/thirdLesson.html"), LessonDto.class);

        List<LessonDto> lessons = template.exchange("/api/lessons",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LessonDto>>() {}).getBody();

        assertThat(lessons).hasSize(3)
                .extracting(LessonDto::getTitle)
                .containsExactly("1. lecke", "2. lecke", "3. lecke");
    }

    @Test
    void updateLessonTest() {
        Long moduleId = moduleService.createModule(new CreateModuleCommand("1. module", "url")).getId();

        Long lessonId = template.postForObject("/api/modules/" + moduleId + "/lessons",
                new CreateLessonCommand("1. lecke", "http://training360.com/fisrtLesson.html"), LessonDto.class).getId();

        template.put("/api/lessons/" + lessonId, new UpdateLessonCommand("First lesson", null));

        LessonDto lesson = template.getForObject("/api/lessons/" + lessonId, LessonDto.class);

        assertEquals("First lesson", lesson.getTitle());
        assertEquals("http://training360.com/fisrtLesson.html", lesson.getUrl());

    }

    @Test
    void deleteLessonTest() {
        Long moduleId = moduleService.createModule(new CreateModuleCommand("1. module", "url")).getId();

        Long lessonId = template.postForObject("/api/modules/" + moduleId + "/lessons",
                new CreateLessonCommand("1. lecke", "http://training360.com/fisrtLesson.html"), LessonDto.class).getId();

        template.delete("/api/lessons/" + lessonId);

        Problem problem = template.getForObject("/api/lessons/" + lessonId, Problem.class);

        assertEquals("404 Not Found", problem.getStatus().toString());
        assertEquals("Lesson not found id = "+ lessonId, problem.getDetail());


    }
}