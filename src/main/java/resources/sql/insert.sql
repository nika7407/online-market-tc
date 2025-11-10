
INSERT INTO debit_cards(card_number, active, money_amount)
VALUES (57275127527, true, 1000.0);

INSERT INTO food_products(name, cost, expiration_date)
VALUES ('bread', 3.0, '2024-02-15 00:00:00');

INSERT INTO non_perishable_products(name, cost, description)
VALUES ('bicycle', 200, 'bicycle for ridthreading');

INSERT INTO discounted_items(name, cost, description)
VALUES ('rotten eggs', 0.5, 'eggs that are rotten');

INSERT INTO services(name, cost, description, service_provider)
VALUES ('lawn_mowing', 20.2, 'mowing the lawn', 'lawnmowertm');

INSERT INTO delivery_addresses(delivery_address, user_id)
VALUES ('random address', 1);

INSERT INTO baskets(sum_cost, date, delivery_address_id)
VALUES (0.0, '2025-10-01 10:30:00', 1);

INSERT INTO users(name, card_number, basket_id)
VALUES ('nika', 57275127527, '1');

INSERT INTO basket_food_products(basket_id, food_product_id)
VALUES ('1', 1);

INSERT INTO basket_non_perishable_products(basket_id, non_perishable_product_id)
VALUES ('1', 1);

INSERT INTO basket_discounted_items(basket_id, discounted_item_id)
VALUES ('1', 1);

INSERT INTO basket_services(basket_id, service_id)
VALUES ('1', 1);