package com.training360.mentortools.registration;

import com.training360.mentortools.student.Student;
import com.training360.mentortools.trainingclass.TrainingClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_class_id")
    private TrainingClass trainingClass;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RegistrationStatus registrationStatus;

    public Registration(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
