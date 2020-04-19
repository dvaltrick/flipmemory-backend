package com.dvaltrick.flipmemory.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dvaltrick.flipmemory.models.CardResponse;
import com.dvaltrick.flipmemory.enums.ResultAnswer;
import com.dvaltrick.flipmemory.models.Card;
import com.dvaltrick.flipmemory.models.Category;
import com.dvaltrick.flipmemory.models.StatsResponse;
import com.dvaltrick.flipmemory.models.UserEntity;
import com.dvaltrick.flipmemory.repositories.CardResponseRepository;
import com.dvaltrick.flipmemory.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * CardResponseService
 */
@Service
public class CardResponseService {
    @Autowired
    private CardResponseRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CardService cardService;
    
    public CardResponse save(CardResponse toSave) throws Exception {
        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

            UserEntity user = userRepository.findByUserName(username);
            toSave.setUserEntity(user);

            toSave.setDate(LocalDateTime.now());

            CardResponse response = repository.save(toSave);

            return response;
        } catch (Exception e) {
            throw new Exception("#CardResponse Failed on save: " + e.getMessage());
        }
    }

    public List<StatsResponse> findStats() throws Exception {
        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

            List<StatsResponse> response = new ArrayList<StatsResponse>();
            List<Category> categories = categoryService.findAll();

            categories.forEach(cat -> {
                List<Card> cards = cardService.findAllByCategory(cat.getId());
                cards.forEach(card -> {
                    List<CardResponse> responses = repository.findStats(username, card.getId());
                    Long cardTotal =  Integer.toUnsignedLong(responses.size());
                    Long cardCorrects = responses.stream().filter(resp -> {return resp.getAnswer() == ResultAnswer.CORRECT; }).count();
                    Long cardIncompletes = responses.stream().filter(resp -> {return resp.getAnswer() == ResultAnswer.INCOMPLETE;}).count();
                    Long cardWrongs = responses.stream().filter(resp -> {return resp.getAnswer() == ResultAnswer.FAIL; }).count();

                    StatsResponse stats = new StatsResponse(card, cardTotal, cardCorrects, cardIncompletes, cardWrongs);
                    response.add(stats);
                });
            });
            
            return response;
        } catch (Exception e) {
            throw new Exception("#StatsResponse Fail on save: " + e.getMessage());
        }

    } 
}