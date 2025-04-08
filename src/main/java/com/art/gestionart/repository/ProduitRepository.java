package com.art.gestionart.repository;

import com.art.gestionart.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByArtisanId(Long artisanId);
    Page<Produit> findByArtisanId(Long artisanId, Pageable pageable);
    Page<Produit> findByArtisanIdAndCategorie(Long artisanId, String categorie, Pageable pageable);
    List<Produit> findByArtisanIdAndQuantiteStockLessThan(Long artisanId, Integer quantiteMinimale);
    Boolean existsByReferenceAndArtisanId(String reference, Long artisanId);
}
