package com.dvaltrick.flipmemory.services;

import java.util.List;

import com.dvaltrick.flipmemory.models.Category;
import com.dvaltrick.flipmemory.models.UserEntity;
import com.dvaltrick.flipmemory.repositories.CategoryRepository;
import com.dvaltrick.flipmemory.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * CategoryService
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    
    @Autowired
    private UserRepository userRepository;

    public Category save(Category toSave) throws Exception {
        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

            UserEntity user = userRepository.findByUserName(username);
            toSave.setUserEntity(user);

            return repository.save(toSave);
        } catch (Exception e) {
            throw new Exception("#Category Fail on save: " + e.getMessage());
        }
    }

    public List<Category> findAll() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        
        return repository.findAllByUser(username);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}