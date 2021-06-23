package com.erik.bank.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static final Map<String, User> userlist = new HashMap<>();

    public static Map<String, User> getUserlist() {
        return userlist;
    }

    public void addUser(String name, String password){
        User user = new User(name,password);
        userlist.put(name, user);
    }

    public void deleteUser(String name){
        userlist.remove(name);
    }

    public void changePassword(String name, String password){
        User user = userlist.get(name);
        user.setPassword(password);
    }

    public boolean checkUser(String name){
        return userlist.get(name) == null;
    }

    public int checkBalance(String name){
        return userlist.get(name).getBalance();
    }

    public void withdraw(String name, int n){
        int balance = userlist.get(name).getBalance();
        userlist.get(name).setBalance(balance-n);
    }

    public void deposite(String name, int n){
        int balance = userlist.get(name).getBalance();
        userlist.get(name).setBalance(balance+n);
    }

}
