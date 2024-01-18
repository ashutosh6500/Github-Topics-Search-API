package tech.app.githubsearch.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.app.githubsearch.models.*;
import tech.app.githubsearch.repository.*;
import tech.app.githubsearch.models.Person;
import tech.app.githubsearch.repository.UserRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Person savePerson(Person p){
        return userRepository.save(p);
    }
    public List<Person> getPerson(){
        return userRepository.findAll();
    }

    public ResponseEntity<?> checkUser(String email,String password){
        Map<String,String> mp  = new LinkedHashMap<>();
        for(Person p : userRepository.findAll()){
            if(p.getUserId().equals(email)){
                if(p.getPassword().equals(password)){
                    mp.put("msg","Welcome");
                    return new ResponseEntity<>(mp, HttpStatus.ACCEPTED);
                }
                //Correct Email but wrong password
                mp.put("msg","Wrong Password");
                return new ResponseEntity<>(mp, HttpStatus.BAD_REQUEST);
            }
        }
        mp.put("status","No User");
        return new ResponseEntity<>(mp, HttpStatus.NOT_FOUND);
    }
    public void deleteUser(String userId){
        for(var user : userRepository.findAll()){
            if(user.getUserId().equals(userId)){
                userRepository.delete(user);
                break;
            }
        }

    }
}
