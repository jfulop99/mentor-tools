package com.training360.mentortools.syllabus;

import com.training360.mentortools.module.Module;
import com.training360.mentortools.module.ModuleService;
import com.training360.mentortools.registration.RecordNotFoundException;
import com.training360.mentortools.trainingclass.TrainingClassRepository;
import com.training360.mentortools.trainingclass.TrainingClassService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
public class SyllabusService {

    private SyllabusRepository syllabusRepository;
    private ModelMapper modelMapper;
    private ModuleService moduleService;
    private TrainingClassRepository trainingClassRepository;

    public SyllabusDto createSyllabus(CreateSyllabusCommand command) {
        Syllabus syllabus = new Syllabus(command.getName());
        syllabusRepository.save(syllabus);
        return modelMapper.map(syllabus, SyllabusDto.class);
    }

    public List<SyllabusDto> listSyllabuses() {
        return syllabusRepository.findAll().stream()
                .map(syllabus -> modelMapper.map(syllabus, SyllabusDto.class))
                .toList();
    }

    public SyllabusDto getSyllabusById(Long id) {
        Syllabus syllabus = findSyllabus(id);
        return modelMapper.map(syllabus, SyllabusDto.class);
    }

    public Syllabus findSyllabus(Long id) {
        return syllabusRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(URI.create("syllabus/not-found"), "Syllabus not found id = " + id));
    }

    @Transactional
    public SyllabusDto updateSyllabusById(Long id, UpdateSyllabusCommand command) {
        Syllabus syllabus = findSyllabus(id);
        syllabus.setName(command.getName());
        syllabusRepository.save(syllabus);
        return modelMapper.map(syllabus, SyllabusDto.class);
    }

    @Transactional
    public void deleteSyllabusById(Long id) {
        Syllabus syllabus = findSyllabus(id);
        trainingClassRepository.findAllBySyllabus_Id(id)
                .forEach(trainingClass -> trainingClass.setSyllabus(null));
        syllabusRepository.delete(syllabus);
    }

    @Transactional
    public SyllabusDto addModule(Long id, AddModuleCommand command) {
        Module module = moduleService.findModule(command.getModuleId());
        Syllabus syllabus = findSyllabus(id);
        syllabus.addModule(module);
        return modelMapper.map(syllabus, SyllabusDto.class);
    }
}
