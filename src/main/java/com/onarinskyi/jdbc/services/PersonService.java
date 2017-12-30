package com.onarinskyi.jdbc.services;

import com.onarinskyi.jdbc.entities.Person;
import com.onarinskyi.jdbc.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person getPerson(long id) {
        //and do something else
        return repository.getPerson(id);
    }
}
