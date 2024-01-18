package tech.app.githubsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tech.app.githubsearch.helper.APIResponse;
import tech.app.githubsearch.models.GithubRepository;
import tech.app.githubsearch.models.Person;
import tech.app.githubsearch.repository.GithubRepoRepository;
import tech.app.githubsearch.repository.UserRepository;
import tech.app.githubsearch.service.GithubRepoService;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GithubRepositoryController {
    @Autowired
    private GithubRepoService githubRepoService;
    @Autowired
    private GithubRepoRepository githubRepoRepository;
    @Autowired
    private UserRepository userRepository;
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/addrepo")
    public ResponseEntity<?> addRepoForUser(@RequestBody GithubRepository g){
        Map<String,String>mp  = new LinkedHashMap<>();
        try {
            for (Person p : userRepository.findAll()) {
                if (p.getUserId().equals(g.getUsers().iterator().next().getUserId())) {
                    //traversing in repos for this person
                    for (GithubRepository repo : p.getRepos()) {
                        if (repo.getRepoId().equals(g.getRepoId())) {
                            //repo is already there
                            mp.put("msg","This Repository is already saved!");
                            return new ResponseEntity<>(mp, HttpStatus.CONFLICT);
                        }
                    }
                }
            }
            githubRepoRepository.save(g);
            mp.put("msg","Repository Added Successfully!");
            return ResponseEntity.ok(mp);
        }
        catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could Not save Repo",e);
        }
    }

    @GetMapping("/getRepos/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> addRepoForUser(@PathVariable String userId){
        try {
            Set<GithubRepository> repositories = githubRepoService.getReposofUser(userId);
            return new ResponseEntity<>(repositories,HttpStatus.OK);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not get repo",e);
        }
    }
    @GetMapping("/getReposWithTopics/{sortBy}/{topics}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<GithubRepository> getReposWithTopics(@PathVariable String sortBy,@PathVariable List<String>topics) throws IOException {
        try {
            List<GithubRepository> githubRepositories = githubRepoService.getReposBasedonTopics(sortBy,topics);
            return githubRepositories;
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error Fetching Repos",e);

        }
    }
    @DeleteMapping("/deleteRepo/{userId}/{repoId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> deleteRepo(@PathVariable String userId,@PathVariable String repoId){
        Map<String,String> mp = new LinkedHashMap<>();
        try {
            githubRepoService.deleteRepo(userId,repoId);
            mp.put("msg","Successfully deleted Repo");
            return new ResponseEntity<>(mp,HttpStatus.OK);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"error Occured while Deleting",e);

        }
    }
}
