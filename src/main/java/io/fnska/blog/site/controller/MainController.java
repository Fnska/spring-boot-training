package io.fnska.blog.site.controller;

import io.fnska.blog.site.domain.Course;
import io.fnska.blog.site.domain.Lesson;
import io.fnska.blog.site.domain.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String main() {
        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin/admin";
    }

    @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model) {
        model.addAttribute("th_course_form", new Course());
        model.addAttribute("th_lesson_form", new Lesson());
        model.addAttribute("th_task_form", new Task());
        return "admin/dashboard";
    }

}
