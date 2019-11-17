package io.fnska.blog.site.repository;

import io.fnska.blog.site.domain.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    @Modifying
    @Transactional
    void deleteCourseByYearAndUser_Id(String year, Long id);


    Course findCourseByYear(String courseYear);

}
