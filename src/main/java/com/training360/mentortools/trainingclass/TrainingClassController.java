package com.training360.mentortools.trainingclass;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/trainingclasses")
@AllArgsConstructor
public class TrainingClassController {

    private TrainingClassService trainingClassService;

    @GetMapping
    public List<TrainingClassDto> getTrainingClasses(){
        return trainingClassService.getTrainingClasses();
    }

    @PostMapping
    public TrainingClassDto addTrainingClass(@Valid @RequestBody CreateTrainingClassCommand command){
        return trainingClassService.addTrainingClass(command);
    }

    @GetMapping("/{id}")
    public TrainingClassDto getTrainingClassById(@PathVariable Long id){
        return trainingClassService.getTrainingClassById(id);
    }

    @PutMapping("/{id}")
    public TrainingClassDto updateTrainingClassById(@PathVariable Long id, @RequestBody UpdateTrainingClassCommand command){
        return trainingClassService.updateTrainingClassById(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainingClassById(@PathVariable Long id){
        trainingClassService.deleteTrainingClassById(id);
    }

}
