--insert examples for every table, ofc in prod id is added automatically and incrementally

INSERT INTO debit_cards(card_number, active, money_amount)
VALUES (57275127527, true, 1000.0);

INSERT INTO food_products(id, name, cost, expiration_date)
VALUES (1, 'bread', 3.0, '2024-02-15 00:00:00');

INSERT INTO non_perishable_products(id, name, cost, description)
VALUES (1, 'bicycle', 200, 'bicycle for riding');

INSERT INTO discounted_items(id, name, cost, description)
VALUES (1, 'rotten eggs', 0.5, 'eggs that are rotten');

INSERT INTO services(id, name, cost, description, service_provider)
VALUES (1, 'lawn_mowing', 20.2, 'mowing the lawn', 'lawnmowertm');

INSERT INTO delivery_addresses(id, delivery_address, user_id)
VALUES (1, 'random address', 1);

INSERT INTO baskets(id, sum_cost, date, delivery_address_id)
VALUES ('1', 0.0, '2025-10-01 10:30:00', 1);

INSERT INTO users(id, name, card_number, basket_id)
VALUES (1, 'nika', 57275127527, '1');

INSERT INTO basket_food_products(basket_id, food_product_id)
VALUES ('1', 1);

INSERT INTO basket_non_perishable_products(basket_id, non_perishable_product_id)
VALUES ('1', 1);

INSERT INTO basket_discounted_items(basket_id, discounted_item_id)
VALUES ('1', 1);

INSERT INTO basket_services(basket_id, service_id)
VALUES ('1', 1);