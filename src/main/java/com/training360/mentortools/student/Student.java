package com.training360.mentortools.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public Student(String name, String email, String githubId, String details) {
        this.name = name;
        this.email = email;
        this.githubId = githubId;
        this.details = details;
    }
}
