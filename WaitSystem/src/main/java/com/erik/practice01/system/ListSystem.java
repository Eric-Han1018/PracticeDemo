package com.erik.practice01.system;

import com.erik.practice01.presenter.Presenter;
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
        while(true) {
            presenter.GetIntoList();
            int idx = this.userManager.current_n();
            Scanner reader = new Scanner(System.in);
            String command = reader.nextLine();
            if(idx == 0){
                System.out.println("It's your turn\n");
                System.exit(1);
            }
            if (command.equals("a")) {
                check_times++;
                if(check_times==3){ //one person leave line every 3 times
                    check_times = 0;
                    userManager.removeUser();
                }
                System.out.println("current idx: " + idx);
            }
            else if (command.equals("e")){
                userManager.removeUser();
                return;
            }
        }
    }
}
