package io.fnska.blog.site.repository;

import io.fnska.blog.site.domain.Lesson;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {

    List<Lesson> findAllByCourse_User_LoginAndCourse_Year(String username, String courseYear);

    @Modifying
    @Transactional
    void deleteLessonByNameAndCourse_Year(String lessonName, String courseYear);

    Lesson findLessonByNameAndCourse_YearAndCourse_User_Login(String lessonName, String courseYear, String username);

}
