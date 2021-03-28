package com.zavrsnirad.CodeFlow.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Objects;

@Entity
public class Programmer extends TimeAndUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="programmer_id")
    private Long programmerId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    @Min(0)
    private Integer taskPoints;

    @Column(nullable = false)
    @Min(0)
    private Integer solutionPoints;

    @OneToMany(mappedBy = "programmer")
    private List<Follower> followers;

    public Programmer() {
    }

    public Programmer(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public Programmer(String username, String email, String password, String role, @Min(0) Integer taskPoints, @Min(0) Integer solutionPoints) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.taskPoints = taskPoints;
        this.solutionPoints = solutionPoints;
    }

    public Programmer(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Integer getTaskPoints() {
        return taskPoints;
    }

    public void setTaskPoints(Integer taskPoints) {
        this.taskPoints = taskPoints;
    }

    public Integer getSolutionPoints() {
        return solutionPoints;
    }

    public void setSolutionPoints(Integer solutionPoints) {
        this.solutionPoints = solutionPoints;
    }

    public List<Follower> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follower> followers) {
        this.followers = followers;
    }

    public Long getProgrammerId() {
        return programmerId;
    }

    public void setProgrammerId(Long programmerId) {
        this.programmerId = programmerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programmer that = (Programmer) o;
        return Objects.equals(programmerId, that.programmerId) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(role, that.role) && Objects.equals(taskPoints, that.taskPoints) && Objects.equals(solutionPoints, that.solutionPoints) && Objects.equals(followers, that.followers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programmerId, username, email, password, role, taskPoints, solutionPoints, followers);
    }
}
