
-- number of bakets on delivery
SELECT
    delivery_addresses.delivery_address,
    COUNT(baskets.id) AS total_baskets FROM baskets
JOIN delivery_addresses ON baskets.delivery_address_id = delivery_addresses.id
GROUP BY delivery_addresses.delivery_address;

-- spend amount by user
SELECT
    users.id AS user_id, users.name AS user_name,
    SUM(baskets.sum_cost) AS spent
FROM users
LEFT JOIN baskets
    ON users.basket_id = baskets.id
GROUP BY users.id, users.name;

-- average cost
SELECT
    'food product' AS product_type,
    AVG(food_products.cost) AS average_cost
FROM food_products
GROUP BY product_type

UNION ALL

SELECT
    'non-perishable product' AS product_type,
    AVG(non_perishable_products.cost) AS average_cost
FROM non_perishable_products
GROUP BY product_type;

-- total services used
SELECT
    services.name AS service_name,
    COUNT(basket_services.basket_id) AS used_in_baskets
FROM services
LEFT JOIN basket_services
    ON services.id = basket_services.service_id
GROUP BY services.name;

-- min_max
SELECT
    MAX(money_amount) AS max,
    MIN(money_amount) AS min,
    AVG(money_amount) AS avg
FROM debit_cards;

-- discounted items per basket
SELECT
    basket_discounted_items.basket_id,
    COUNT(discounted_items.id) AS total_discounted
FROM basket_discounted_items
JOIN discounted_items
    ON basket_discounted_items.discounted_item_id = discounted_items.id
GROUP BY basket_discounted_items.basket_id;

--food producvts per basket
SELECT
    baskets.id AS basket_id,
    STRING_AGG(food_products.name, '|') AS food_products_list
FROM baskets
LEFT JOIN basket_food_products
    ON baskets.id = basket_food_products.basket_id
LEFT JOIN food_products
    ON basket_food_products.food_product_id = food_products.id
GROUP BY baskets.id;
