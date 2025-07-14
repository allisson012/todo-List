package com.allissonsilva.todosimple.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = User.TABLE_NAME)
public class User {

    public interface CreateUser {
    }

    public interface UpdateUser {
    }

    private static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(min = 2, max = 100, groups = CreateUser.class)
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    @NotNull(groups = { CreateUser.class, UpdateUser.class })
    @NotEmpty(groups = { CreateUser.class, UpdateUser.class })
    @Size(min = 8, max = 60, groups = { CreateUser.class, UpdateUser.class })
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<Task>();

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.username, other.username)
                && Objects.equals(this.password, other.password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
}
