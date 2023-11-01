package com.chunjae.test03.exception;

public class DataIntefrityViolationException extends RuntimeException{
    public DataIntefrityViolationException(String message){
        super(message);
    }
}
