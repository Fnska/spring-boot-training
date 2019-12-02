package io.fnska.blog.site.repository;

import io.fnska.blog.site.domain.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAllByLesson_Id(Long lessonId);

    @Modifying
    @Transactional
    void deleteTaskByNumberAndLesson_Id(String taskNumber, Long lessonId);
}
