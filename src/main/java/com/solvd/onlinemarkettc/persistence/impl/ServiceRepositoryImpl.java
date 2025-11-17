package com.solvd.onlinemarkettc.persistence.impl;

import com.solvd.onlinemarkettc.domain.item.Service;
import com.solvd.onlinemarkettc.persistence.ServiceRepository;
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

public class ServiceRepositoryImpl implements ServiceRepository {

    private static final Logger log = LogManager.getLogger(ServiceRepositoryImpl.class);
    private static final Pool connectionPool = Pool.getInstance(4);

    @Override
    public Optional<Service> findById(Long id) {
        String sqlSelect = "SELECT * FROM services WHERE id=?";
        Optional<Service> serviceOptional = Optional.empty();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToService(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return serviceOptional;
    }


    public Optional<Service> findByName(String name) {
        String sqlSelect = "SELECT * FROM services WHERE name=?";
        Optional<Service> serviceOptional = Optional.empty();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToService(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return serviceOptional;
    }

    @Override
    public List<Service> findAll() {
        String sqlSelectAll = "SELECT * FROM services";
        List<Service> serviceList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlSelectAll)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    serviceList.add(mapResultSetToService(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return serviceList;
    }

    @Override
    public Long save(Service service) {
        String sqlSave = "INSERT INTO services(name, cost, description, service_provider) VALUES (?, ?, ?, ?)";

        if (service.getName() != null) {
            Optional<Service> existingService = findByName(service.getName());
            if (existingService.isPresent()) {
                log.info("id {} already exists", existingService.get().getId());
                return existingService.get().getId();
            }
        }

        Connection connection = null;
        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlSave, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, service.getName());
                statement.setDouble(2, service.getCost());
                statement.setString(3, service.getDescription());
                statement.setString(4, service.getServiceProvider());

                int affectedRows = statement.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            Long generatedId = generatedKeys.getLong(1);
                            service.setId(generatedId);
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
        return service.getId();
    }

    @Override
    public void deleteById(Long id) {
        String sqlDelete = "DELETE FROM services WHERE id=?";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlDelete)) {
                statement.setLong(1, id);
                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    log.info("deleted id {}", id);
                } else {
                    log.warn("none found id {}", id);
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
    public Long update(Service service) {
        String sqlUpdate = "UPDATE services SET name = ?, cost = ?, description = ?, service_provider = ? WHERE id = ?";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlUpdate)) {
                statement.setString(1, service.getName());
                statement.setDouble(2, service.getCost());
                statement.setString(3, service.getDescription());
                statement.setString(4, service.getServiceProvider());
                statement.setLong(5, service.getId());

                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    log.info("updated id {}", service.getId());
                } else {
                    log.warn("not found id {}", service.getId());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return service.getId();
    }

    private Service mapResultSetToService(ResultSet resultSet) throws SQLException {
        Service service = new Service();
        service.setId(resultSet.getLong("id"));
        service.setName(resultSet.getString("name"));
        service.setCost(resultSet.getDouble("cost"));
        service.setDescription(resultSet.getString("description"));
        service.setServiceProvider(resultSet.getString("service_provider"));
        return service;
    }
}