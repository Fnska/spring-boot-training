package io.fnska.blog.site.service;

import io.fnska.blog.site.domain.Task;
import io.fnska.blog.site.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(String lessonName) {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findByLessonName(lessonName).forEach(tasks::add);
        return tasks;
    }
}
