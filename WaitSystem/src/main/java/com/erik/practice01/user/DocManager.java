package com.erik.practice01.user;

import com.erik.practice01.user.exceptions.NoPatientException;

import java.util.NoSuchElementException;
import java.util.Queue;

public class DocManager extends UserManager{
    private final Doctor doc = new Doctor("Doctor");

    public DocManager() {

    }

    public Doctor getDoc() {
        return this.doc;
    }

    public void removeUser() throws NoPatientException {
        try {
            UserManager.getWaitlist().remove();
        }
        catch (NoSuchElementException e){
            throw new NoPatientException("No Patient now");
        }
    }
}
