package com.erik.practice01.user;

import java.util.LinkedList;
import java.util.Queue;

public class UserManager {
    private final Queue<User> waitlist = new LinkedList<User>() {
    };

    public UserManager() {
        User.reset_list();
    }

    public void addUser(String name){
        User u = new User(name);
        waitlist.offer(u);
    }
    
    public void removeUser(String name){
        waitlist.removeIf(i -> i.getName().equals(name));
    }

    public int current_n(){
        return waitlist.size();
    }

    public User getUser(String name){
        for(User i:waitlist){
            if(i.getName().equals(name)){
                return i;
            }
        }
        return null;
    }
}
