package com.training360.mentortools.trainingclass;

import com.training360.mentortools.registration.Registration;
import com.training360.mentortools.syllabus.Syllabus;
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
@Table(name = "training_classes")
public class TrainingClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private CourseInterval courseInterval;

    @OneToMany(mappedBy = "trainingClass", cascade = CascadeType.ALL)
    private List<Registration> registrations;

    @ManyToOne
    @JoinColumn(name = "syllabus_id")
    private Syllabus syllabus;

    public TrainingClass(String name, CourseInterval courseInterval) {
        this.name = name;
        this.courseInterval = courseInterval;
    }

    public void addRegistration(Registration registration){
        if (registrations == null){
            registrations = new ArrayList<>();
        }
        registrations.add(registration);
        registration.setTrainingClass(this);
    }
}
