package io.fnska.blog.site.controller;

import io.fnska.blog.site.domain.Lesson;
import io.fnska.blog.site.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @RequestMapping(value = "user/{username}/courses/{courseYear}/lessons", method = RequestMethod.GET)
    public String getAllLessonsByUsername(@PathVariable("username") String username,
                                          @PathVariable String courseYear,
                                          Principal principal, Model model) {
        if (username.equalsIgnoreCase(principal.getName())) {
            model.addAttribute("th_courseYear", courseYear);
            model.addAttribute("th_lessons", lessonService.getAllLessonsByUserAndYear(principal, courseYear));
            return "complex/lessons";
        }
        return "error/403";
    }

    @RequestMapping(value = "user/{username}/courses/{courseYear}/lessons/{lessonName}", method = RequestMethod.GET)
    public String getLessonByUsername(@PathVariable("username") String username,
                                      @PathVariable String courseYear,
                                      @PathVariable String lessonName,
                                      Principal principal, Model model) {
        if (username.equalsIgnoreCase(principal.getName())) {
            model.addAttribute("th_courseYear", courseYear);
            model.addAttribute("th_lesson", lessonService.getLessonByUserAndCourseYearAndLessonName(principal, courseYear, lessonName));
            return "single/lesson";
        }
        return "error/403";
    }

    @RequestMapping(value = "user/{username}/dashboard/create-lesson", method = RequestMethod.POST)
    public String addLesson(@PathVariable("username") String username,
                            @ModelAttribute Lesson lesson, Principal principal) {
        if (username.equalsIgnoreCase(principal.getName()) && !(lesson.getName().isEmpty())) {
            lessonService.addLesson(lesson, principal);
            return "redirect:/user/{username}/dashboard";
        }
        return "error/403";
    }

    @RequestMapping(value = "user/{username}/dashboard/delete-lesson", method = RequestMethod.POST)
    public String deleteLesson(@PathVariable("username") String username,
                               @ModelAttribute Lesson lesson, Principal principal) {
        if (username.equalsIgnoreCase(principal.getName()) && !(lesson.getName().isEmpty())) {
            lessonService.deleteLesson(lesson, principal);
            return "redirect:/user/{username}/dashboard";
        }
        return "error/403";
    }

}
