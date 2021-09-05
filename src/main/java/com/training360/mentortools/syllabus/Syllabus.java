package com.training360.mentortools.syllabus;

import com.training360.mentortools.module.Module;
import com.training360.mentortools.trainingclass.TrainingClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "syllabuses")
public class Syllabus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "syllabus", cascade = CascadeType.PERSIST)
    private List<TrainingClass> trainingClasses;

    @ManyToMany
    @JoinTable(name = "syllabuses_modules", joinColumns = @JoinColumn(name = "syllabus_id"),
    inverseJoinColumns = @JoinColumn(name = "module_id"))
    private List<Module> modules;

    public void addTrainingClass(TrainingClass trainingClass){
        if (trainingClasses == null){
            trainingClasses = new ArrayList<>();
        }
        trainingClasses.add(trainingClass);
        trainingClass.setSyllabus(this);
    }

    public void addModule(Module module){
        if (modules == null){
            modules = new ArrayList<>();
        }
        modules.add(module);
    }

    public Syllabus(String name) {
        this.name = name;
    }
}
