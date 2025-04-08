package com.art.gestionart.repository;

import com.art.gestionart.model.Artisan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtisanRepository extends JpaRepository<Artisan, Long> {
    Optional<Artisan> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsBySiret(String siret);
}
