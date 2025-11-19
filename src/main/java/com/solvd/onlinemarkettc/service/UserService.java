package com.solvd.onlinemarkettc.service;

import com.solvd.onlinemarkettc.domain.user.User;

import java.util.List;

public interface UserService {

    Long createUser(User user);

    User getUserById(Long id);

    User getUserByName(String name);

    List<User> getAllUsers();

    Long updateUser(User user);

    void deleteUser(Long id);

    boolean userExists(Long id);

    boolean userExistsByName(String name);
}