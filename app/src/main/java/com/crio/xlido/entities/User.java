package com.crio.xlido.entities;

public class User {
    public static void resetCounter() {
        counter = 1;
    }
    private static long counter = 1;
    private final long id;
    private final String email;
    private final String password;

    public User(String email2, String password2) {
        this.id = counter++;
        this.email = email2;
        this.password = password2;
    }

    public long getId() { return id; }
    public String getEmail() { return email; }
}
