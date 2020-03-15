package com.dvaltrick.flipmemory.repositories;

import java.util.List;

import com.dvaltrick.flipmemory.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * CategoryRepository
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select A from Category A where A.userEntity.username = :user ")
    public List<Category> findAllByUser(@Param("user") String user);
    
}