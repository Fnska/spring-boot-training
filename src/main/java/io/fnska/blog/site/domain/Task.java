package io.fnska.blog.site.domain;

import javax.persistence.*;


@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private long id;

    @Column(nullable = false)
    private String number;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "lesson_id")
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

    @Override
    public String toString() {
        return "Task{" +
                "number='" + number + '\'' +
                ", lesson=" + lesson +
                ", description='" + description + '\'' +
                ", solutionLink='" + solutionLink + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
