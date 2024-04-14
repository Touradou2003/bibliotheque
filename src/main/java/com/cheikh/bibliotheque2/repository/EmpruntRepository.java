package com.cheikh.bibliotheque2.repository;

import com.cheikh.bibliotheque2.domain.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    List<Emprunt> findAllByRendu(boolean b);

    List<Emprunt> findAllByUtilisateurId(Long id);
}
