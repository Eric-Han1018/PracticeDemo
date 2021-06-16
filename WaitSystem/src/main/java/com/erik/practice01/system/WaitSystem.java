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
        while(true){
            presenter.logInPrompt();
            Scanner reader = new Scanner(System.in);// Reading from System.in
            String command = reader.nextLine();
            if(command.equals("a")){
                presenter.NumCheck();
                Scanner reader01 = new Scanner(System.in);
                String num = reader.nextLine();
                int n = Integer.parseInt(num);
                for(int i=0; i<n; i++){
                    usermanager.addUser();
                }
                System.out.println("Join in waitlsit successfully");
                ListSystem listSystem = new ListSystem(usermanager);
                listSystem.run();
            }
            else if(command.equals("e")){
                presenter.LogOutPrompt();
                break;
            }

        }
    }
}
