package tech.app.githubsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.app.githubsearch.helper.APIResponse;
import tech.app.githubsearch.models.GithubRepository;
import tech.app.githubsearch.models.Person;
import tech.app.githubsearch.repository.GithubRepoRepository;
import tech.app.githubsearch.repository.UserRepository;
import tech.app.githubsearch.service.GithubRepoService;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class GithubRepositoryController {
    @Autowired
    private GithubRepoService githubRepoService;
    @Autowired
    private GithubRepoRepository githubRepoRepository;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/addrepo")
    public ResponseEntity<?> addRepoForUser(@RequestBody GithubRepository g){
        Map<String,Object> map = new LinkedHashMap<>();
        for(Person p :userRepository.findAll()){
            if(p.getUserId().equals(g.getUsers().iterator().next().getUserId())){
                //traversing in repos for this person
                for(GithubRepository repo : p.getRepos()){
                    if(repo.getRepoId().equals(g.getRepoId())){
                        //repo is already there
                        map.put("status",0);
                        map.put("msg","This Repository is already saved!");
                        return new ResponseEntity<>(map,HttpStatus.OK);
                    }
                }
            }
        }
        githubRepoRepository.save(g);
        map.put("msg","Added Successfully!");
        map.put("status",1);
        return new ResponseEntity<>(map,HttpStatus.CREATED);
    }

    @GetMapping("/getRepos/{userId}")
    public ResponseEntity<Set<GithubRepository>> addRepoForUser(@PathVariable String userId){
        Set<GithubRepository> repositories = githubRepoService.getReposofUser(userId);
        if(repositories.size() == 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.of(Optional.of(repositories));
    }
    @GetMapping("/getReposWithTopics/{sortBy}/{topics}")
    public List<GithubRepository> getReposWithTopics(@PathVariable String sortBy,@PathVariable List<String>topics) throws IOException {
        Map<String,Object> map = new LinkedHashMap<>();
        try {
            List<GithubRepository> githubRepositories = githubRepoService.getReposBasedonTopics(sortBy,topics);
            return githubRepositories;
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();

        }
    }
}
