package com.erik.practice01.presenter;

public class Presenter {
    public void logInPrompt() {
        System.out.println("Welcome to our hospital.\n" +
                        "Input [a] to join in waitlist.\n"+
                "Input [e] to quit the system.\n");
    }

    public void LogOutPrompt(){
        System.out.println("ByeBye\n");
    }

    public void GetIntoList(){
        System.out.println("Welcome to the Waitlist\n" +
                "Input [a] to check current index\n"+
                "Input [e] to exit the list\n");
    }

    public void NumCheck(){
        System.out.println("How many people with you?\n");
    }
}
