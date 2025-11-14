package com.solvd.onlinemarkettc.persistence;

import com.solvd.onlinemarkettc.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByName(String name);

    List<User> findAll();

    User save(User user);

    void deleteById(Long id);

    User update(User user);

}