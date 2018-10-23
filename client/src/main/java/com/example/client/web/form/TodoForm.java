package com.example.client.web.form;

import com.example.client.service.dto.Todo;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class TodoForm {

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deadline;

    public TodoForm() {}

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Todo convertToDto() {
        return new Todo(description, deadline);
    }
}
