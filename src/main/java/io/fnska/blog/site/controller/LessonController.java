package io.fnska.blog.site.controller;

import io.fnska.blog.site.domain.Lesson;
import io.fnska.blog.site.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @RequestMapping("/courses/{courseYear}/lessons")
    public String getAllLessons(@PathVariable String courseYear, Model model) {
        model.addAttribute("th_courseYear", courseYear);
        model.addAttribute("th_lessons", lessonService.getAllLessons(courseYear));
        return "complex/lessons";
    }

    @RequestMapping(value = "/courses/{courseYear}/lessons/{lessonName}", method = RequestMethod.GET)
    public String getLesson(@PathVariable String lessonName, Model model) {
        model.addAttribute("th_lesson", lessonService.getLesson(lessonName));
        return "single/lesson";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/create-lesson", method = RequestMethod.POST)
    public String addLesson(@ModelAttribute Lesson lesson) {
        lessonService.addLesson(lesson);
        return "redirect:/admin/dashboard";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/delete-lesson", method = RequestMethod.POST)
    public String deleteLesson(@ModelAttribute Lesson lesson) {
        lessonService.deleteLesson(lesson.getName());
        return "redirect:/admin/dashboard";
    }

}
