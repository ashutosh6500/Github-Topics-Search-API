package tech.app.githubsearch.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.app.githubsearch.models.Person;
import tech.app.githubsearch.repository.UserRepository;
import tech.app.githubsearch.service.UserService;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody Person p){
        Map<String,Object> map = new LinkedHashMap<>();
        for(Person person : userRepository.findAll()){
            if(person.getUserId().equals(p.getUserId())){
                map.put("status",0);
                map.put("msg","User with given ID already exist!");
                return  new ResponseEntity<>(map,HttpStatus.CONFLICT);
            }
        }
        userService.savePerson(p);
        map.put("status",1);
        map.put("msg","User Added Successfully");
        return  new ResponseEntity<>(map,HttpStatus.CREATED);
    }
    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers(){
        List<Person> personList= userService.getPerson();

        Map<String,Object> map = new LinkedHashMap<>();
        if(personList.size() == 0){
            map.put("status",1);
            map.put("msg","Its Nothing Here!");
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        map.put("status",1);
        map.put("data",personList);
        return new ResponseEntity<>(map,HttpStatus.OK);

         //return new ResponseEntity<>(personList,HttpStatus.OK);
    }
    @GetMapping("/checkUser/{email}/{password}")
    public ResponseEntity<?> checkUser(@PathVariable String email,@PathVariable String password){
        Map<String,Object> map = new LinkedHashMap<>();

        try {
            return userService.checkUser(email,password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        map.put("status",1);
        map.put("msg","Error!");
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

}
