package io.fnska.blog.site.controller;

import io.fnska.blog.site.domain.Course;
import io.fnska.blog.site.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String getAllCourse(Model model) {
        model.addAttribute("th_courses", courseService.getAllCourses());
        return "complex/courses";
    }

    @RequestMapping(value = "/courses/{courseYear}", method = RequestMethod.GET)
    public String getCourse(@PathVariable String courseYear, Model model) {
        model.addAttribute("th_course", courseService.getCourse(courseYear));
        return "single/course";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/create-course", method = RequestMethod.POST)
    public String addCourse(@ModelAttribute Course course) {
        courseService.addCourse(course);
        return "redirect:/admin/dashboard";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/delete-course", method = RequestMethod.POST)
    public String deleteCourse(@ModelAttribute Course course) {
        courseService.deleteCourse(course.getYear());
        return "redirect:/admin/dashboard";
    }

}
