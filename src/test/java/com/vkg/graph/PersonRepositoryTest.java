package com.vkg.graph;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonRepositoryTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("graph.xml");
        PersonRepository repository = context.getBean("personRepository", PersonRepository.class);
        Person person = createPerson("DDLJ");
        repository.save(person);
        Person savedPerson = repository.findOne(person.getId());
        System.out.println(savedPerson);
    }

    private static Person createPerson(String name) {
        Person person = new Person();
        person.setName(name);
        return person;
    }
}