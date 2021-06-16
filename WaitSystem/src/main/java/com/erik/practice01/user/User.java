package com.erik.practice01.user;

public class User {
    private int id;
    private static int current_idx = 0;

    public User() {
        this.id = current_idx;
        current_idx += 1;
    }

    public int getId() {
        return id;
    }

    public static int getCurrent_idx() {
        return current_idx;
    }

    public static void reset_list(){
        current_idx = 0;
    }

}
