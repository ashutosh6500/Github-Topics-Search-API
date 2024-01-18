package tech.app.githubsearch.controller;
import ch.qos.logback.core.joran.conditional.ThenAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tech.app.githubsearch.models.Person;
import tech.app.githubsearch.repository.UserRepository;
import tech.app.githubsearch.service.UserService;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/saveUser")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveUser(@RequestBody Person p){
       Map<String,String> mp = new LinkedHashMap<>();
        try {
            for (Person person : userRepository.findAll()) {
                if (person.getUserId().equals(p.getUserId())) {
                    mp.put("msg","User is already there with this Email!");
                    return new ResponseEntity<>(mp,HttpStatus.CONFLICT);
                }
            }
            userService.savePerson(p);
            mp.put("msg","User Added Succcesfully!");
            return new ResponseEntity<>(mp, HttpStatus.CREATED);
        }
        catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error Ocurred!",e);
        }
    }
    @GetMapping("/getUsers")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> getUsers(){
        try {
            List<Person> personList = userService.getPerson();
            return new ResponseEntity<>(personList, HttpStatus.OK);

        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error Occurred :",e);
        }
    }
    @GetMapping("/checkUser/{email}/{password}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> checkUser(@PathVariable String email,@PathVariable String password){

        try {
            return userService.checkUser(email,password);
        }
        catch (Exception e){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error checking creds",e);
        }

    }
    @DeleteMapping("/deleteUser/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        Map<String,String>mp = new LinkedHashMap<>();
        try {
            userService.deleteUser(id);
            mp.put("msg","Deleted Successfully!");
            return new ResponseEntity<>(mp,HttpStatus.OK);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error Occurred while delete",e);
        }

    }

}
