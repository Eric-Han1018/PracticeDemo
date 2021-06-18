package com.erik.practice01.system;

import com.erik.practice01.presenter.Presenter;
import com.erik.practice01.user.Patient;
import com.erik.practice01.user.User;
import com.erik.practice01.user.UserManager;

import java.util.Scanner;

public class ListSystem {
    private final UserManager userManager;
    Presenter presenter;
    int check_times;

    public ListSystem(UserManager userManager) {
        this.userManager = userManager;
        this.presenter = new Presenter();
        this.check_times = 0;
    }

    public void run(){
        label:
        while(true) {
            presenter.GetIntoList();
            int idx = this.userManager.current_n();
            Scanner reader = new Scanner(System.in);
            String name = reader.nextLine();
            Patient user = userManager.getUser(name);
            if(user==null){
                presenter.notInLine();
                break;
            }
            else{
                presenter.WaitSystem();
                Scanner reader01 = new Scanner(System.in);
                String letter = reader01.nextLine();
                switch (letter) {
                    case "a":
                        System.out.println(user.getMy_idx());
                        break label;
                    case "b":
                        presenter.LogOutPrompt();
                        userManager.removeUser(name);
                        break label;
                    case "e":
                        return;
                }
            }
//            if(idx == 0){
//                System.out.println("It's your turn\n");
//                System.exit(1);
//            }
//            if (command.equals("a")) {
//                check_times++;
//                if(check_times==3){ //one person leave line every 3 times
//                    check_times = 0;
//                    userManager.removeUser();
//                }
//                System.out.println("current idx: " + idx);
//            }
//            else if (command.equals("e")){
//                userManager.removeUser();
//                return;
//            }
        }
    }
}
