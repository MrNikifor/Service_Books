/*
package com.example.demo.servises;

import com.example.demo.entity.Person;
import com.example.demo.entity.enums.Role;
import com.example.demo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createPerson(Person person){
        String login = person.getLogin();
        if(personRepository.findByLogin(login) != null){
            return false;
        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.getRoles().add(Role.ROLE_USER);
        log.info("Сохраняем нового Пользователя с логином: {}", login);
        return true;
    }
    public List<Person> findAll(){
        return personRepository.findAll();
    }
    public Person findById(long id){
        return personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Пользователя с таким id не найден!"));
    }

    public UserDetails findByLogin(String login){
        return personRepository.findByLogin(login);

    }
}

*/
