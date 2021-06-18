package com.erik.practice01.user;

public abstract class User {
    private final String name;


    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
