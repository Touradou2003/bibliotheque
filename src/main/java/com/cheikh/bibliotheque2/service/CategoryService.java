package com.cheikh.bibliotheque2.service;

import com.cheikh.bibliotheque2.domain.Category;
import com.cheikh.bibliotheque2.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> findAll() {
       return categoryRepository.findAll();
    }

    public Optional<Category> findOne(Long id) {
        return categoryRepository.findById(id);
    }
}
