package com.erik.practice01.user.exceptions;

/**
 * Throws when there is no patient.
 */
public class NoPatientException extends Exception {
    public NoPatientException(String errorMessage) {
        super(errorMessage);
    }
}

