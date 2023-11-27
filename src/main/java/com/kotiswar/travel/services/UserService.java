package com.kotiswar.travel.services;

import com.kotiswar.travel.entitiy.User;
import com.kotiswar.travel.exceptions.ResourceNotFoundException;
import com.kotiswar.travel.payloads.UserDto;
import com.kotiswar.travel.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

interface UserServiceInterface {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto getUserByUsername(String username);

    UserDto createUser(UserDto user);

    UserDto updateUser(Long id, UserDto userDetails);

    void deleteUser(Long id);
}

@Service
public class UserService implements UserServiceInterface {

    private final UserRepo userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepo userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDto> getAllUsers() {
        return this.modelMapper.map(userRepository.findAll(), List.class);
    }

    public UserDto getUserById(Long id) {
        return this.modelMapper.map(userRepository.findById(id), UserDto.class);
    }

    public UserDto getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(value -> this.modelMapper.map(value, UserDto.class)).orElse(null);
    }

    public UserDto createUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        User addedUser = userRepository.save(user);
        return this.modelMapper.map(addedUser, UserDto.class);
    }

    public UserDto updateUser(Long id, UserDto userDetails) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setUsername(userDetails.getUsername());

        User updatedUser = this.userRepository.save(user);
        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    public void deleteUser(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        this.userRepository.delete(user);
    }

    // Additional business logic and validations can be added as needed
}
