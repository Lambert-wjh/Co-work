package entity.business;

public class Company {
    private String code;
    private String name;

    public Company(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(code).append(" ").append(name);

        return sb.toString();
    }
}
