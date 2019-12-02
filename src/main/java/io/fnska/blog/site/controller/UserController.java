package io.fnska.blog.site.controller;

import io.fnska.blog.site.domain.Course;
import io.fnska.blog.site.domain.Lesson;
import io.fnska.blog.site.domain.Task;
import io.fnska.blog.site.domain.User;
import io.fnska.blog.site.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "user/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("th_user_form", new User());
        return "user/registration";
    }

    @RequestMapping(value = "user/registration/create", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "user/{username}/dashboard", method = RequestMethod.GET)
    public String dashboard(@PathVariable("username") String username, Principal principal, Model model) {
        if (username.equalsIgnoreCase(principal.getName())) {
            model.addAttribute("th_course_form", new Course());
            model.addAttribute("th_lesson_form", new Lesson());
            model.addAttribute("th_task_form", new Task());
            model.addAttribute("th_username", username);
            return "user/dashboard";
        } else {
            return "error/403";
        }
    }
}
