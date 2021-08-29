package com.training360.mentortools.module;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements ={"delete from modules"})
class ModuleControllerIT {

    @Autowired
    TestRestTemplate template;

    @Test
    void createAndGetModulesTest() {

        template.postForObject("/api/modules", new CreateModuleCommand("1. Module", "http://t360.com/modules/1"),
                ModuleDto.class);
        template.postForObject("/api/modules", new CreateModuleCommand("2. Module", "http://t360.com/modules/2"),
                ModuleDto.class);
        template.postForObject("/api/modules", new CreateModuleCommand("3. Module", "http://t360.com/modules/3"),
                ModuleDto.class);

        List<ModuleDto> modules = template.exchange
                (
                "/api/modules",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ModuleDto>>() {}
                ).getBody();

        assertThat(modules)
                .hasSize(3)
                .extracting(ModuleDto::getTitle)
                .containsExactly("1. Module", "2. Module", "3. Module");

    }

    @Test
    void getModuleTest() {
        template.postForObject("/api/modules", new CreateModuleCommand("1. Module", "http://t360.com/modules/1"),
                ModuleDto.class);
        template.postForObject("/api/modules", new CreateModuleCommand("2. Module", "http://t360.com/modules/2"),
                ModuleDto.class);
        Long id = template.postForObject("/api/modules", new CreateModuleCommand("3. Module", "http://t360.com/modules/3"),
                ModuleDto.class).getId();

        ModuleDto moduleDto = template.getForObject("/api/modules/" + id, ModuleDto.class);

        assertEquals("3. Module", moduleDto.getTitle());
    }

    @Test
    void updateModuleTest(){
        Long id = template.postForObject("/api/modules", new CreateModuleCommand("3. Module", "http://t360.com/modules/3"),
                ModuleDto.class).getId();

        template.put("/api/modules/" + id, new UpdateModuleCommand("JPA Module", "http://t360.com/modules/jpa"));

        ModuleDto moduleDto = template.getForObject("/api/modules/" + id, ModuleDto.class);

        assertEquals("JPA Module", moduleDto.getTitle());
        assertEquals("http://t360.com/modules/jpa", moduleDto.getUrl());

    }
}