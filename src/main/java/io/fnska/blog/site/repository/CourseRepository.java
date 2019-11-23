package io.fnska.blog.site.repository;

import io.fnska.blog.site.domain.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    @Modifying
    @Transactional
    void deleteCourseByYearAndUser_Id(String year, Long id);

    Course findCourseByYear(String courseYear);

    List<Course> findAllByUser_Login(String username);

    Course findCourseByYearAndUser_Login(String courseYear, String username);

    Course findCourseByUser_Login(String username);
}
