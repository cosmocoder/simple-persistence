package org.simplepersistence.testmodel.employee;

public class User {
    private final String login;
    private String name;

    public User(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
