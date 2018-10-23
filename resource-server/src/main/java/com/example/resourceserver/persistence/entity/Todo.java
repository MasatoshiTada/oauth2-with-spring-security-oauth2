package com.example.resourceserver.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todoIdGenerator")
    @SequenceGenerator(name = "todoIdGenerator", sequenceName = "seq_todo_id", initialValue = 4, allocationSize = 1)
    private Integer id;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private LocalDate deadline;

    private Boolean done;

    public Todo() {
    }

    public Todo(String description, LocalDate deadline) {
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.deadline = deadline;
        this.done = Boolean.FALSE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
