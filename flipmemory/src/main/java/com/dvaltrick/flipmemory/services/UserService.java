package com.dvaltrick.flipmemory.services;

import java.util.List;

import com.dvaltrick.flipmemory.mail.EmailServiceImpl;
import com.dvaltrick.flipmemory.models.StatsResponse;
import com.dvaltrick.flipmemory.models.UserEntity;
import com.dvaltrick.flipmemory.models.UserProfile;
import com.dvaltrick.flipmemory.repositories.UserProfileRepository;
import com.dvaltrick.flipmemory.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserProfileRepository profileRepository;
    
    public UserEntity save(UserEntity toSave) throws Exception {
        try {
            if (toSave == null) throw new Exception("#UserEntity Fail on save: null object");

            if (toSave.getUsername().isEmpty()) throw new Exception("Informe o nome de usuário");
            if (toSave.getUsername().length() < 5) throw new Exception("Nome de usuário muito curto");
            if (toSave.getPassword().isEmpty()) throw new Exception("Informe a senha do usuário");
            if (toSave.getPassword().length() < 5) throw new Exception("Senha muito curta");

            toSave.setPassword(bCryptPasswordEncoder.encode(toSave.getPassword()));
            UserEntity saved = repository.findByUserName(toSave.getUsername());
            if (saved != null) {
                throw new Exception("Usuário já existe");
            }

            UserEntity toReturn = repository.save(toSave);
            UserProfile profile = toReturn.getUserProfile();
            profile.setUserEntity(toReturn);
            profileRepository.save(profile);

            return toReturn;
        } catch (Exception e) {
            throw new Exception("#UserEntity Fail on save: " + e.getMessage());
        }
    }

    public String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 

    public String recovery(String username) throws Exception {
        try {
            UserEntity toSave = repository.findByUserName(username);

            if (toSave == null) {
                throw new Exception("User not found");
            }

            String newPassword = this.getAlphaNumericString(8);
            toSave.setPassword(bCryptPasswordEncoder.encode(newPassword));

            repository.save(toSave);

            StringBuilder sb = new StringBuilder();
            sb.append("Segue abaixo a nova senha para acesso com o usuário: " + username);
            sb.append("Nova senha: " + newPassword);

            EmailServiceImpl email = new EmailServiceImpl();
            email.sendSimpleMessage(toSave.getUserProfile().getEmail(), "Password recovery", sb.toString());

        } catch (Exception e) {
            throw new Exception("#UserEntity Fail on save: " + e.getMessage());
        }
        
        return "OK!";
    }

    public UserEntity update(UserEntity toUpdate) throws Exception {
        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();

            UserEntity toSave = repository.findByUserName(username);

            if (toSave == null) throw new Exception("#UserEntity Fail on save: null object");

            if (toUpdate.getPassword().isEmpty()) throw new Exception("Informe a senha do usuário");
            if (toUpdate.getPassword().length() < 5) throw new Exception("Senha muito curta");

            toSave.setPassword(bCryptPasswordEncoder.encode(toUpdate.getPassword()));
            return repository.save(toSave);
        } catch (Exception e) {
            throw new Exception("#UserEntity Fail on save: " + e.getMessage());
        }
    }

    public UserEntity find(Long toFind) throws Exception {
        try {
            return repository.getOne(toFind);
        } catch (Exception e) {
            throw new Exception("#UserEntity Fail on find: " + e.getMessage());
        }
    }

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public UserEntity login(String userName, String password) throws Exception {
        try {
            return repository.findByUserNameAndPassword(userName, password);
        } catch (Exception e) {
            throw new Exception("#UserEntity Fail on find: " + e.getMessage());
        }
    }
}

