package com.training360.mentortools.syllabus;

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
@Sql(statements ={"delete from syllabuses"})
class SyllabusControllerIT {

    @Autowired
    TestRestTemplate template;

    @Test
    void createAndListSyllabusTest() {

        template.postForObject("/api/syllabuses",
                new CreateSyllabusCommand("Java Junior Backend"), SyllabusDto.class);
        template.postForObject("/api/syllabuses",
                new CreateSyllabusCommand("Junior Frontend"), SyllabusDto.class);
        template.postForObject("/api/syllabuses",
                new CreateSyllabusCommand("Junior Tester"), SyllabusDto.class);

        List<SyllabusDto> syllabuses = template.exchange("/api/syllabuses",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SyllabusDto>>() {
                }).getBody();

        assertThat(syllabuses).hasSize(3)
                .extracting(SyllabusDto::getName)
                .containsExactly("Java Junior Backend", "Junior Frontend", "Junior Tester");

    }

    @Test
    void getSyllabusTest(){
        Long id = template.postForObject("/api/syllabuses",
                new CreateSyllabusCommand("Java Junior Backend"), SyllabusDto.class).getId();

        SyllabusDto syllabusDto = template.getForObject("/api/syllabuses/" + id, SyllabusDto.class);

        assertEquals("Java Junior Backend", syllabusDto.getName());
    }

    @Test
    void updateSyllabusTest(){
        Long id = template.postForObject("/api/syllabuses",
                new CreateSyllabusCommand("Java Junior Backend"), SyllabusDto.class).getId();

        template.put("/api/syllabuses/" + id, new UpdateSyllabusCommand("Vállalati Backend"));

        SyllabusDto syllabusDto = template.getForObject("/api/syllabuses/" + id, SyllabusDto.class);

        assertEquals("Vállalati Backend", syllabusDto.getName());
    }

    @Test
    void deleteSyllabusTest(){
        Long id = template.postForObject("/api/syllabuses",
                new CreateSyllabusCommand("Java Junior Backend"), SyllabusDto.class).getId();

        template.delete("/api/syllabuses/" + id);

        Problem problem = template.getForObject("/api/syllabuses/" + id, Problem.class);

        assertEquals("404 Not Found", problem.getStatus().toString());
    }
}

