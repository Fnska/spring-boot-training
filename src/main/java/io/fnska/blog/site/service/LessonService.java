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
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    public List<Lesson> getAllLessons(String courseYear) {
        List<Lesson> lessons = new ArrayList<>();
        lessonRepository.findByCourseYear(courseYear).forEach(lessons::add);
        return lessons;
    }

    public void addLesson(Lesson lesson, Principal principal) {
        if (!lesson.getName().isEmpty() && !lesson.getCourse().getYear().isEmpty()) {
            Course course = courseRepository.findCourseByYearAndUser_Login(lesson.getCourse().getYear(), principal.getName());
            if (course != null) {
                lesson.setCourse(course);
                lessonRepository.save(lesson);
            }
        }
    }

    public void deleteLesson(Lesson lesson, Principal principal) {
        if (lesson != null) {
            Course course = courseRepository.findCourseByYearAndUser_Login(lesson.getCourse().getYear(), principal.getName());
            if (course != null) {
                lesson.setCourse(course);
                lessonRepository.deleteLessonByNameAndCourse_Year(lesson.getName(), lesson.getCourse().getYear());
            }
        }
    }

    public Lesson getLesson(String lessonName) {
        return lessonRepository.findLessonByName(lessonName);
    }

    public List<Lesson> getAllLessonsByUserAndYear(Principal principal, String courseYear) {

        return lessonRepository.findAllByCourse_User_LoginAndCourse_Year(principal.getName(), courseYear);
    }
}
