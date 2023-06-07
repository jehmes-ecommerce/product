package com.ecommerce.products.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    public interface ProductView {
        public static interface RegistrationPost{}
        public static interface ProductUp{}
    }

    private String productId;
    @NotBlank(groups = {ProductView.RegistrationPost.class, ProductView.ProductUp.class})
    @JsonView({ProductView.RegistrationPost.class, ProductView.ProductUp.class})
    private String name;
    @NotNull(groups = {ProductView.RegistrationPost.class, ProductView.ProductUp.class})
    @JsonView({ProductView.RegistrationPost.class, ProductView.ProductUp.class})
    private BigDecimal price;
    @NotNull(groups = ProductView.RegistrationPost.class)
    @JsonView(ProductView.RegistrationPost.class)
    private Integer quantity;

}
