package com.erik.practice01.presenter;

public class Presenter {
    public void logInPrompt() {
        System.out.println("Welcome to our hospital.\n" +
                        "Input [a]: join in waitlist.\n"+
                "Input [b]: get into WaitList System\n" +
                "Input [e]: quit the system.\n");
    }

    public void LogOutPrompt(){
        System.out.println("ByeBye\n");
    }

    public void GetIntoList(){
        System.out.println("Welcome to the Waitlist\n" +
                "Input your name please");
    }

    public void WaitSystem(){
        System.out.println("Input [a]: check current index\n"+
                "Input [b]: Leave the line\n" +
                "Input [e]: exit the list\n");
    }

    public void nameCheck(){
        System.out.println("What's your name?\n");
    }

    public void notInLine(){
        System.out.println("Sorry, you are not in line");
    }
}
