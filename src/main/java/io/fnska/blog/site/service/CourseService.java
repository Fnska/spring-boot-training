package io.fnska.blog.site.service;

import io.fnska.blog.site.domain.Course;
import io.fnska.blog.site.domain.User;
import io.fnska.blog.site.repository.CourseRepository;
import io.fnska.blog.site.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        courses.sort((o1, o2) -> o1.getYear().compareTo(o2.getYear())); // 10 goes after 1; 21 goes after 2 adn etc.
        return courses;
    }

    public void addCourse(Course course, Principal principal) {
        if (!course.getYear().isEmpty()) {
            course.setUser(userRepository.findUserByLogin(principal.getName()));
            courseRepository.save(course);
        }
    }

    public void deleteCourse(String courseYear, Principal principal) {

        if (!courseYear.isEmpty()) {
            User user = userRepository.findUserByLogin(principal.getName());
            courseRepository.deleteCourseByYearAndUser_Id(courseYear, user.getId());
        }
    }

    public Course getCourse(String courseYear) {
        return courseRepository.findCourseByYear(courseYear);
    }

}
