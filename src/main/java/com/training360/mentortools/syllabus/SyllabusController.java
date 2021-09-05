package com.training360.mentortools.syllabus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Operations on syllabuses")
@RequestMapping("/api/syllabuses")
public class SyllabusController {

    private SyllabusService syllabusService;

    @PostMapping
    @Operation(description = "Create a syllabus")
    public SyllabusDto createSyllabus(@Valid @RequestBody CreateSyllabusCommand command){
        return syllabusService.createSyllabus(command);
    }

    @GetMapping
    @Operation(description = "List all syllabuses")
    public List<SyllabusDto> listSyllabuses(){
        return syllabusService.listSyllabuses();
    }

    @GetMapping("/{id}")
    @Operation(description = "Get syllabus by id")
    public SyllabusDto getSyllabusById(@PathVariable Long id){
        return syllabusService.getSyllabusById(id);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update syllabus by id")
    public SyllabusDto updateSyllabusById(@PathVariable Long id, @Valid @RequestBody UpdateSyllabusCommand command){
        return syllabusService.updateSyllabusById(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete syllabus by id")
    public void deleteSyllabusById(@PathVariable Long id){
        syllabusService.deleteSyllabusById(id);
    }

    @PostMapping("/{id}/modules")
    @Operation(summary = "Add module to syllabus")
    public SyllabusDto addModule(@PathVariable Long id, @Valid @RequestBody AddModuleCommand command){
        return syllabusService.addModule(id, command);
    }
}
