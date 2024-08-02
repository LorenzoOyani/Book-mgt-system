package com.example.crudapplication.book.Domain.EntityException;

public class NoAvailableStockException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "No available stock!";

    private static final String MESSAGE_AMOUNT = "no stock available for this amount: ";

    public  NoAvailableStockException(int amount){
        super(MESSAGE_AMOUNT.concat(String.valueOf(amount)));
    }

    public  NoAvailableStockException(){
        super(DEFAULT_MESSAGE);
    }
}
