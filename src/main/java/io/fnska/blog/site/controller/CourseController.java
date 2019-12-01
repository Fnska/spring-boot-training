package io.fnska.blog.site.controller;

import io.fnska.blog.site.domain.Course;
import io.fnska.blog.site.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "user/{username}/courses", method = RequestMethod.GET)
    public String getAllCoursesByUsername(@PathVariable("username") String username,
                                          Principal principal, Model model) {
        if (username.equalsIgnoreCase(principal.getName())) {
            model.addAttribute("th_courses", courseService.getAllCoursesByUsername(principal));
            return "complex/courses";
        }
        return "error/403";
    }

    @RequestMapping(value = "user/{username}/courses/{courseYear}", method = RequestMethod.GET)
    public String getCourse(@PathVariable("username") String username,
                            @PathVariable String courseYear,
                            Principal principal, Model model) {
        if (username.equalsIgnoreCase(principal.getName())) {
            model.addAttribute("th_course", courseService.getCourseByYearAndUsername(principal, courseYear));
            return "single/course";
        }
        return "error/403";
    }

    @RequestMapping(value = "user/{username}/dashboard/create-course", method = RequestMethod.POST)
    public String addCourse(@PathVariable("username") String username,
                            @ModelAttribute Course course, Principal principal) {
        if (username.equalsIgnoreCase(principal.getName())) {
            courseService.addCourse(course, principal);
            return "redirect:/user/{username}/dashboard";
        }
        return "error/403";
    }

    @RequestMapping(value = "user/{username}/dashboard/delete-course", method = RequestMethod.POST)
    public String deleteCourse(@PathVariable("username") String username,
                               @ModelAttribute Course course, Principal principal) {
        if (username.equalsIgnoreCase(principal.getName())) {
            courseService.deleteCourse(course.getYear(), principal);
            return "redirect:/user/{username}/dashboard";
        }
        return "error/403";
    }
}
