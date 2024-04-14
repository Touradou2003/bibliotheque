package com.cheikh.bibliotheque2.service;

import com.cheikh.bibliotheque2.domain.Utilisateur;
import com.cheikh.bibliotheque2.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    /**
     * Creates a new Utilisateur.
     *
     * @param utilisateur The Utilisateur object to create.
     * @return The newly created Utilisateur object.
     */
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        // verifier si utilisateur existe
        if (
                utilisateurRepository.findByUsername(utilisateur.getUsername()).isPresent()
                        ||
                utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()
        ) {
            throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà");
        }

        utilisateur.setRole("USER");
        utilisateur.setEnRegle(true);

        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur login(String username, String password) {
        var user = utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Mot de passe incorrect");
        }

        return user;
    }

    public Utilisateur regulariser(Long id) {
        var user = utilisateurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        user.setEnRegle(true);
        return utilisateurRepository.save(user);
    }

    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> findById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public void deregulariserAll() {
         utilisateurRepository
            .findAll()
            .forEach(user -> {
                user.setEnRegle(false);
                utilisateurRepository.save(user);
            });
    }
}
