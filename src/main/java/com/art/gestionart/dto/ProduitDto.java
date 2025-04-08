package com.art.gestionart.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class ProduitDto {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProduitRequest {
        @NotBlank(message = "Le nom du produit est obligatoire")
        @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
        private String nom;

        @Size(max = 1000, message = "La description ne peut pas dépasser 1000 caractères")
        private String description;

        @NotNull(message = "Le prix est obligatoire")
        @Min(value = 0, message = "Le prix doit être positif")
        private BigDecimal prix;

        @NotNull(message = "La quantité en stock est obligatoire")
        @Min(value = 0, message = "La quantité en stock doit être positive")
        private Integer quantiteStock;

        private String reference;

        private String categorie;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProduitResponse {
        private Long id;
        private String nom;
        private String description;
        private BigDecimal prix;
        private Integer quantiteStock;
        private String reference;
        private String categorie;
    }
}
