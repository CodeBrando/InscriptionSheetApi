package com.brandocode.inscriptionsheetapi.enums;

public class CareerDoesNotExistByName extends Exception{
    public CareerDoesNotExistByName(String message, Throwable error){
        super(message, error);

    }

    public CareerDoesNotExistByName(String message){
        super(message);
    }
}
