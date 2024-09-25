package com.testejhon.tasks.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O campo nome não pode estar vazio e nem pode ser preenchido com espaços.")
    @Size(min = 5, max = 100, message = "O nome precisa ter entre 5 e 100 carácteres.")
    private String username;

    @NotBlank(message = "O campo senha não pode estar vazio e nem pode ser preenchido com espaços.")
    @Size(min = 5, max = 100, message = "A senha precisa ter entre 5 e 100 carácteres.")
    private String password;

    @NotBlank(message = "O campo nivel não pode estar vazio e nem pode ser preenchido com espaços.")
    @Size(min = 4, max = 5, message = "O nivel deve ser 'adm' ou 'user'. ")
    private String nivel;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<Task> task;

    public UserModel(long id, String username, String password, String nivel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nivel = nivel;
    }

    public UserModel() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
}
