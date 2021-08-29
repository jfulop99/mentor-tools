package com.training360.mentortools.trainingclass;

import com.training360.mentortools.registration.RecordNotFoundException;
import com.training360.mentortools.syllabus.Syllabus;
import com.training360.mentortools.syllabus.SyllabusService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
public class TrainingClassService {

    private TrainingClassRepository trainingClassRepository;

    private SyllabusService syllabusService;

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

    public TrainingClass findTrainingClassById(Long id){
        return trainingClassRepository
                .findById(id).orElseThrow(() -> new RecordNotFoundException(URI.create("trainingclass/not-found"), "Trainingclass not found id = "+ id));
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

    @Transactional
    public TrainingClassDto addSyllabus(Long id, SyllabusCommand command) {
        TrainingClass trainingClass = findTrainingClassById(id);
        Syllabus syllabus = syllabusService.findSyllabus(command.getSyllabusId());
        if (trainingClass.getSyllabus() == null){
            syllabus.addTrainingClass(trainingClass);
        }
        return modelMapper.map(trainingClass, TrainingClassDto.class);
    }

    @Transactional
    public TrainingClassDto updateSyllabus(Long id, SyllabusCommand command) {
        TrainingClass trainingClass = findTrainingClassById(id);
        Syllabus syllabus = syllabusService.findSyllabus(command.getSyllabusId());
        if (trainingClass.getSyllabus() != null){
            trainingClass.setSyllabus(syllabus);
        }
        return modelMapper.map(trainingClass, TrainingClassDto.class);
    }
}
