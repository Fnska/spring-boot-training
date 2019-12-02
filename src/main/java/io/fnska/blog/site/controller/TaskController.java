package io.fnska.blog.site.controller;

import io.fnska.blog.site.domain.Task;
import io.fnska.blog.site.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("user/{username}/courses/{courseYear}/lessons/{lessonName}/tasks")
    public String getAllTasksByUsername(@PathVariable("username") String username,
                                        @PathVariable String courseYear,
                                        @PathVariable String lessonName,
                                        Principal principal, Model model) {
        if (username.equalsIgnoreCase(principal.getName())) {
            model.addAttribute("th_courseYear", courseYear);
            model.addAttribute("th_lesson_name", lessonName);
            model.addAttribute("th_tasks", taskService.getAllTasksByUserAndLessonName(principal, courseYear, lessonName));
            return "complex/tasks";
        }
        return "error/403";
    }

    @RequestMapping(value = "user/{username}/courses/{courseYear}/lessons/{lessonName}/tasks/{taskNumber}", method = RequestMethod.GET)
    public String getLessonByUsername(@PathVariable("username") String username,
                                      @PathVariable String courseYear,
                                      @PathVariable String lessonName,
                                      @PathVariable String taskNumber,
                                      Principal principal, Model model) {
        if (username.equalsIgnoreCase(principal.getName())) {
            model.addAttribute("th_courseYear", courseYear);
            model.addAttribute("th_task", taskService.getTaskByNumberAndUser(principal, courseYear, lessonName, taskNumber));
            return "single/task";
        }
        return "error/403";
    }


    @RequestMapping(value = "user/{username}/dashboard/create-task", method = RequestMethod.POST)
    public String addTask(@PathVariable("username") String username, @ModelAttribute Task task, Principal principal) {
        if (username.equalsIgnoreCase(principal.getName())
                && isInputTaskValid(task)) {
            taskService.addTask(task, principal);
            return "redirect:/user/{username}/dashboard";
        }
        return "error/403";
    }

    private static boolean isInputTaskValid(Task task) {
        if ((task.getNumber().isEmpty()
                || task.getLesson().getName().isEmpty()
                || task.getLesson().getCourse().getYear().isEmpty())) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "user/{username}/dashboard/delete-task", method = RequestMethod.POST)
    public String deleteTask(@PathVariable("username") String username, @ModelAttribute Task task, Principal principal) {
        if (username.equalsIgnoreCase(principal.getName())
                && !(task.getNumber().isEmpty())
                && !(task.getLesson().getName().isEmpty())
                && !(task.getLesson().getCourse().getYear().isEmpty())) {
            taskService.deleteTask(task, principal);
            return "redirect:/user/{username}/dashboard";
        }
        return "error/403";
    }

}
