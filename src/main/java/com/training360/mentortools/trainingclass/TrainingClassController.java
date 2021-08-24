package com.training360.mentortools.trainingclass;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/trainingclasses")
@AllArgsConstructor
@Tag(name = "Operations on training classes")
public class TrainingClassController {

    private TrainingClassService trainingClassService;

    @GetMapping
    @Operation(summary = "List of trainingclasses")
    public List<TrainingClassDto> getTrainingClasses(){
        return trainingClassService.getTrainingClasses();
    }

    @PostMapping
    @Operation(summary = "Create a trainingclass")
    public TrainingClassDto addTrainingClass(@Valid @RequestBody CreateTrainingClassCommand command){
        return trainingClassService.addTrainingClass(command);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a trainingclass by id")
    public TrainingClassDto getTrainingClassById(@PathVariable Long id){
        return trainingClassService.getTrainingClassById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a trainingclass")
    public TrainingClassDto updateTrainingClassById(@PathVariable Long id, @RequestBody UpdateTrainingClassCommand command){
        return trainingClassService.updateTrainingClassById(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a trainingclass")
    public void deleteTrainingClassById(@PathVariable Long id){
        trainingClassService.deleteTrainingClassById(id);
    }

}
