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

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/courses/{courseYear}/lessons/{lessonName}/tasks")
    public String getAllTasks(@PathVariable String lessonName, Model model) {
        model.addAttribute("th_lessonName", lessonName);
        model.addAttribute("th_tasks", taskService.getAllTasks(lessonName));
        return "complex/tasks";
    }

    @RequestMapping(value = "/courses/{courseYear}/lessons/{lessonName}/tasks/{taskNumber}", method = RequestMethod.GET)
    public String getLesson(@PathVariable String taskNumber, Model model) {
        model.addAttribute("th_task", taskService.getTask(taskNumber));
        return "single/task";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/create-task", method = RequestMethod.POST)
    public String addTask(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:/admin/dashboard";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/delete-task", method = RequestMethod.POST)
    public String deleteTask(@ModelAttribute Task task) {
        taskService.deleteTask(task.getNumber());
        return "redirect:/admin/dashboard";
    }

}
