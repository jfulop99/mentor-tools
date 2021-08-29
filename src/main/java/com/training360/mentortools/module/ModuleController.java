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

}
