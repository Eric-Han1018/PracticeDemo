package com.erik.practice01.user;

import java.util.LinkedList;
import java.util.Queue;

public class UserManager {
    private final Queue<User> waitlist = new LinkedList<User>() {
    };

    public UserManager() {
        User.reset_list();
    }

    public void addUser(){
        User u = new User();
        waitlist.offer(u);
    }
    
    public User removeUser(){
        return waitlist.poll();
    }

    public int current_n(){
        return waitlist.size();
    }
}
