package tech.app.githubsearch.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.app.githubsearch.models.Person;
import tech.app.githubsearch.service.UserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public Person saveUser(@RequestBody Person p){
        return userService.savePerson(p);
    }
    @GetMapping("/getUsers")
    public List<Person> getUsers(){
        return userService.getPerson();
    }
    @GetMapping("/welcome")
    public String welcome(){
        return "hello world";
    }
}
