package com.dvaltrick.flipmemory.repositories;

import java.util.List;

import com.dvaltrick.flipmemory.models.Card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * CardRepository
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("select A from Card A where A.userEntity.username = :user ")
    public List<Card> findAllByUser(@Param("user") String user);

    @Query("select A from Card A join fetch A.categories B where A.userEntity.username = :user and B.id = :category")
    public List<Card> findAllByCategory(@Param("user") String user, @Param("category") Long category);
    
}