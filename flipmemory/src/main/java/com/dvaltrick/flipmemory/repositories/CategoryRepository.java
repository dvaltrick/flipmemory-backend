package com.dvaltrick.flipmemory.repositories;

import com.dvaltrick.flipmemory.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CategoryRepository
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    
}