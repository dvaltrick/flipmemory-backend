package com.dvaltrick.flipmemory.repositories;

import com.dvaltrick.flipmemory.models.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("select A from UserEntity A where A.username = :username")
    public UserEntity findByUserName(@Param("username") String userName);
    
    @Query("select A from UserEntity A where A.username = :username and A.password = :password")
    public UserEntity findByUserNameAndPassword(@Param("username") String userName,
        @Param("password") String password);
}