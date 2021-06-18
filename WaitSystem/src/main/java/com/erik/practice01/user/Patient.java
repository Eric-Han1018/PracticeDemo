package com.erik.practice01.user;

public class Patient extends User{
    private static int current_idx = 0;
    private final int id;
    private int my_idx;


    public Patient(String name) {
        super(name);
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

    public static void reset_list(){
        current_idx = 0;
    }

    public void change_idx(){
        this.my_idx--;
    }

    public static void change_current_idx(){
        current_idx--;
    }

    public int getMy_idx(){
        return this.my_idx;
    }


}
