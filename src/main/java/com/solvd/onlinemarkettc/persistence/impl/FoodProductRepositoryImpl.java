package com.solvd.onlinemarkettc.persistence.impl;

import com.solvd.onlinemarkettc.domain.item.FoodProduct;
import com.solvd.onlinemarkettc.persistence.FoodProductRepository;
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

public class FoodProductRepositoryImpl implements FoodProductRepository {

    private static final Logger log = LogManager.getLogger(FoodProductRepositoryImpl.class);
    private static final Pool connectionPool = Pool.getInstance(4);


    @Override
    public Optional<FoodProduct> findById(Long id) {
        String sqlSelect = "SELECT * FROM food_products WHERE id=?";
        Optional<FoodProduct> foodProductOptional = Optional.empty();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToFoodProduct(resultSet));
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return foodProductOptional;
    }

    @Override
    public Optional<FoodProduct> findByName(String name) {
        String sqlSelect = "SELECT * FROM food_products WHERE name=?";
        Optional<FoodProduct> foodProductOptional = Optional.empty();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToFoodProduct(resultSet));
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return foodProductOptional;
    }

    @Override
    public List<FoodProduct> findAll() {
        String sqlSelectAll = "SELECT * FROM food_products";
        List<FoodProduct> foodProductList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlSelectAll)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    foodProductList.add(mapResultSetToFoodProduct(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return foodProductList;
    }

    @Override
    public Long save(FoodProduct foodProduct) {
        String sqlSaveFood = "INSERT INTO food_products(name, cost, expiration_date) VALUES (?, ?, ?)";

        if (foodProduct.getName() != null) {
            Optional<FoodProduct> existingProduct = findByName(foodProduct.getName());
            if (existingProduct.isPresent()) {
                log.info("product id {} already exists", existingProduct.get().getId());
                return existingProduct.get().getId();
            }
        }

        Connection connection = null;
        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlSaveFood, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, foodProduct.getName());
                statement.setDouble(2, foodProduct.getCost());
                statement.setDate(3, new java.sql.Date(foodProduct.getExpirationDate().getTime()));

                int affectedRows = statement.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            Long generatedId = generatedKeys.getLong(1);
                            foodProduct.setId(generatedId);
                            log.info("inserted food id {}", generatedId);
                        }
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
        return foodProduct.getId();
    }

    @Override
    public void deleteById(Long id) {
        String sqlDelete = "DELETE FROM food_products WHERE id=?";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlDelete)) {
                statement.setLong(1, id);
                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    log.info("deleted food product with id {}", id);
                } else {
                    log.warn("no food product found with id {}", id);
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
    public Long update(FoodProduct foodProduct) {
        String sqlUpdate = "UPDATE food_products SET name = ?, cost = ?, expiration_date = ? WHERE id = ?";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlUpdate)) {
                statement.setString(1, foodProduct.getName());
                statement.setDouble(2, foodProduct.getCost());
                statement.setDate(3, new java.sql.Date(foodProduct.getExpirationDate().getTime()));
                statement.setLong(4, foodProduct.getId());

                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    log.info("updated id {}", foodProduct.getId());
                } else {
                    log.warn("not found id {}", foodProduct.getId());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return foodProduct.getId();
    }

    private FoodProduct mapResultSetToFoodProduct(ResultSet resultSet) throws SQLException {
        FoodProduct foodProduct = new FoodProduct();

        foodProduct.setId(resultSet.getLong("id"));
        foodProduct.setName(resultSet.getString("name"));
        foodProduct.setCost(resultSet.getDouble("cost"));
        foodProduct.setExpirationDate(resultSet.getTimestamp("expiration_date"));
        return foodProduct;
    }
}
