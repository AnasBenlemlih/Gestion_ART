package com.art.gestionart.repository;

import com.art.gestionart.model.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    Optional<Commande> findByNumeroCommande(String numeroCommande);
    Page<Commande> findByArtisanId(Long artisanId, Pageable pageable);
    Page<Commande> findByArtisanIdAndStatut(Long artisanId, Commande.StatutCommande statut, Pageable pageable);
    List<Commande> findByArtisanIdAndDateCreationBetween(Long artisanId, LocalDateTime debut, LocalDateTime fin);
    List<Commande> findByArtisanIdAndStatutAndDateCreationBetween(Long artisanId, Commande.StatutCommande statut, LocalDateTime debut, LocalDateTime fin);
}
