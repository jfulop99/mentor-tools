package com.training360.mentortools.trainingclass;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainingClassService {

    private TrainingClassRepository trainingClassRepository;

    private ModelMapper modelMapper;

    public List<TrainingClassDto> getTrainingClasses() {
        return trainingClassRepository.findAll()
                .stream()
                .map(trainingClass -> modelMapper.map(trainingClass, TrainingClassDto.class))
                .toList();
    }

    public TrainingClassDto addTrainingClass(CreateTrainingClassCommand command) {
        TrainingClass trainingClass = new TrainingClass(command.getName(),
                command.getCourseInterval());
        trainingClassRepository.save(trainingClass);
        return modelMapper.map(trainingClass, TrainingClassDto.class);
    }

    public TrainingClassDto getTrainingClassById(Long id) {
        return modelMapper.map(findTrainingClassById(id), TrainingClassDto.class);
    }

    private TrainingClass findTrainingClassById(Long id){
        return trainingClassRepository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("Trainingclass not found id = "+ id));
    }

    public TrainingClassDto updateTrainingClassById(Long id, UpdateTrainingClassCommand command) {
        TrainingClass trainingClass = findTrainingClassById(id);
        trainingClass.setName(command.getName());
        trainingClass.setCourseInterval(command.getCourseInterval());
        trainingClassRepository.save(trainingClass);
        return modelMapper.map(trainingClass, TrainingClassDto.class);
    }

    public void deleteTrainingClassById(Long id) {
        TrainingClass trainingClass = findTrainingClassById(id);
        trainingClassRepository.delete(trainingClass);
    }
}
