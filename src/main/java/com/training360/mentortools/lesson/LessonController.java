package com.training360.mentortools.lesson;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@AllArgsConstructor
@Tag(name = "Operations on Lessons")
public class LessonController {

    private LessonService lessonService;

    @GetMapping
    @Operation(description = "List all lessons")
    public List<LessonDto> getLessons(){
        return lessonService.getLessons();
    }

    @GetMapping("/{id}")
    @Operation(description = "Get lesson by id")
    public LessonDto getLessonById(@PathVariable Long id){
        return lessonService.getLessonById(id);
    }

    @PutMapping("/{id}")
    public LessonDto updateLesson(@PathVariable Long id, @RequestBody UpdateLessonCommand command){
        return lessonService.updateLesson(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Long id){
        lessonService.deleteLesson(id);
    }
}
