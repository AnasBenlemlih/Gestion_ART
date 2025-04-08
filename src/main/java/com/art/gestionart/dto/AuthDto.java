package com.art.gestionart.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AuthDto {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRequest {
        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "Format d'email invalide")
        private String email;

        @NotBlank(message = "Le mot de passe est obligatoire")
        private String password;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignupRequest {
        @NotBlank(message = "Le nom est obligatoire")
        @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
        private String nom;

        @NotBlank(message = "Le prénom est obligatoire")
        @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères")
        private String prenom;

        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "Format d'email invalide")
        private String email;

        @NotBlank(message = "Le mot de passe est obligatoire")
        @Size(min = 6, max = 40, message = "Le mot de passe doit contenir entre 6 et 40 caractères")
        private String password;

        private String telephone;

        private String adresse;

        @NotBlank(message = "Le SIRET est obligatoire")
        @Size(min = 14, max = 14, message = "Le SIRET doit contenir 14 caractères")
        private String siret;

        @NotBlank(message = "Le métier est obligatoire")
        private String metier;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JwtResponse {
        private String token;
        private String type = "Bearer";
        private Long id;
        private String email;
        private String nom;
        private String prenom;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MessageResponse {
        private String message;
    }
}
