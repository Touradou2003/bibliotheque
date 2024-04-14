package com.cheikh.bibliotheque2.rest;

import com.cheikh.bibliotheque2.domain.Emprunt;
import com.cheikh.bibliotheque2.service.EmpruntService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {
    private final EmpruntService empruntService;

    //emprunter un livre
    @PostMapping("/utilisateur/{userId}/livre/{livreId}")
    public ResponseEntity<Emprunt> emprunterLivre(@PathVariable Long userId, @PathVariable Long livreId){
        return ResponseEntity.ok(empruntService.emprunterLivre(userId, livreId));
    }

    //rendre livre
    @PostMapping("/utilisateur/{userId}/rendre/{empruntId}")
    public ResponseEntity<Emprunt> rendreLivre(@PathVariable Long userId, @PathVariable Long empruntId){
        return ResponseEntity.ok(empruntService.rendreLivre(userId, empruntId));
    }

    //find all emprunts
    @GetMapping("")
    public ResponseEntity<List<Emprunt>> findAll(){
        return ResponseEntity.ok(empruntService.findAll());
    }

    //find all rendus
    @GetMapping("/rendus")
    public ResponseEntity<List<Emprunt>> findAllRendus(){
        return ResponseEntity.ok(empruntService.findAllRendus());
    }

    //find all non rendus
    @GetMapping("/non-rendus")
    public ResponseEntity<List<Emprunt>> findAllNonRendus(){
        return ResponseEntity.ok(empruntService.findAllNonRendus());
    }

    //find all by user
    @GetMapping("/utilisateur/{userId}")
    public ResponseEntity<List<Emprunt>> findAllByUser(@PathVariable Long userId){
        return ResponseEntity.ok(empruntService.findAllByUser(userId));
    }

    //find one
    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> findOne(@PathVariable Long id){
        return ResponseEntity.ok(empruntService.findOne(id));
    }
}
