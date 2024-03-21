package com.andrade.crud.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        @NotBlank
        String name,
        @NotNull
        Integer price_in_cents
) {
        public ProductDTO (Product product) {
            this( product.getName(), product.getPrice_in_cents());
        }
}
