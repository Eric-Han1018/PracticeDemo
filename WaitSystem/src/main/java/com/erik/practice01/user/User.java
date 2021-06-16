package com.erik.practice01.user;

public class User {
    private final int id;
    private String name;
    private static int current_idx = 0;
    private int my_idx;

    public User() {
        this.id = current_idx;
        current_idx += 1;
        this.my_idx = current_idx;
    }

    public int getId() {
        return id;
    }

    public static int getCurrent_idx() {
        return current_idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void reset_list(){
        current_idx = 0;
    }

    public void change_idx(){
        this.my_idx--;
    }

}
