package org.sopt.classes;

public class PersonBuilder {
    private String name;
    private int age;
    private String sex;

    public PersonBuilder name(String name) {
        this.name = name;
        return this;
    }
    public PersonBuilder age(int name) {
        this.age = age;
        return this;
    }
    public PersonBuilder sex(String name) {
        this.sex = sex;
        return this;
    }

    public Person build() {
        return new Person(this.name, this.age, this.sex);
    }
}
