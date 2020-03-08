package com.dvaltrick.flipmemory.services;

import com.dvaltrick.flipmemory.repositories.CardResponseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CardResponseService
 */
@Service
public class CardResponseService {
    @Autowired
    private CardResponseRepository repository;
    
}