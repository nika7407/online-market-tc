package com.solvd.onlinemarkettc.persistence;

import com.solvd.onlinemarkettc.connection.Connection;
import com.solvd.onlinemarkettc.connection.Pool;
import com.solvd.onlinemarkettc.item.NonPerishebleProduct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class NonPerishableProductRepository implements Repository<NonPerishebleProduct> {

    private static final Logger log = LogManager.getLogger(NonPerishableProductRepository.class);
    private static final Pool connectionPool = Pool.getInstance(4);

    @Override
    public Optional<NonPerishebleProduct> findById(Long id) {
        String sqlSelect = "SELECT * FROM non_perishable_products WHERE id=?";
        Optional<NonPerishebleProduct> nonPerishableProductOptional = Optional.empty();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToNonPerishableProduct(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return nonPerishableProductOptional;
    }

    public Optional<NonPerishebleProduct> findByName(String name) {
        String sqlSelect = "SELECT * FROM non_perishable_products WHERE name=?";
        Optional<NonPerishebleProduct> nonPerishableProductOptional = Optional.empty();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToNonPerishableProduct(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return nonPerishableProductOptional;
    }

    @Override
    public List<NonPerishebleProduct> findAll() {
        String sqlSelectAll = "SELECT * FROM non_perishable_products";
        List<NonPerishebleProduct> nonPerishableProductList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlSelectAll)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    nonPerishableProductList.add(mapResultSetToNonPerishableProduct(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return nonPerishableProductList;
    }

    @Override
    public NonPerishebleProduct save(NonPerishebleProduct nonPerishableProduct) {
        String sqlSave = "INSERT INTO non_perishable_products(name, cost, description) VALUES (?, ?, ?)";

        if (nonPerishableProduct.getName() != null) {
            Optional<NonPerishebleProduct> existingProduct = findByName(nonPerishableProduct.getName());
            if (existingProduct.isPresent()) {
                log.info("id {} already exists", existingProduct.get().getId());
                return existingProduct.get();
            }
        }

        Connection connection = null;
        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlSave, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, nonPerishableProduct.getName());
                statement.setDouble(2, nonPerishableProduct.getCost());
                statement.setString(3, nonPerishableProduct.getDescription());

                int affectedRows = statement.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            Long generatedId = generatedKeys.getLong(1);
                            nonPerishableProduct.setId(generatedId);
                            log.info("inserted id {}", generatedId);
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
        return nonPerishableProduct;
    }

    @Override
    public void deleteById(Long id) {
        String sqlDelete = "DELETE FROM non_perishable_products WHERE id=?";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlDelete)) {
                statement.setLong(1, id);
                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    log.info("deleted  id {}", id);
                } else {
                    log.warn("not found id {}", id);
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
    public NonPerishebleProduct update(NonPerishebleProduct nonPerishableProduct) {
        String sqlUpdate = "UPDATE non_perishable_products SET name = ?, cost = ?, description = ? WHERE id = ?";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlUpdate)) {
                statement.setString(1, nonPerishableProduct.getName());
                statement.setDouble(2, nonPerishableProduct.getCost());
                statement.setString(3, nonPerishableProduct.getDescription());
                statement.setLong(4, nonPerishableProduct.getId());

                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    log.info("updated  id {}", nonPerishableProduct.getId());
                } else {
                    log.warn("not found id {}", nonPerishableProduct.getId());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return nonPerishableProduct;
    }

    private NonPerishebleProduct mapResultSetToNonPerishableProduct(ResultSet resultSet) throws SQLException {
        NonPerishebleProduct nonPerishableProduct = new NonPerishebleProduct();
        nonPerishableProduct.setId(resultSet.getLong("id"));
        nonPerishableProduct.setName(resultSet.getString("name"));
        nonPerishableProduct.setCost(resultSet.getDouble("cost"));
        nonPerishableProduct.setDescription(resultSet.getString("description"));
        return nonPerishableProduct;
    }
}