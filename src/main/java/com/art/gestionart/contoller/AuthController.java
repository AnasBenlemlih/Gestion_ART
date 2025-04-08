package com.art.gestionart.contoller;

import com.art.gestionart.dto.AuthDto;
import com.art.gestionart.model.Artisan;
import com.art.gestionart.repository.ArtisanRepository;
import com.art.gestionart.security.JwtTokenProvider;
import com.art.gestionart.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final ArtisanRepository artisanRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthDto.LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(AuthDto.JwtResponse.builder()
                .token(jwt)
                .id(userDetails.getId())
                .email(userDetails.getEmail())
                .nom(artisanRepository.findById(userDetails.getId()).get().getNom())
                .prenom(artisanRepository.findById(userDetails.getId()).get().getPrenom())
                .build());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AuthDto.SignupRequest signupRequest) {
        if (artisanRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthDto.MessageResponse("Erreur: Cet email est déjà utilisé!"));
        }

        if (artisanRepository.existsBySiret(signupRequest.getSiret())) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthDto.MessageResponse("Erreur: Ce SIRET est déjà enregistré!"));
        }

        // Création du compte artisan
        Artisan artisan = Artisan.builder()
                .nom(signupRequest.getNom())
                .prenom(signupRequest.getPrenom())
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .telephone(signupRequest.getTelephone())
                .adresse(signupRequest.getAdresse())
                .siret(signupRequest.getSiret())
                .metier(signupRequest.getMetier())
                .statut(Artisan.StatutCompte.ACTIF) // Par défaut, le compte est actif à la création
                .build();

        artisanRepository.save(artisan);

        return ResponseEntity.ok(new AuthDto.MessageResponse("Artisan enregistré avec succès!"));
    }
}
