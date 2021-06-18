package com.erik.practice01.system;

import com.erik.practice01.presenter.Presenter;
import com.erik.practice01.user.DocManager;
import com.erik.practice01.user.UserManager;
import com.erik.practice01.user.exceptions.NoPatientException;

import java.util.Scanner;

public class DocSystem {
    private final DocManager docManager;
    Presenter presenter;

    public DocSystem() {
        this.presenter = new Presenter();
        this.docManager = new DocManager();
    }

    public void run(){
        while(true){
            presenter.DocSystem();
            Scanner reader = new Scanner(System.in);// Reading from System.in
            String command = reader.nextLine();
            if(command.equals("a")){
                try {
                    docManager.removeUser();
                }
                catch (NoPatientException e){
                    presenter.printErrorMessage(e);
                }
                break;
            }
            else if(command.equals("e")){
                break;
            }
        }
    }
}
