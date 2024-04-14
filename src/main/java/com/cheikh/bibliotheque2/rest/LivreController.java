package com.cheikh.bibliotheque2.rest;

import com.cheikh.bibliotheque2.domain.Livre;
import com.cheikh.bibliotheque2.service.LivreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
@AllArgsConstructor
public class LivreController {
    private final LivreService livreService;

    //create a new book
    @PostMapping("")
    public ResponseEntity<Livre> createLivre(@RequestBody Livre livre){
        if (livre.getId() != null) {
            throw new IllegalArgumentException("Livre id must be null");
        }
        return ResponseEntity.ok(livreService.save(livre));
    }

    //update a book
    @PutMapping("")
    public ResponseEntity<Livre> updateLivre(@RequestBody Livre livre){
        if (livre.getId() == null) {
            throw new IllegalArgumentException("Livre id must not be null");
        }
        return ResponseEntity.ok(livreService.update(livre));
    }

    //delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Long id){
        livreService.delete(id);
        return ResponseEntity.ok().build();
    }

    //find one
    @GetMapping("/{id}")
    public ResponseEntity<Livre> findOne(@PathVariable Long id){
        var livre = livreService.findOne(id).orElseThrow(()->new RuntimeException("Livre not found"));
        return ResponseEntity.ok(livre);
    }

    //find one by isbn
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Livre> findOneByIsbn(@PathVariable String isbn){
        var livre = livreService.findOneByIsbn(isbn).orElseThrow(()->new RuntimeException("Livre not found"));
        return ResponseEntity.ok(livre);
    }

    //find all
    @GetMapping("")
    public ResponseEntity<List<Livre>> findAll(){
        return ResponseEntity.ok(livreService.findAll());
    }
}
