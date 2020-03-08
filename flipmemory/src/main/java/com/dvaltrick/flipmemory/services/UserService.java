package com.dvaltrick.flipmemory.services;

import java.util.List;

import com.dvaltrick.flipmemory.models.UserEntity;
import com.dvaltrick.flipmemory.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

