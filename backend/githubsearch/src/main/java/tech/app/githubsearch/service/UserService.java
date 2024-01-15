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
        Map<String,Object> response = new LinkedHashMap<>();
        for(Person p : userRepository.findAll()){
            if(p.getUserId().equals(email)){
                if(p.getPassword().equals(password)){
                    response.put("status",1);
                    response.put("msg","welcome");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                //Correct Email but wrong password
                response.put("status",0);
                response.put("msg","Wrong Password!");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        response.put("status",1);
        response.put("msg","No User With Given Mail Id!");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
