package com.training360.mentortools.student;

import com.training360.mentortools.registration.Registration;
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
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Column(name = "github_id")
    private String githubId;

    private String details;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> registrations;

    public Student(String name, String email, String githubId, String details) {
        this.name = name;
        this.email = email;
        this.githubId = githubId;
        this.details = details;
    }

    public void addRegistration(Registration registration){
        if (registrations == null){
            registrations = new ArrayList<>();
        }
        registrations.add(registration);
        registration.setStudent(this);
    }

}
