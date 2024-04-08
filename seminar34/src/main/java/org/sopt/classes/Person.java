package org.sopt.classes;

public class Person {
    private String name;
    private int age;
    private String sex;
    //constructor
    public Person(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    //constructor overloading
    public Person(String name, int age) {
        this(name, age, "unknown");
    }

    //static method
    public static void run() {
        System.out.println("사람이 달립니다");
    }
    //instance method
    public void walk() {
        System.out.println("사람이 걷습니다");
    }

    //getter, setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //factory method
    public static Person create(String name, int age, String sex) {
        return new Person(name, age, sex);
    }

    protected String getInfo() {
        return "이름: " + this.name + "나이: " + this.age + "성별: " + this.sex;
    }
}
