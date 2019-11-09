package io.fnska.blog.site.repository;

import io.fnska.blog.site.domain.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, String> {
    Course findCourseByYear(String courseYear);
}
