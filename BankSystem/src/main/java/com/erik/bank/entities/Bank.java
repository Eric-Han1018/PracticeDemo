package com.erik.bank.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Bank {
    private int balance;
    private static final ArrayList<User> userlist = new ArrayList<>();

    public Bank(int balance) {
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public static ArrayList<User> getUserlist() {
        return userlist;
    }
}
