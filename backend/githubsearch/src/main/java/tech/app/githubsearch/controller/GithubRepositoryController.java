package tech.app.githubsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.app.githubsearch.helper.APIResponse;
import tech.app.githubsearch.models.GithubRepository;
import tech.app.githubsearch.service.GithubRepoService;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
public class GithubRepositoryController {

    @Autowired
    private GithubRepoService githubRepoService;

    @PostMapping("/addrepo")
    public GithubRepository addRepoForUser(@RequestBody GithubRepository g){
        return githubRepoService.saveRepo(g);
    }

    @GetMapping("/getRepos/{userId}")
    public Set<GithubRepository> addRepoForUser(@PathVariable String userId){
        return githubRepoService.getReposofUser(userId);
    }
    @GetMapping("/getReposWithTopics")
    public  List<GithubRepository> getReposWithTopics(@RequestBody APIResponse apiResponse) throws IOException {
        return githubRepoService.getReposBasedonTopics(apiResponse);
    }
}
