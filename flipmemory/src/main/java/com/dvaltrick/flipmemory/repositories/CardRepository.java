package com.dvaltrick.flipmemory.repositories;

import com.dvaltrick.flipmemory.models.Card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CardRepository
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    
}