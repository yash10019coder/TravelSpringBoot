package com.kotiswar.travel.services;

import com.kotiswar.travel.entitiy.User;
import com.kotiswar.travel.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepository;

    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(User user) {
        // Add any business logic or validation here before saving
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setPasswordHash(userDetails.getPasswordHash()); // Consider encrypting this
                    user.setEmail(userDetails.getEmail());
                    user.setRole(userDetails.getRole());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    userDetails.setId(id);
                    return userRepository.save(userDetails);
                });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Additional business logic and validations can be added as needed
}
