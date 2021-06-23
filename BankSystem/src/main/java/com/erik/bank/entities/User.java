package com.erik.bank.entities;

public class User {
    private static int id = 0;
    private String name;
    private String password;
    private int balance;

    public User(String name, String password) {
        id++;
        this.name = name;
        this.password = password;
        this.balance = 0;
    }

    public static int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public static void setId(int id) {
        User.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
