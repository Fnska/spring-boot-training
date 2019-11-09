package io.fnska.blog.site.domain;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
public class Course {
    @Id
    private String year;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course")
    private List<Lesson> lessons;

    public Course() {
    }

    public Course(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return year;
    }
}
