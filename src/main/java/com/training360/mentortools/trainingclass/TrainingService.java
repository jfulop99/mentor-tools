package com.training360.mentortools.trainingclass;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainingService {

    private TrainingClassRepository trainingClassRepository;

    private ModelMapper modelMapper;

    public List<TrainingClassDto> getTrainingClasses() {
        return trainingClassRepository.findAll()
                .stream()
                .map(trainingClass -> modelMapper.map(trainingClass, TrainingClassDto.class))
                .toList();
    }
}
