package com.erik.practice01.system;

import com.erik.practice01.presenter.Presenter;
import com.erik.practice01.user.UserManager;

import java.util.Scanner;

public class WaitSystem {
    UserManager usermanager;
    Presenter presenter;

    public WaitSystem() {
        this.presenter = new Presenter();
        this.usermanager = new UserManager();
    }

    public void run(){
        label:
        while(true){
            presenter.logInPrompt();
            Scanner reader = new Scanner(System.in);// Reading from System.in
            String command = reader.nextLine();
            switch (command) {
                case "a": {
                    presenter.nameCheck();
                    Scanner reader01 = new Scanner(System.in);
                    String name = reader.nextLine();
                    System.out.println(name + " join in waitlsit successfully");
                    usermanager.addUser(name);
                    break;
                }
                case "b": {
                    ListSystem listSystem = new ListSystem(usermanager);
                    listSystem.run();
                    break;
                }
                case "e":
                    presenter.LogOutPrompt();
                    break label;
            }

        }
    }
}
