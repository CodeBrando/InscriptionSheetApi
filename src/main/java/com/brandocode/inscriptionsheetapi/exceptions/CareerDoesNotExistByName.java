package com.brandocode.inscriptionsheetapi.exceptions;

public class CareerDoesNotExistByName extends Exception{
    public CareerDoesNotExistByName(String message, Throwable error){
        super(message, error);

    }

    public CareerDoesNotExistByName(String message){
        super(message);
    }
}
