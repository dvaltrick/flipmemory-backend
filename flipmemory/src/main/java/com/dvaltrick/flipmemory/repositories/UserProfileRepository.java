package com.dvaltrick.flipmemory.repositories;

import com.dvaltrick.flipmemory.models.UserProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserProfileRepository
 */
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    
}