package Staff;

public class Person {
    private String name;
    private Sex sex;
    private int age;

    public Person(String name, Sex sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public String GetName() {
        return this.name;
    }

    public void SetSex(Sex sex) {
        this.sex = sex;
    }

    public Sex GetSex() {
        return this.sex;
    }

    public void SetAge(int age) {
        this.age = age;
    }

    public int GetAge() {
        return this.age;
    }
}

enum Sex {
    MALE, FEMALE;
}
