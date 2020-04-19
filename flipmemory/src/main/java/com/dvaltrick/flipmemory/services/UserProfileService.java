package com.dvaltrick.flipmemory.services;

import com.dvaltrick.flipmemory.models.UserProfile;
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
    
    public UserProfile save(UserProfile toSave) throws Exception {
        try {    
            return repository.save(toSave);
        } catch (Exception e) {
            throw new Exception("#UserEntity Fail on save: " + e.getMessage());
        }
    }
}