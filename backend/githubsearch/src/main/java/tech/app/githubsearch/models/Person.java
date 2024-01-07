package tech.app.githubsearch.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Person {
    @Id
    private String userId;

    @Column
    private String password;

    public Person(String userId, String password, String email, Set<GithubRepository> repos) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.repos = repos;
    }
    public Person(){}

    @Column
    private String email;


    @ManyToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private Set<GithubRepository> repos = new HashSet<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<GithubRepository> getRepos() {
        return repos;
    }

    public void setRepos(Set<GithubRepository> repos) {
        this.repos = repos;
    }
}
