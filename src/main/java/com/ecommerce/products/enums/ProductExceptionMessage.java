package com.ecommerce.products.enums;

public enum ProductExceptionMessage {
    NOT_FOUND("Product not found"),
    OUT_OF_BOUNDS("Product out of bounds");

    private final String message;

    ProductExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
