package com.crio.xlido.services;

import com.crio.xlido.entities.User;
import com.crio.xlido.repositories.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email, String password) {
        User user = new User(email, password);
        return userRepository.save(user);
    }

    public User getUser(long id) {
        return userRepository.findById(id);
    }
}
