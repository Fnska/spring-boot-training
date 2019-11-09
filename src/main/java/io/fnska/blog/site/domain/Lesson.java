package io.fnska.blog.site.domain;

import javax.persistence.*;
import java.util.List;


@Entity
public class Lesson {
    @Id
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "lesson")
    private List<Task> tasks;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_year")
    private Course course;

    public Lesson() {
    }

    @Override
    public String toString() {
        return name;
    }

    public Lesson(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
