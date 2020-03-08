package com.dvaltrick.flipmemory.services;

import com.dvaltrick.flipmemory.repositories.UserProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserProfileService
 */
@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository repository;
    
}