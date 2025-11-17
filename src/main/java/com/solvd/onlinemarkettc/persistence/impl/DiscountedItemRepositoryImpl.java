package com.solvd.onlinemarkettc.persistence.impl;

import com.solvd.onlinemarkettc.domain.item.DiscountedItem;
import com.solvd.onlinemarkettc.persistence.DiscountedItemRepository;
import com.solvd.onlinemarkettc.persistence.connection.Connection;
import com.solvd.onlinemarkettc.persistence.connection.Pool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class DiscountedItemRepositoryImpl implements DiscountedItemRepository {

    private static final Logger log = LogManager.getLogger(DiscountedItemRepositoryImpl.class);
    private static final Pool connectionPool = Pool.getInstance(4);

    @Override
    public Optional<DiscountedItem> findById(Long id) {
        String sqlSelect = "SELECT * FROM discounted_items WHERE id=?";
        Optional<DiscountedItem> discountedItemOptional = Optional.empty();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToDiscountedItem(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return discountedItemOptional;
    }

    public Optional<DiscountedItem> findByName(String name) {
        String sqlSelect = "SELECT * FROM discounted_items WHERE name=?";
        Optional<DiscountedItem> discountedItemOptional = Optional.empty();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToDiscountedItem(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return discountedItemOptional;
    }

    @Override
    public List<DiscountedItem> findAll() {
        String sqlSelectAll = "SELECT * FROM discounted_items";
        List<DiscountedItem> discountedItemList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlSelectAll)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    discountedItemList.add(mapResultSetToDiscountedItem(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return discountedItemList;
    }

    @Override
    public Long save(DiscountedItem discountedItem) {
        String sqlSave = "INSERT INTO discounted_items(name, cost, description) VALUES (?, ?, ?)";

        if (discountedItem.getName() != null) {
            Optional<DiscountedItem> existingItem = findByName(discountedItem.getName());
            if (existingItem.isPresent()) {
                log.info("{} id already exists", existingItem.get().getId());
                return existingItem.get().getId();
            }
        }

        Connection connection = null;
        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlSave, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, discountedItem.getName());
                statement.setDouble(2, discountedItem.getCost());
                statement.setString(3, discountedItem.getDescription());

                int affectedRows = statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Long generatedId = generatedKeys.getLong(1);
                        discountedItem.setId(generatedId);
                        log.info("inserted id {}", generatedId);
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return discountedItem.getId();
    }

    @Override
    public void deleteById(Long id) {
        String sqlDelete = "DELETE FROM discounted_items WHERE id=?";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlDelete)) {
                statement.setLong(1, id);
                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    log.info("deleted  id {}", id);
                } else {
                    log.warn("no  found  id {}", id);
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
    public Long update(DiscountedItem discountedItem) {
        String sqlUpdate = "UPDATE discounted_items SET name = ?, cost = ?, description = ? WHERE id = ?";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlUpdate)) {
                statement.setString(1, discountedItem.getName());
                statement.setDouble(2, discountedItem.getCost());
                statement.setString(3, discountedItem.getDescription());
                statement.setLong(4, discountedItem.getId());

                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    log.info("updated  id {}", discountedItem.getId());
                } else {
                    log.warn("not present id {}", discountedItem.getId());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return discountedItem.getId();
    }

    private DiscountedItem mapResultSetToDiscountedItem(ResultSet resultSet) throws SQLException {
        DiscountedItem discountedItem = new DiscountedItem();
        discountedItem.setId(resultSet.getLong("id"));
        discountedItem.setName(resultSet.getString("name"));
        discountedItem.setCost(resultSet.getDouble("cost"));
        discountedItem.setDescription(resultSet.getString("description"));
        return discountedItem;
    }
}