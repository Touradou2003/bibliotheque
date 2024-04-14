package com.cheikh.bibliotheque2.rest;

import com.cheikh.bibliotheque2.domain.Utilisateur;
import com.cheikh.bibliotheque2.rest.vm.LoginVM;
import com.cheikh.bibliotheque2.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@AllArgsConstructor
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    @PostMapping("")
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {

        if(utilisateur.getId() != null) {
            throw new IllegalArgumentException("Utilisateur id must be null");
        }

        var createdUser = utilisateurService.createUtilisateur(utilisateur);
        return ResponseEntity.ok().body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Utilisateur> login(@RequestBody LoginVM loginVM) {
        var user = utilisateurService.login(loginVM.getUsername(), loginVM.getPassword());
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/regulariser/{id}")
    public ResponseEntity<Utilisateur> regulariser(@PathVariable Long id) {
        var user = utilisateurService.regulariser(id);
        return ResponseEntity.ok().body(user);
    }

    //scheduled request to regularize users
    @Scheduled(cron = "0 0 1 * *")
    @GetMapping("/deregulariser")
    public ResponseEntity<Void> deregulariserAll() {
        utilisateurService.deregulariserAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Utilisateur>> findAll() {
        var users = utilisateurService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> findById(@PathVariable Long id) {
        var user = utilisateurService.findById(id).orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        return ResponseEntity.ok().body(user);
    }
}
