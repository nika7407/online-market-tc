package com.solvd.onlinemarkettc.persistence.impl;

import com.solvd.onlinemarkettc.domain.item.DiscountedItem;
import com.solvd.onlinemarkettc.domain.item.FoodProduct;
import com.solvd.onlinemarkettc.domain.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.domain.item.Service;
import com.solvd.onlinemarkettc.persistence.BasketRepository;
import com.solvd.onlinemarkettc.persistence.connection.Connection;
import com.solvd.onlinemarkettc.persistence.connection.Pool;
import com.solvd.onlinemarkettc.domain.finantialoperation.Basket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class BasketRepositoryImpl implements BasketRepository {

    private static final Logger log = LogManager.getLogger(BasketRepositoryImpl.class);
    private static final Pool connectionPool = Pool.getInstance(4);

    @Override
    public Optional<Basket> findById(Long id) {
        String sqlSelect = "SELECT * FROM baskets WHERE id=?";
        Optional<Basket> basketOptional = Optional.empty();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    basketOptional = Optional.of(mapResultSetToBasket(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return basketOptional;
    }

    @Override
    public List<Basket> findAll() {
        String sqlSelectAll = "SELECT * FROM baskets";
        List<Basket> basketList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlSelectAll)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    basketList.add(mapResultSetToBasket(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return basketList;
    }

    @Override
    public Basket save(Basket basket) {
        String sqlSave = "INSERT INTO baskets(sum_cost, date, delivery_address_id) VALUES (?, ?, ?)";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlSave, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setDouble(1, basket.getSumCost());
                statement.setTimestamp(2, new Timestamp(basket.getDate().getTime()));
                statement.setObject(3, basket.getAddress().getId());

                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            Long generatedId = generatedKeys.getLong(1);
                            basket.setId(generatedId);
                            log.info("inserted basket id {}", generatedId);
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
        return basket;
    }

    @Override
    public void deleteById(Long id) {
        String sqlDelete = "DELETE FROM baskets WHERE id=?";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlDelete)) {
                statement.setLong(1, id);
                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    log.info("deleted basket id {}", id);
                } else {
                    log.warn("not found basket id {}", id);
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
    public Basket update(Basket basket) {
        String sqlUpdate = "UPDATE baskets SET sum_cost = ?, date = ?, delivery_address_id = ? WHERE id = ?";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlUpdate)) {
                statement.setDouble(1, basket.getSumCost());
                statement.setTimestamp(2, new Timestamp(basket.getDate().getTime()));
                statement.setObject(3, basket.getAddress().getId());
                statement.setLong(4, basket.getId());

                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    log.info("updated basket id {}", basket.getId());
                } else {
                    log.warn("not found basket id {}", basket.getId());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return basket;
    }

    public void addFoodProductToBasket(Long basketId, Long foodProductId) {
        String sqlInsert = "INSERT INTO basket_food_products(basket_id, food_product_id) VALUES (?, ?)";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlInsert)) {
                statement.setLong(1, basketId);
                statement.setLong(2, foodProductId);
                statement.executeUpdate();
                log.info("added food product {} to basket {}", foodProductId, basketId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void addNonPerishableProductToBasket(Long basketId, Long nonPerishableProductId) {
        String sqlInsert = "INSERT INTO basket_non_perishable_products(basket_id, non_perishable_product_id) VALUES (?, ?)";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlInsert)) {
                statement.setLong(1, basketId);
                statement.setLong(2, nonPerishableProductId);
                statement.executeUpdate();
                log.info("added non-perishable product {} to basket {}", nonPerishableProductId, basketId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void addDiscountedItemToBasket(Long basketId, Long discountedItemId) {
        String sqlInsert = "INSERT INTO basket_discounted_items(basket_id, discounted_item_id) VALUES (?, ?)";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlInsert)) {
                statement.setLong(1, basketId);
                statement.setLong(2, discountedItemId);
                statement.executeUpdate();
                log.info("added discounted item {} to basket {}", discountedItemId, basketId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void addServiceToBasket(Long basketId, Long serviceId) {
        String sqlInsert = "INSERT INTO basket_services(basket_id, service_id) VALUES (?, ?)";
        Connection connection = null;

        try {
            connection = connectionPool.getConnection(1, TimeUnit.SECONDS);
            try (PreparedStatement statement = connection.getSqlConnection().prepareStatement(sqlInsert)) {
                statement.setLong(1, basketId);
                statement.setLong(2, serviceId);
                statement.executeUpdate();
                log.info("added service {} to basket {}", serviceId, basketId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public List<Long> findFoodProductIdsByBasketId(Long basketId) {
        String sqlSelect = "SELECT food_product_id FROM basket_food_products WHERE basket_id=?";
        List<Long> foodProductIds = new ArrayList<>();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setLong(1, basketId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    foodProductIds.add(resultSet.getLong("food_product_id"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return foodProductIds;
    }

    public List<Long> findNonPerishableProductIdsByBasketId(Long basketId) {
        String sqlSelect = "SELECT non_perishable_product_id FROM basket_non_perishable_products WHERE basket_id=?";
        List<Long> productIds = new ArrayList<>();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setLong(1, basketId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    productIds.add(resultSet.getLong("non_perishable_product_id"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return productIds;
    }

    public List<Long> findDiscountedItemIdsByBasketId(Long basketId) {
        String sqlSelect = "SELECT discounted_item_id FROM basket_discounted_items WHERE basket_id=?";
        List<Long> itemIds = new ArrayList<>();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setLong(1, basketId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    itemIds.add(resultSet.getLong("discounted_item_id"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return itemIds;
    }

    public List<Long> findServiceIdsByBasketId(Long basketId) {
        String sqlSelect = "SELECT service_id FROM basket_services WHERE basket_id=?";
        List<Long> serviceIds = new ArrayList<>();
        Connection conn = null;
        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setLong(1, basketId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    serviceIds.add(resultSet.getLong("service_id"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return serviceIds;
    }

    @Override
    public Basket findBasketWithAllItems(Long basketId) {
        String sqlSelect = """
                SELECT 
                    basket.id as basket_id, basket.sum_cost as basket_cost, basket.date as basket_date,
                    food.id as food_id, food.name as food_name, food.cost as food_cost, food.expiration_date as food_expiration,
                    non_perishable.id as non_perishable_id, non_perishable.name as non_perishable_name, non_perishable.cost as non_perishable_cost, non_perishable.description as non_perishable_desc,
                    discounted.id as discounted_id, discounted.name as discounted_name, discounted.cost as discounted_cost, discounted.description as discounted_desc,
                    service.id as service_id, service.name as service_name, service.cost as service_cost, service.description as service_desc, service.service_provider as service_provider
                FROM baskets basket
                LEFT JOIN basket_food_products basket_food ON basket.id = basket_food.basket_id
                LEFT JOIN food_products food ON basket_food.food_product_id = food.id
                LEFT JOIN basket_non_perishable_products basket_non_perishable ON basket.id = basket_non_perishable.basket_id
                LEFT JOIN non_perishable_products non_perishable ON basket_non_perishable.non_perishable_product_id = non_perishable.id
                LEFT JOIN basket_discounted_items basket_discounted ON basket.id = basket_discounted.basket_id
                LEFT JOIN discounted_items discounted ON basket_discounted.discounted_item_id = discounted.id
                LEFT JOIN basket_services basket_service ON basket.id = basket_service.basket_id
                LEFT JOIN services service ON basket_service.service_id = service.id
                WHERE basket.id = ?
                """;

        Basket basket = null;
        Connection conn = null;

        try {
            conn = connectionPool.getConnection(10, TimeUnit.SECONDS);
            try (PreparedStatement statement = conn.getSqlConnection().prepareStatement(sqlSelect)) {
                statement.setLong(1, basketId); // Changed to setLong since basket.id is integer
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    if (basket == null) {
                        basket = new Basket();
                        basket.setId(basketId);
                        basket.setSumCost(resultSet.getDouble("basket_cost"));
                        basket.setDate(resultSet.getTimestamp("basket_date"));
                    }

                    Long foodId = resultSet.getLong("food_id");
                    if (!resultSet.wasNull() && foodId > 0) {
                        FoodProduct food = new FoodProduct();
                        food.setId(foodId);
                        food.setName(resultSet.getString("food_name"));
                        food.setCost(resultSet.getDouble("food_cost"));
                        food.setExpirationDate(resultSet.getTimestamp("food_expiration"));
                        if (!basket.getFoodProductList().contains(food)) {
                            basket.addFoodProduct(food);
                        }
                    }

                    Long nonPerishableId = resultSet.getLong("non_perishable_id");
                    if (!resultSet.wasNull() && nonPerishableId > 0) {
                        NonPerishebleProduct product = new NonPerishebleProduct();
                        product.setId(nonPerishableId);
                        product.setName(resultSet.getString("non_perishable_name"));
                        product.setCost(resultSet.getDouble("non_perishable_cost"));
                        product.setDescription(resultSet.getString("non_perishable_desc"));
                        if (!basket.getNonPerishebleProductList().contains(product)) {
                            basket.addProduct(product);
                        }
                    }

                    Long discountedId = resultSet.getLong("discounted_id");
                    if (!resultSet.wasNull() && discountedId > 0) {
                        DiscountedItem item = new DiscountedItem();
                        item.setId(discountedId);
                        item.setName(resultSet.getString("discounted_name"));
                        item.setCost(resultSet.getDouble("discounted_cost"));
                        item.setDescription(resultSet.getString("discounted_desc"));
                        if (!basket.getDiscountedItemList().contains(item)) {
                            basket.addDiscountedItem(item);
                        }
                    }

                    Long serviceId = resultSet.getLong("service_id");
                    if (!resultSet.wasNull() && serviceId > 0) {
                        Service service = new Service();
                        service.setId(serviceId);
                        service.setName(resultSet.getString("service_name"));
                        service.setCost(resultSet.getDouble("service_cost"));
                        service.setDescription(resultSet.getString("service_desc"));
                        service.setServiceProvider(resultSet.getString("service_provider"));
                        if (!basket.getServiceList().contains(service)) {
                            basket.addService(service);
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(conn);
        }
        return basket;
    }

    private Basket mapResultSetToBasket(ResultSet resultSet) throws SQLException {
        Basket basket = new Basket();
        basket.setId(resultSet.getLong("id"));
        basket.setSumCost(resultSet.getDouble("sum_cost"));
        basket.setDate(resultSet.getTimestamp("date"));
        return basket;
    }
}