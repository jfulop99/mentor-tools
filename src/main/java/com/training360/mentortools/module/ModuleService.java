package com.training360.mentortools.module;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModuleService {

    private ModuleRepository moduleRepository;
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
}
