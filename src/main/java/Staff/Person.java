package Staff;

import Tools.Enums.Sex;

public class Person {
    protected String name;
    protected Sex sex;
    protected int age;


    protected Person(String name, Sex sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }


    protected void SetName(String name) {
        this.name = name;
    }
    protected String GetName() {
        return this.name;
    }


    protected void SetSex(Sex sex) {
        this.sex = sex;
    }
    protected Sex GetSex() {
        return this.sex;
    }


    protected void SetAge(int age) {
        this.age = age;
    }
    protected int GetAge() {
        return this.age;
    }
}
