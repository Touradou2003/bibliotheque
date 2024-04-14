package com.cheikh.bibliotheque2.repository;

import com.cheikh.bibliotheque2.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
