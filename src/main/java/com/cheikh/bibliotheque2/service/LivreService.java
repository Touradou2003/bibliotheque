package com.cheikh.bibliotheque2.service;

import com.cheikh.bibliotheque2.domain.Livre;
import com.cheikh.bibliotheque2.repository.LivreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LivreService {
    private final LivreRepository livreRepository;

    public Livre save(Livre livre) {
        return livreRepository.save(livre);
    }

    public Livre update(Livre livre) {
        return livreRepository.save(livre);
    }

    public void delete(Long id) {
        livreRepository.deleteById(id);
    }

    public Optional<Livre> findOne(Long id) {
        return livreRepository.findById(id);
    }

    public Optional<Livre> findOneByIsbn(String isbn) {
        return livreRepository.findFirstByIsbn(isbn);
    }

    public List<Livre> findAll() {
        return livreRepository.findAll();
    }
}
