package com.dvaltrick.flipmemory.services;

import java.util.List;
import java.util.Random;

import com.dvaltrick.flipmemory.models.Card;
import com.dvaltrick.flipmemory.models.UserEntity;
import com.dvaltrick.flipmemory.repositories.CardRepository;
import com.dvaltrick.flipmemory.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * CardService
 */
@Service
public class CardService {
    @Autowired
    private CardRepository repository;

    @Autowired
    private UserRepository userRepository;

    public Card save(Card toSave) throws Exception {
        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

            UserEntity user = userRepository.findByUserName(username);
            toSave.setUserEntity(user);

            return repository.save(toSave);
        } catch (Exception e) {
            throw new Exception("#Card Fail on save: " + e.getMessage());
        }
    }

    public List<Card> findAll() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        
        return repository.findAllByUser(username);
    }

    public List<Card> findAllByCategory(Long categoryId) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        
        return repository.findAllByCategory(username, categoryId);
    }

    public Card findRandom(Long category) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        
        List<Card> userCards;
        if (category > 0) {
            userCards = repository.findAllByCategory(username, category);
        } else {
            userCards = repository.findAllByUser(username);
        }

        Random random = new Random();

        if (userCards.size() == 0) {
            return null;
        }

        int selectCard = random.nextInt(userCards.size());
        
        return userCards.get(selectCard);

    }
    
}