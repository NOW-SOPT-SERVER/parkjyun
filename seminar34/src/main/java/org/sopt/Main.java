package org.sopt;

import org.sopt.classes.Person;
import org.sopt.classes.PersonBuilder;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("박재연", 27, "Male");
        person.setName("박박재재연연");
        System.out.println(person.getName());
        Person.run();
        person.walk();

        Person personWithBuilder = new PersonBuilder()
                .name("박재연")
                .age(27)
                .sex("male")
                .build();

        Person personWithFactory = Person.create("박재연", 27, "Male");
    }
}