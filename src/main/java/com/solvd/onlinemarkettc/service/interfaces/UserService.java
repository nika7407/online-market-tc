package com.solvd.onlinemarkettc.service.interfaces;

import com.solvd.onlinemarkettc.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByName(String name);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(Long id);
    boolean userExists(Long id);
    boolean userExistsByName(String name);
}