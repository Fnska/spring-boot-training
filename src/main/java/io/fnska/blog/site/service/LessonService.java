package io.fnska.blog.site.service;

import io.fnska.blog.site.domain.Course;
import io.fnska.blog.site.domain.Lesson;
import io.fnska.blog.site.repository.CourseRepository;
import io.fnska.blog.site.repository.LessonRepository;
import io.fnska.blog.site.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    public void addLesson(Lesson lesson, Principal principal) {
        List<Lesson> lessons = getAllLessonsByUserAndYear(principal, lesson.getCourse().getYear());
        if (!lesson.getName().isEmpty() && !lesson.getCourse().getYear().isEmpty() && !lessons.contains(lesson)) {
            Course course = courseRepository.findCourseByYearAndUser_Login(lesson.getCourse().getYear(), principal.getName());
            if (course != null) {
                lesson.setCourse(course);
                lessonRepository.save(lesson);
            }
        }
    }

    public void deleteLesson(Lesson lesson, Principal principal) {
        /* TODO: Exception like in the Task.deleteTask() when delete last or Lesson with foreign key
            this trying to delete all links in course and task but it is impossible
        */
        if (lesson != null) {
            Course course = courseRepository.findCourseByYearAndUser_Login(lesson.getCourse().getYear(), principal.getName());
            if (course != null) {
                lesson.setCourse(course);
                lessonRepository.deleteLessonByNameAndCourse_Year(lesson.getName(), lesson.getCourse().getYear());
            }
        }
    }

    public Lesson getLessonByUserAndCourseYearAndLessonName(Principal principal, String courseYear, String lessonName) {
        List<Lesson> lessons = getAllLessonsByUserAndYear(principal, courseYear);
        Lesson lesson = findLessonByLessonName(lessons, lessonName);
        return lesson;
    }

    private static Lesson findLessonByLessonName(List<Lesson> lessons, String lessonName) {
        for (Lesson lesson : lessons) {
            if (lesson.getName().toLowerCase().equals(lessonName.toLowerCase())) {
                return lesson;
            }
        }
        return null;
    }

    public List<Lesson> getAllLessonsByUserAndYear(Principal principal, String courseYear) {

        return lessonRepository.findAllByCourse_User_LoginAndCourse_Year(principal.getName(), courseYear);
    }
}
