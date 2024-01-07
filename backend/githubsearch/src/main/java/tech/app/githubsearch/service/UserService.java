package tech.app.githubsearch.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.app.githubsearch.models.*;
import tech.app.githubsearch.repository.*;
import tech.app.githubsearch.models.Person;
import tech.app.githubsearch.repository.UserRepository;

import java.util.List;

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

}
