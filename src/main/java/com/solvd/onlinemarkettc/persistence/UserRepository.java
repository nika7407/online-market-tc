package com.solvd.onlinemarkettc.persistence;

import com.solvd.onlinemarkettc.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByName(String name);

    List<User> findAll();

    Long save(User user);

    void deleteById(Long id);

    Long update(User user);

}