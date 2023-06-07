package com.ecommerce.products.exceptions;

public class NotFoundException extends Exception{

    private final String msg;

    public NotFoundException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
