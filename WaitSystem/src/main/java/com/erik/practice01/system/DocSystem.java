package com.erik.practice01.system;

import com.erik.practice01.presenter.Presenter;
import com.erik.practice01.readWrite.Write;
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
        label:
        while(true){
            presenter.DocSystem();
            Scanner reader = new Scanner(System.in);// Reading from System.in
            String command = reader.nextLine();
            switch (command) {
                case "a":
                    try {
                        docManager.removeUser();
                    } catch (NoPatientException e) {
                        presenter.printErrorMessage(e);
                    }
                    break label;
                case "clear":
                    try {
                        Write.clear();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break label;
                case "e":
                    break label;
            }
        }
    }
}
