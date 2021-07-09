package Business;

public class Company {
    private String number;
    private String name;

    public Company(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public void SetNumber(String number) {
        this.number = number;
    }

    public String GetNumber() {
        return this.number;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public String GetName() {
        return this.name;
    }
}
