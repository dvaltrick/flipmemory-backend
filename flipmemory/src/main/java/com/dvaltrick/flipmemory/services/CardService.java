package com.dvaltrick.flipmemory.services;

import com.dvaltrick.flipmemory.repositories.CardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CardService
 */
@Service
public class CardService {
    @Autowired
    private CardRepository repository;
    
}