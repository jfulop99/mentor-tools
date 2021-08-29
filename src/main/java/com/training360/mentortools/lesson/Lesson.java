package com.training360.mentortools.lesson;

import com.training360.mentortools.module.Module;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String url;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    public Lesson(String title, String url) {
        this.title = title;
        this.url = url;
    }
}
