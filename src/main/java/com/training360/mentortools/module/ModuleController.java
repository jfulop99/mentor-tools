package com.training360.mentortools.module;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Operations on modules")
@RequestMapping("/api/modules")
public class ModuleController {

    private ModuleService moduleService;

    @GetMapping
    @Operation(summary = "List all modules")
    public List<ModuleDto> findModules(){
        return moduleService.findModules();
    }

    @PostMapping
    @Operation(summary = "Create a module")
    public ModuleDto createModule(@Valid @RequestBody CreateModuleCommand command){
        return moduleService.createModule(command);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a module")
    public ModuleDto getModule(@PathVariable Long id){
        return moduleService.getModule(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a module")
    public ModuleDto updateModule(@PathVariable Long id, @Valid @RequestBody UpdateModuleCommand command){
        return moduleService.updateModule(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a module by id")
    public void deleteModule(@PathVariable Long id){
        moduleService.deleteModule(id);
    }

}
