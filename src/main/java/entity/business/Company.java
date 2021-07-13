package entity.business;

public class Company {
    private String code;
    private String name;

    public Company(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
