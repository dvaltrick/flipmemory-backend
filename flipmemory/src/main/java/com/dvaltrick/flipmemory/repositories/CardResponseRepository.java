package com.dvaltrick.flipmemory.repositories;

import com.dvaltrick.flipmemory.models.CardResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CardResponseRepository
 */
@Repository
public interface CardResponseRepository extends JpaRepository<CardResponse, Long> {

    
}