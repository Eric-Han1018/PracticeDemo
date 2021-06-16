package com.erik.practice01.system;

import com.erik.practice01.presenter.Presenter;
import com.erik.practice01.user.UserManager;

import java.util.Scanner;

public class WaitSystem {
    UserManager usermanager;
    Presenter presenter;

    public WaitSystem() {
        this.presenter = new Presenter();
    }

    public void run(){
        while(true){
            usermanager = new UserManager();
            presenter.logInPrompt();
            Scanner reader = new Scanner(System.in);// Reading from System.in
            String command = reader.nextLine();
            if(command.equals("a")){
                usermanager.addUser();
                System.out.println("Join in waitlsit successfully");
                ListSystem listSystem = new ListSystem(usermanager);
                listSystem.run();
            }
            else if(command.equals("e")){
                break;
            }

        }
    }
}
