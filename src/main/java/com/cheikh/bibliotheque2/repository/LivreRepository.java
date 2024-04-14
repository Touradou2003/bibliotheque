package com.cheikh.bibliotheque2.repository;

import com.cheikh.bibliotheque2.domain.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    Optional<Livre> findFirstByIsbn(String isbn);
}
