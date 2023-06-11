package com.ecommerce.products.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderItemsDto(@NotNull String productId, @NotNull Integer quantity){

}
