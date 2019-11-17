package io.fnska.blog.site.repository;

import io.fnska.blog.site.domain.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, String> {//TODO: String -> Long and change methods like in Course

    List<Lesson> findByCourseYear(String courseYear);

    Lesson findLessonByName(String lessonName);
}
