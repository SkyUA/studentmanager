package entities;

public class Person {

    private int id;
    private String surname;
    private String name;
    private String secondname;
    private String role;
    private String login;
    private String password;
    private String authorized;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSecondname() {
        return secondname;
    }
    public void setSecondname(String secondname) { this.secondname = secondname; }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAuthorized() { return authorized; }
    public void setAuthorized(String authorized) { this.authorized = authorized; }
}