package com.solvd.onlinemarkettc.persistence.mybatisimpl;

import com.solvd.onlinemarkettc.domain.user.User;
import com.solvd.onlinemarkettc.persistence.MyBatisHandler;
import com.solvd.onlinemarkettc.persistence.UserRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class UserRepositoryMybatisImpl implements UserRepository {

    @Override
    public Optional<User> findById(Long id) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            UserRepository repository = session.getMapper(UserRepository.class);
            return repository.findById(id);
        }
    }

    @Override
    public Optional<User> findByName(String name) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            UserRepository repository = session.getMapper(UserRepository.class);
            return repository.findByName(name);
        }
    }

    @Override
    public List<User> findAll() {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            UserRepository repository = session.getMapper(UserRepository.class);
            return repository.findAll();
        }
    }

    @Override
    public Long save(User user) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            UserRepository repository = session.getMapper(UserRepository.class);
            return repository.save(user);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            UserRepository repository = session.getMapper(UserRepository.class);
            repository.deleteById(id);
        }
    }

    @Override
    public Long update(User user) {
        try (SqlSession session = MyBatisHandler.getSqlSessionFactory().openSession(true)) {
            UserRepository repository = session.getMapper(UserRepository.class);
            return repository.update(user);
        }
    }
}