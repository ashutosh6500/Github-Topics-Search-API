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

    public Person(String userId, String password, Set<GithubRepository> repos) {
        this.userId = userId;
        this.password = password;
        this.repos = repos;
    }
    public Person(){}




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

    public Set<GithubRepository> getRepos() {
        return repos;
    }

    public void setRepos(Set<GithubRepository> repos) {
        this.repos = repos;
    }
}
