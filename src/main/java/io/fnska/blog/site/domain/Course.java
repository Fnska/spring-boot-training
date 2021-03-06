package io.fnska.blog.site.domain;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private long id;

    @Column(nullable = false)
    private String year;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private List<Lesson> lessons;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


    public Course() {
    }

    public Course(String year, User user) {
        this.year = year;
        this.user = user;
    }

    public Course(String year, List<Lesson> lessons, User user) {
        this.year = year;
        this.lessons = lessons;
        this.user = user;
    }

    @Override
    public String toString() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(year, course.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
