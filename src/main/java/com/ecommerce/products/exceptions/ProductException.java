package com.ecommerce.products.exceptions;

public class ProductException extends Exception{

    private final String msg;

    public ProductException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
