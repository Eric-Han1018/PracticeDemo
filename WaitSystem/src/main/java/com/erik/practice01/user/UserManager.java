package com.erik.practice01.user;

import java.util.LinkedList;
import java.util.Queue;


public class UserManager {
    private static final Queue<Patient> waitlist = new LinkedList<>();

    public UserManager() {
        Patient.reset_list();
    }

    public void addUser(String name){
        Patient u = new Patient(name);
        waitlist.offer(u);
    }


    public void removeUser(String name){
        int check_back = 0;
        Patient delete = null;
        for(Patient i:waitlist){
            if (check_back == 1){
                i.change_idx();
            }
            if(i.getName().equals(name)){
                delete = i;
                check_back++;
            }
        }
        waitlist.remove(delete);
        Patient.change_current_idx();
    }

    public int current_n(){
        return waitlist.size();
    }

    public Patient getUser(String name){
        for(Patient i:waitlist){
            if(i.getName().equals(name)){
                return i;
            }
        }
        return null;
    }

    public static Queue<Patient> getWaitlist(){
        return waitlist;
    }

    public boolean checkname(String name){
        for(Patient i:waitlist){
            if(i.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
