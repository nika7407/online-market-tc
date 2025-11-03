-- number of bakets on delivery  > 1 basket
SELECT
    delivery_addresses.delivery_address,
    COUNT(baskets.id) AS total_baskets FROM baskets
JOIN delivery_addresses ON baskets.delivery_address_id = delivery_addresses.id
GROUP BY delivery_addresses.delivery_address
HAVING COUNT(baskets.id) > 1;

-- spend amount by user
SELECT
    users.id AS user_id, users.name AS user_name,
    SUM(baskets.sum_cost) AS spent
FROM users
LEFT JOIN baskets
    ON users.basket_id = baskets.id
GROUP BY users.id, users.name
HAVING SUM(baskets.sum_cost) > 50;

-- average cost for expensive products over 5
SELECT
    'food product' AS product_type,
    AVG(food_products.cost) AS average_cost
FROM food_products
GROUP BY product_type
HAVING AVG(food_products.cost) > 5

UNION ALL

SELECT
    'non-perishable product' AS product_type,
    AVG(non_perishable_products.cost) AS average_cost
FROM non_perishable_products
GROUP BY product_type
HAVING AVG(non_perishable_products.cost) > 5;

-- total services used more than once
SELECT
    services.name AS service_name,
    COUNT(basket_services.basket_id) AS used_in_baskets
FROM services
LEFT JOIN basket_services
    ON services.id = basket_services.service_id
GROUP BY services.name
HAVING COUNT(basket_services.basket_id) > 1;

-- min_max for cards with high average over 100
SELECT
    MAX(money_amount) AS max,
    MIN(money_amount) AS min,
    AVG(money_amount) AS avg
FROM debit_cards
GROUP BY active
HAVING AVG(money_amount) > 100;

-- discounted items per basket with multiple items
SELECT
    basket_discounted_items.basket_id,
    COUNT(discounted_items.id) AS total_discounted
FROM basket_discounted_items
JOIN discounted_items
    ON basket_discounted_items.discounted_item_id = discounted_items.id
GROUP BY basket_discounted_items.basket_id
HAVING COUNT(discounted_items.id) >= 2;

--food producvts per basket with food items
SELECT
    baskets.id AS basket_id,
    STRING_AGG(food_products.name, '|') AS food_products_list
FROM baskets
LEFT JOIN basket_food_products
    ON baskets.id = basket_food_products.basket_id
LEFT JOIN food_products
    ON basket_food_products.food_product_id = food_products.id
GROUP BY baskets.id
HAVING COUNT(food_products.id) > 0;