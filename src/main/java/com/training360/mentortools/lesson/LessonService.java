package com.training360.mentortools.lesson;

import com.training360.mentortools.module.Module;
import com.training360.mentortools.module.ModuleService;
import com.training360.mentortools.registration.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
public class LessonService {

    private LessonRepository lessonRepository;
    private ModuleService moduleService;
    private ModelMapper modelMapper;

    public List<LessonDto> getLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons.stream()
                .map(lesson -> modelMapper.map(lesson, LessonDto.class))
                .toList();
    }

    public LessonDto updateLesson(Long id, UpdateLessonCommand command) {
        Lesson lesson = findLessonById(id);
        String title = command.getTitle();
        String url = command.getUrl();
        if (title != null && !title.isBlank()){
            lesson.setTitle(title);
        }
        if (url != null && !url.isBlank()) {
            lesson.setUrl(command.getUrl());
        }
        lessonRepository.save(lesson);
        return modelMapper.map(lesson, LessonDto.class);
    }

    private Lesson findLessonById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(URI.create("lesson/not-found"), "Lesson not found id = " + id));
    }

    public void deleteLesson(Long id) {
        Lesson lesson = findLessonById(id);
        lessonRepository.delete(lesson);
    }

    public LessonDto getLessonById(Long id) {
        return modelMapper.map(findLessonById(id), LessonDto.class);
    }
}
