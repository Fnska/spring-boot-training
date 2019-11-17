package io.fnska.blog.site.domain;

import javax.persistence.*;
import java.util.List;


@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "lesson")
    private List<Task> tasks;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    public Lesson() {
    }

    public Lesson(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    public Lesson(String name, List<Task> tasks, Course course) {
        this.name = name;
        this.tasks = tasks;
        this.course = course;
    }

    @Override
    public String toString() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
