package io.fnska.blog.site.controller;

import io.fnska.blog.site.domain.Task;
import io.fnska.blog.site.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/courses/{courseYear}/lessons/{lessonName}/tasks")
    @ResponseBody
    public List<Task> getAllTasks(@PathVariable String lessonName) {
        return taskService.getAllTasks(lessonName);
    }

}
