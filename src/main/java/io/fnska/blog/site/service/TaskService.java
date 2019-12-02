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

    private static Long findLessonIdByLessonName(List<Lesson> lessons, String lessonName) {
        for (Lesson lesson : lessons) {
            if (lesson.getName().toLowerCase().equals(lessonName.toLowerCase())) {
                return lesson.getId();
            }
        }
        return null;
    }

    public Task getTaskByNumberAndUser(Principal principal, String courseYear, String lessonName, String taskNumber) {
        List<Task> tasks = getAllTasksByUserAndLessonName(principal, courseYear, lessonName);
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

    public void addTask(Task task, Principal principal) {

        Lesson lesson = lessonRepository.findLessonByNameAndCourse_YearAndCourse_User_Login(task.getLesson().getName(),
                task.getLesson().getCourse().getYear(), principal.getName());

        task.setLesson(lesson);
        List<Task> tasks = getAllTasksByUserAndLessonName(principal, task.getLesson().getCourse().getYear(), task.getLesson().getName());
        if (!task.getNumber().isEmpty() && !tasks.contains(task)) {
            taskRepository.save(task);
        }
    }

    public void deleteTask(Task task, Principal principal) {
        Lesson lesson = lessonRepository.findLessonByNameAndCourse_YearAndCourse_User_Login(task.getLesson().getName(),
                task.getLesson().getCourse().getYear(), principal.getName());
        task.setLesson(lesson);
        if (!task.getNumber().isEmpty() && !(task.getLesson().getId() == 0) && lesson != null) {
            /* TODO : when delete last Task line below is trying to delete lesson and you get Exception:
             org.postgresql.util.PSQLException: ОШИБКА: UPDATE или DELETE в таблице "course" нарушает ограничение внешнего ключа "XXXXXX" таблицы "lesson"
            */
            taskRepository.deleteTaskByNumberAndLesson_Id(task.getNumber(), task.getLesson().getId());
        }
    }
}
