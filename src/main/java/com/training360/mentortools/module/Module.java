package com.training360.mentortools.module;

import com.training360.mentortools.lesson.Lesson;
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
@Table(name = "modules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String url;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    public void addLesson(Lesson lesson){
        if (lessons == null){
            lessons = new ArrayList<>();
        }
        lessons.add(lesson);
        lesson.setModule(this);
    }

    public Module(String title, String url) {
        this.title = title;
        this.url = url;
    }
}
