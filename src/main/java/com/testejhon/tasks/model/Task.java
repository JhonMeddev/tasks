package com.testejhon.tasks.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "tb_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O campo titulo não pode estar vazio e nem pode ser preenchido com espaços.")
    @Size(min = 5, max = 100,  message = "O campo titulo deve conter entre 5 e 100 caracteres.")
    private String title;

    @NotBlank(message = "O campo descrição não pode estar vazio e nem pode ser preenchido com espaços.")
    @Size(min = 5, max = 1000,  message = "O campo texto deve conter entre 5 e 1000 caracteres.")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new java.sql.Date(System.currentTimeMillis());

    @NotNull(message = "Defina uma data limite para a tarefa.")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @NotNull(message = "O campo status não pode estar vazio.")
    private Status status;

    @ManyToOne
    @JsonIgnoreProperties("task")
    private UserModel user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
