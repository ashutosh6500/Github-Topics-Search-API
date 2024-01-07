package tech.app.githubsearch.helper;

import java.util.List;

public class APIResponse {
    private List<String> topics;
    private String sortBy;

    public APIResponse(List<String> topics, String sortBy) {
        this.topics = topics;
        this.sortBy = sortBy;
    }
    public APIResponse(){}

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
