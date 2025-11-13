package com.solvd.onlinemarkettc.service.implementation;

import com.solvd.onlinemarkettc.domain.user.User;
import com.solvd.onlinemarkettc.persistence.repository.UserRepository;
import com.solvd.onlinemarkettc.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepository();
    }

    public User createUser(User user) {
        log.info("create user: {}", user.getName());
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        log.debug("find user id: {}", id);
        return userRepository.findById(id);
    }

    public Optional<User> getUserByName(String name) {
        log.debug("find user name: {}", name);
        return userRepository.findByName(name);
    }

    public List<User> getAllUsers() {
        log.debug("get all users");
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        log.info("update user id: {}", user.getId());
        return userRepository.update(user);
    }

    public void deleteUser(Long id) {
        log.info("delete user id: {}", id);
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            log.info("delete failed id: {}", id);
        }
    }

    public boolean userExists(Long id) {
        return userRepository.findById(id).isPresent();
    }

    public boolean userExistsByName(String name) {
        return userRepository.findByName(name).isPresent();
    }
}