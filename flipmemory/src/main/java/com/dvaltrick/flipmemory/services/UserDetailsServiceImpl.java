package com.dvaltrick.flipmemory.services;

import java.util.ArrayList;

import com.dvaltrick.flipmemory.models.UserEntity;
import com.dvaltrick.flipmemory.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsServiceImpl
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository applicationUserRepository;
    
    public UserDetailsServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity applicationUser = applicationUserRepository.findByUserName(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), new ArrayList<>());
    }
}