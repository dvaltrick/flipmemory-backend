package com.dvaltrick.flipmemory.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * User
 */
@Entity
public class UserEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String username;

    private String password;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL,
                fetch = FetchType.LAZY, optional = false)
    private UserProfile userProfile;

    @OneToMany(
        mappedBy = "userEntity",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Category> categories;

    @OneToMany(
        mappedBy = "userEntity",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Card> cards;

    @OneToMany(
        mappedBy = "userEntity",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<CardResponse> responses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<CardResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<CardResponse> responses) {
        this.responses = responses;
    }

    
    
}