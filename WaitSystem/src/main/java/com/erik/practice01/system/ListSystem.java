package com.erik.practice01.system;

import com.erik.practice01.user.UserManager;

public class ListSystem {
    private UserManager userManager;

    public ListSystem(UserManager userManager) {
        this.userManager = userManager;
    }

    public void run(){
        while(true){
            int idx = this.userManager.current_n();
            
            System.out.println("current idx: "+idx);
        }
    }
}
