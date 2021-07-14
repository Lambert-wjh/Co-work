package entity.staff;

import entity.enums.Sex;

public class Person {
    protected String name;
    protected Sex sex;
    protected int age;

    protected Person(String name, Sex sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}
