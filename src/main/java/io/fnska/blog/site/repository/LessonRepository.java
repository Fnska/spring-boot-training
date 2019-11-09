package io.fnska.blog.site.repository;

import io.fnska.blog.site.domain.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, String> {
    public List<Lesson> findByCourseYear(String courseYear);

    public Lesson findLessonByName(String lessonName);
}
