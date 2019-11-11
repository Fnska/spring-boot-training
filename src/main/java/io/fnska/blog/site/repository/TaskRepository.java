package io.fnska.blog.site.repository;

import io.fnska.blog.site.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, String> {
    public List<Task> findByLessonName(String lessonName);

    public Task findTaskByNumber(String taskNumber);

}
