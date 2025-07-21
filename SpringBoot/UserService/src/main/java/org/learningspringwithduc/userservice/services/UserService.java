package org.learningspringwithduc.userservice.services;

import org.learningspringwithduc.userservice.entities.User;
import org.learningspringwithduc.userservice.repositories.UserRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepositories userRepositories;

    public UserService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    public List<User> getAllUsers() {
        return userRepositories.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepositories.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
         return userRepositories.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepositories.save(user);
    }

    public void deleteUser(Long id) {
        userRepositories.deleteById(id);
    }

//    public User updateUser(Long id, User newInfo) {
//        return userRepositories.findById(id).map(
//                existingUser -> {
//                    existingUser.setUsername(newInfo.getEmail());
//                    existingUser.setPassword(newInfo.getEmail());
//                    return userRepositories.save(existingUser);
//                }).orElseThrow(() -> new RuntimeException("User not found!"));
//    }

}
