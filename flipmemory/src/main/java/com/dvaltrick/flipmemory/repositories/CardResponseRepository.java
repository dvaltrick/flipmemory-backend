package com.dvaltrick.flipmemory.repositories;

import java.util.List;

import com.dvaltrick.flipmemory.models.CardResponse;
import com.dvaltrick.flipmemory.models.StatsResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * CardResponseRepository
 */
@Repository
public interface CardResponseRepository extends JpaRepository<CardResponse, Long> {
     @Query("select A from CardResponse A " +
            " where A.userEntity.username = :user and A.card.id = :card")
    public List<CardResponse> findStats(@Param("user") String user, @Param("card") Long card);
    
}