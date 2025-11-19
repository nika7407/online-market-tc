package com.solvd.onlinemarkettc.service.impl;

import com.solvd.onlinemarkettc.domain.user.User;
import com.solvd.onlinemarkettc.persistence.mybatisimpl.UserRepositoryMybatisImpl;
import com.solvd.onlinemarkettc.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    //   private final UserRepositoryImpl userRepository;
    private final UserRepositoryMybatisImpl userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryMybatisImpl();
    }

    @Override
    public Long createUser(User user) {
        log.info("create user: {}", user.getName());
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public User getUserById(Long id) {
        log.debug("find user id: {}", id);
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public User getUserByName(String name) {
        log.debug("find user name: {}", name);
        return userRepository.findByName(name).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<User> getAllUsers() {
        log.debug("get all users");
        return userRepository.findAll();
    }

    @Override
    public Long updateUser(User user) {
        log.info("update user id: {}", user.getId());
        userRepository.update(user);
        return user.getId();
    }

    @Override
    public void deleteUser(Long id) {
        log.info("delete user id: {}", id);
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            log.info("delete failed id: {}", id);
        }
    }

    @Override
    public boolean userExists(Long id) {
        return userRepository.findById(id).isPresent();
    }

    @Override
    public boolean userExistsByName(String name) {
        return userRepository.findByName(name).isPresent();
    }

}