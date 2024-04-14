package com.cheikh.bibliotheque2.rest;

import com.cheikh.bibliotheque2.domain.Category;
import com.cheikh.bibliotheque2.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {

        if(category.getId() != null) {
            throw new IllegalArgumentException("Category id must be null");
        }

        var createdCategory = categoryService.createCategory(category);
        return ResponseEntity.ok().body(createdCategory);
    }

    @PutMapping("")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {

        if(category.getId() == null) {
            throw new IllegalArgumentException("Category id must not be null");
        }

        var updatedCategory = categoryService.updateCategory(category);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> findAll() {
        var categories = categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findOne(@PathVariable Long id) {
        var category = categoryService.findOne(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        return ResponseEntity.ok().body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

}
