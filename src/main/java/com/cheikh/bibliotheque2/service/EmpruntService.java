package com.cheikh.bibliotheque2.service;

import com.cheikh.bibliotheque2.domain.Emprunt;
import com.cheikh.bibliotheque2.repository.EmpruntRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EmpruntService {
    private final EmpruntRepository empruntRepository;
    private final LivreService livreService;
    private final UtilisateurService utilisateurService;

    public Emprunt emprunterLivre(Long userId, Long livreId) {
        var user = utilisateurService.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        var livre = livreService.findOne(livreId).orElseThrow(() -> new RuntimeException("Livre not found"));

        if(!user.getEnRegle())
            throw new RuntimeException("L'utilisateur n'est pas en règle");

        if(livre.getNbreExemplaire() == 0) {
            throw new RuntimeException("Il n'y a plus d'exemplaire disponible pour ce livre");
        }

        livre.setNbreExemplaire(livre.getNbreExemplaire() - 1);
        var updatedLivre = livreService.save(livre);

        var emprunt = new Emprunt();
        emprunt.setLivre(updatedLivre);
        emprunt.setUtilisateur(user);
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setRendu(false);

        return empruntRepository.save(emprunt);
    }

    public Emprunt rendreLivre(Long userId, Long empruntId) {
        var user = utilisateurService.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        var emprunt = empruntRepository.findById(empruntId).orElseThrow(() -> new RuntimeException("Emprunt not found"));

        if((long)emprunt.getUtilisateur().getId() != user.getId())
            throw new RuntimeException("Cet emprunt ne vous appartient pas");

        if(emprunt.getRendu())
            throw new RuntimeException("Cet emprunt a déjà été rendu");

        var livre = emprunt.getLivre();
        livre.setNbreExemplaire(livre.getNbreExemplaire() + 1);
        livreService.save(livre);

        emprunt.setRendu(true);
        return empruntRepository.save(emprunt);
    }

    //find all emprunts
    public List<Emprunt> findAll(){
        return empruntRepository.findAll();
    }

    //find all rendus
    public List<Emprunt> findAllRendus(){
        return empruntRepository.findAllByRendu(true);
    }

    //find all non rendus
    public List<Emprunt> findAllNonRendus(){
        return empruntRepository.findAllByRendu(false);
    }

    //find one
    public Emprunt findOne(Long id){
        return empruntRepository.findById(id).orElseThrow(()->new RuntimeException("Emprunt not found"));
    }

    //find all by user
    public List<Emprunt> findAllByUser(Long userId){
        var user = utilisateurService.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return empruntRepository.findAllByUtilisateurId(user.getId());
    }
}
