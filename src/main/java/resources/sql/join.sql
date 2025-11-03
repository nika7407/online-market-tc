-- #1 left

SELECT
    u.id AS id, u.name AS name,
    b.id AS basket_id, b.sum_cost AS total
FROM users u
LEFT JOIN baskets b ON u.basket_id = b.id
ORDER BY u.id;

--#2 another left
SELECT
    users.id,
    users.name,

	debit_cards.card_number,
    debit_cards.money_amount,
    debit_cards.active
FROM users
LEFT JOIN debit_cards
    ON users.card_number = debit_cards.card_number
ORDER BY users.id;


--#3 right
SELECT
      b.id AS id,
      b.sum_cost AS total,
      u.id AS user_id,
      u.name AS user_name
  FROM users u
  RIGHT JOIN baskets b ON u.basket_id = b.id
  ORDER BY b.id;

  --#4 inner join
  SELECT
      users.id,
      users.name,
      debit_cards.card_number,
      debit_cards.money_amount
  FROM users
  INNER JOIN debit_cards
      ON users.card_number = debit_cards.card_number;

--#5 full
SELECT
    food_products.name AS food_product_name,
    non_perishable_products.name AS non_perishable_product_name
FROM food_products
FULL OUTER JOIN non_perishable_products
    ON food_products.id = non_perishable_products.id;

