package com.training360.mentortools.trainingclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public TrainingClass(String name, CourseInterval courseInterval) {
        this.name = name;
        this.courseInterval = courseInterval;
    }
}
