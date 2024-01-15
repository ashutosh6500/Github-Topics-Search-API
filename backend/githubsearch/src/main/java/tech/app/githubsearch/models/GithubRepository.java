package tech.app.githubsearch.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class GithubRepository {
    @Id
    private String repoId;
    private String name;
    private long stargazersCount;
    private long forkCount;
    private String recentupdateTime;
    private String projectUrl;
    private String description;
    private String cloneUrl;
    private List<String> topicList;


    public GithubRepository(String name,String repoId, long stargazersCount, long forkCount, String recentupdateTime, String projectUrl, String description, String cloneUrl, List<String> topicList, Set<Person> users) {
        this.repoId = repoId;
        this.name = name;
        this.stargazersCount = stargazersCount;
        this.forkCount = forkCount;
        this.recentupdateTime = recentupdateTime;
        this.projectUrl = projectUrl;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.topicList = topicList;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GithubRepository(){}
    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinTable(
            name = "User_Repositories",
            joinColumns = {@JoinColumn(name = "repoId")},
            inverseJoinColumns = {@JoinColumn(name = "userId")}
    )
    Set<Person> users = new HashSet<>();

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public long getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(long stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public List<String> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<String> topicList) {
        this.topicList = topicList;
    }

    public Set<Person> getUsers() {
        return users;
    }

    public void setUsers(Set<Person> users) {
        this.users = users;
    }

    public long getForkCount() {
        return forkCount;
    }

    public void setForkCount(long forkCount) {
        this.forkCount = forkCount;
    }

    public String getRecentupdateTime() {
        return recentupdateTime;
    }

    public void setRecentupdateTime(String recentupdateTime) {
        this.recentupdateTime = recentupdateTime;
    }
}
