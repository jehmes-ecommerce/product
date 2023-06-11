package com.ecommerce.products.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentDto (UUID paymentId, @NotNull String cardNumbers, LocalDateTime requestDateTime, @NotNull BigDecimal valuePaid) { }
