package com.art.gestionart.dto;

import com.art.gestionart.model.Commande;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CommandeDto {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommandeRequest {
        @NotBlank(message = "Le nom du client est obligatoire")
        private String nomClient;

        @NotBlank(message = "L'email du client est obligatoire")
        @Email(message = "Format d'email invalide")
        private String emailClient;

        private String telephoneClient;

        @NotBlank(message = "L'adresse de livraison est obligatoire")
        private String adresseLivraison;

        private String commentaires;

        @NotEmpty(message = "La commande doit contenir au moins un produit")
        @Valid
        private List<LigneCommandeRequest> lignesCommande;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LigneCommandeRequest {
        private Long produitId;
        private Integer quantite;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommandeResponse {
        private Long id;
        private String numeroCommande;
        private Commande.StatutCommande statut;
        private BigDecimal montantTotal;
        private String nomClient;
        private String emailClient;
        private String telephoneClient;
        private String adresseLivraison;
        private String commentaires;
        private LocalDateTime dateCreation;
        private List<LigneCommandeResponse> lignesCommande;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LigneCommandeResponse {
        private Long id;
        private ProduitDto.ProduitResponse produit;
        private Integer quantite;
        private BigDecimal prixUnitaire;
        private BigDecimal sousTotal;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommandeUpdateRequest {
        private Commande.StatutCommande statut;
        private String commentaires;
    }
}
