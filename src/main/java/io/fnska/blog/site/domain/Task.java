package io.fnska.blog.site.domain;

import javax.persistence.*;


@Entity
public class Task {
    @Id
    private String number;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "lesson_name")
    private Lesson lesson;

    private String description;

    private String solutionLink;

    public Task() {
    }

    public Task(String number, String description, String solutionLink) {
        this.number = number;
        this.description = description;
        this.solutionLink = solutionLink;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSolutionLink() {
        return solutionLink;
    }

    public void setSolutionLink(String solutionLink) {
        this.solutionLink = solutionLink;
    }
}
