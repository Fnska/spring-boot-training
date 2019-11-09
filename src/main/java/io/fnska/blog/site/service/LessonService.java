package io.fnska.blog.site.service;

import io.fnska.blog.site.domain.Lesson;
import io.fnska.blog.site.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> getAllLessons(String courseYear) {
        List<Lesson> lessons = new ArrayList<>();
        lessonRepository.findByCourseYear(courseYear).forEach(lessons::add);
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        if (!lesson.getName().isEmpty()) {
            lessonRepository.save(lesson);
        }
    }

    public void deleteLesson(String lessonName) {
        if (!lessonName.isEmpty()) {
            lessonRepository.deleteById(lessonName);
        }
    }

    public Lesson getLesson(String lessonName) {
        return lessonRepository.findLessonByName(lessonName);
    }
}
