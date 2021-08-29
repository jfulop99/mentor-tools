package com.training360.mentortools.syllabus;

import com.training360.mentortools.registration.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
public class SyllabusService {

    private SyllabusRepository syllabusRepository;
    private ModelMapper modelMapper;

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

    public SyllabusDto updateSyllabusById(Long id, UpdateSyllabusCommand command) {
        Syllabus syllabus = findSyllabus(id);
        syllabus.setName(command.getName());
        syllabusRepository.save(syllabus);
        return modelMapper.map(syllabus, SyllabusDto.class);
    }

    public void deleteSyllabusById(Long id) {
        Syllabus syllabus = findSyllabus(id);
        syllabusRepository.delete(syllabus);
    }
}
