package com.training360.mentortools.trainingclass;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/trainingclasses")
@AllArgsConstructor
public class TrainingClassController {

    private TrainingService trainingService;

    @GetMapping
    public List<TrainingClassDto> getTrainingClasses(){
        return trainingService.getTrainingClasses();
    }

}
