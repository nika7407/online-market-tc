package com.solvd.onlinemarkettc.persistence;

import com.solvd.onlinemarkettc.connection.Connection;
import com.solvd.onlinemarkettc.connection.Pool;
import com.solvd.onlinemarkettc.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class UserRepository implements Repository<User> {

    private static final Logger log = LogManager.getLogger(UserRepository.class);
    private static final Pool connectionPool = Pool.getInstance(4);

    @Override
    public Optional<User> findById(Long id) {
        String sqlSelect = "SELECT * FROM users WHERE id=?";
        Optional<User> userOptional = Optional.empty();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToUser(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return userOptional;
    }

    public Optional<User> findByName(String name) {
        String sqlSelect = "SELECT * FROM users WHERE name=?";
        Optional<User> userOptional = Optional.empty();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToUser(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return userOptional;
    }

    @Override
    public List<User> findAll() {
        String sqlSelectAll = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlSelectAll)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    userList.add(mapResultSetToUser(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return userList;
    }


    @Override
    public User save(User user) {
        String sqlSave = "INSERT INTO users(name, card_number, basket_id) VALUES (?, ?, ?) RETURNING id";

        Connection connection = null;
        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlSave)) {
                statement.setString(1, user.getName());
                statement.setLong(2, user.getDebitCard().getCardNumber());
                statement.setLong(3, user.getBasket().getId());

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    user.setId(resultSet.getLong("id"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public void deleteById(Long id) {
        String sqlDelete = "DELETE FROM users WHERE id=?";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlDelete)) {
                statement.setLong(1, id);
                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    log.info("deleted user id {}", id);
                } else {
                    log.warn("not found user with id {}", id);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public User update(User user) {
        String sqlUpdate = "UPDATE users SET name = ?, card_number = ?, basket_id = ? WHERE id = ?";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlUpdate)) {
                statement.setString(1, user.getName());
                statement.setLong(2, user.getDebitCard().getCardNumber());
                statement.setLong(3, user.getBasket().getId());
                statement.setLong(4, user.getId());

                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    log.info("updated user id {}", user.getId());
                } else {
                    log.warn("not found user with  id {}", user.getId());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        return user;
    }
}