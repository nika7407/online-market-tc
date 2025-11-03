-- deletes

DELETE FROM basket_services
WHERE basket_id = '1';

DELETE FROM basket_discounted_items
WHERE basket_id = '1';

DELETE FROM basket_non_perishable_products
WHERE basket_id = '1';

DELETE FROM basket_food_products
WHERE basket_id = '1';

DELETE FROM users
WHERE id = 1;

DELETE FROM baskets
WHERE id = '1';

DELETE FROM delivery_addresses
WHERE id = 1;

DELETE FROM debit_cards
WHERE card_number = 57275127527;

DELETE FROM services
WHERE id = 1;

DELETE FROM discounted_items
WHERE id = 1;

DELETE FROM non_perishable_products
WHERE id = 1;

DELETE FROM food_products
WHERE id = 1;