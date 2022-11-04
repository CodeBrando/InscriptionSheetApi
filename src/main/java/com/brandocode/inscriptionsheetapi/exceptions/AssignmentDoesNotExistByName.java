package com.brandocode.inscriptionsheetapi.exceptions;

public class AssignmentDoesNotExistByName extends Exception{
    public AssignmentDoesNotExistByName(String message, Throwable error){
        super(message, error);
    }

    public AssignmentDoesNotExistByName(String message){
        super(message);
    }
}
