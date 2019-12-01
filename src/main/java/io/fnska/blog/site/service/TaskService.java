package io.fnska.blog.site.service;

import io.fnska.blog.site.domain.Lesson;
import io.fnska.blog.site.domain.Task;
import io.fnska.blog.site.repository.LessonRepository;
import io.fnska.blog.site.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LessonService lessonService;
    @Autowired
    private LessonRepository lessonRepository;

    public List<Task> getAllTasksByUserAndLessonName(Principal principal, String courseYear, String lessonName) {

        List<Lesson> lessons = lessonService.getAllLessonsByUserAndYear(principal, courseYear);
        Long lessonId = findLessonIdByLessonName(lessons, lessonName);
        if (lessonId == null) {
            return new ArrayList<Task>();
        }
        return taskRepository.findAllByLesson_Id(lessonId);
    }

    private static Long findLessonIdByLessonName (List<Lesson> lessons, String lessonName) {
        for (Lesson lesson : lessons) {
            if (lesson.getName().toLowerCase().equals(lessonName.toLowerCase())) {
                return lesson.getId();
            }
        }
        return null;
    }

    public Task getTaskByNumberAndUser(Principal principal, String courseYear, String lessonName, String taskNumber) {
        List<Task> tasks = getAllTasksByUserAndLessonName(principal,courseYear, lessonName);
        Task task = findTaskByNumber(tasks, taskNumber);
        return task;
    }

    private static Task findTaskByNumber(List<Task> tasks, String taskNumber) {
        for (Task task : tasks) {
            if (task.getNumber().toLowerCase().equals(taskNumber.toLowerCase())) {
                return task;
            }
        }
        return null;
    }
    // TODO: добавление и удаление Тасков, из principal найти lessonId!
//    public void addTask(Task task, Principal principal) {
//
//        Lesson lesson = lessonRepository.findLessonByNameAndCourse_Year(task.getLesson().getName(),
//                                                                        task.getLesson().getCourse().getYear());
//        task.setLesson(lesson);
//        if (!task.getNumber().isEmpty()) {
//            taskRepository.save(task);
//        }
//    }
//
//    public void deleteTask(String taskNumber, String lessonName) {
//        if (!taskNumber.isEmpty() && !lessonName.isEmpty()) {
//            taskRepository.deleteTaskByNumberAndLesson_Name(taskNumber, lessonName);
//        }
//    }
}
