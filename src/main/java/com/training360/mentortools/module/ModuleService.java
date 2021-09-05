package com.training360.mentortools.module;

import com.training360.mentortools.lesson.*;
import com.training360.mentortools.registration.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
public class ModuleService {

    private ModuleRepository moduleRepository;
    private LessonRepository lessonRepository;
    private ModelMapper modelMapper;

    public List<ModuleDto> findModules() {
        return moduleRepository.findAll().stream()
                .map(module -> modelMapper.map(module, ModuleDto.class))
                .toList();
    }

    public ModuleDto createModule(CreateModuleCommand command) {

        Module module = new Module(command.getTitle(), command.getUrl());
        moduleRepository.save(module);
        return modelMapper.map(module, ModuleDto.class);
    }

    public ModuleDto getModule(Long id) {
        Module module = findModule(id);
        return modelMapper.map(module, ModuleDto.class);
    }

    public Module findModule(Long id) {
        return moduleRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(URI.create("module/not-found"), "Module not found id = " + id));
    }

    @Transactional
    public ModuleDto updateModule(Long id, UpdateModuleCommand command) {
        Module module = findModule(id);
        module.setTitle(command.getTitle());
        module.setUrl(command.getUrl());
        return modelMapper.map(module, ModuleDto.class);
    }

    public void deleteModule(Long id) {
        Module module = findModule(id);
        moduleRepository.delete(module);
    }

    @Transactional
    public LessonDto createLesson(Long id, CreateLessonCommand command) {
        Module module = findModule(id);
        Lesson lesson = new Lesson(command.getTitle(), command.getUrl());
        module.addLesson(lesson);
        lessonRepository.save(lesson);

        return modelMapper.map(lesson, LessonDto.class);
    }
}
