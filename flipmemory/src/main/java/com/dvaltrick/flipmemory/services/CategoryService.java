package com.dvaltrick.flipmemory.services;

import com.dvaltrick.flipmemory.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CategoryService
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    
}