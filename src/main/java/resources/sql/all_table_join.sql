SELECT
    users.id AS user_id, users.name AS user_name,

    debit_cards.card_number AS user_card_number,

    baskets.id AS basket_id, baskets.sum_cost AS basket_total_cost,

   delivery_addresses.delivery_address AS delivery_address
FROM users

JOIN debit_cards
    ON users.card_number = debit_cards.card_number
JOIN baskets
    ON users.basket_id = baskets.id
JOIN delivery_addresses
    ON baskets.delivery_address_id = delivery_addresses.id
LEFT JOIN basket_food_products
    ON baskets.id = basket_food_products.basket_id
LEFT JOIN food_products
    ON basket_food_products.food_product_id = food_products.id
LEFT JOIN basket_non_perishable_products
    ON baskets.id = basket_non_perishable_products.basket_id
LEFT JOIN non_perishable_products
    ON basket_non_perishable_products.non_perishable_product_id = non_perishable_products.id
LEFT JOIN basket_discounted_items
    ON baskets.id = basket_discounted_items.basket_id
LEFT JOIN discounted_items
    ON basket_discounted_items.discounted_item_id = discounted_items.id
LEFT JOIN basket_services
    ON baskets.id = basket_services.basket_id
LEFT JOIN services
    ON basket_services.service_id = services.id
GROUP BY
    users.id, users.name,

    debit_cards.card_number,

    baskets.id, baskets.sum_cost,
    delivery_addresses.delivery_address
ORDER BY
    users.id,
    baskets.id;
