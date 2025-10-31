
CREATE TABLE IF NOT EXISTS food_products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(25),
    cost DOUBLE PRECISION,
    expiration_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS non_perishable_products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(25),
    cost DOUBLE PRECISION,
    description VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS discounted_items (
    id SERIAL PRIMARY KEY,
    name VARCHAR(25),
    cost DOUBLE PRECISION,
    description VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS services (
    id SERIAL PRIMARY KEY,
    name VARCHAR(25),
    cost DOUBLE PRECISION,
    description VARCHAR(25),
    service_provider VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS debit_cards (
    card_number BIGINT PRIMARY KEY,
    active BOOLEAN,
    money_amount DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS delivery_addresses (
    id SERIAL PRIMARY KEY,
    delivery_address VARCHAR(50),
    user_id BIGINT
);

CREATE TABLE IF NOT EXISTS baskets (
    id VARCHAR(25) PRIMARY KEY,
    sum_cost DOUBLE PRECISION,
    date TIMESTAMP,
    delivery_address_id INT,
    FOREIGN KEY (delivery_address_id) REFERENCES delivery_addresses(id)
);

CREATE TABLE IF NOT EXISTS basket_food_products (
    basket_id VARCHAR(25),
    food_product_id INT,
    PRIMARY KEY (basket_id, food_product_id),
    FOREIGN KEY (basket_id) REFERENCES baskets(id),
    FOREIGN KEY (food_product_id) REFERENCES food_products(id)
);

CREATE TABLE IF NOT EXISTS basket_non_perishable_products (
    basket_id VARCHAR(25),
    non_perishable_product_id INT,
    PRIMARY KEY (basket_id, non_perishable_product_id),
    FOREIGN KEY (basket_id) REFERENCES baskets(id),
    FOREIGN KEY (non_perishable_product_id) REFERENCES non_perishable_products(id)
);

CREATE TABLE IF NOT EXISTS basket_discounted_items (
    basket_id VARCHAR(25),
    discounted_item_id INT,
    PRIMARY KEY (basket_id, discounted_item_id),
    FOREIGN KEY (basket_id) REFERENCES baskets(id),
    FOREIGN KEY (discounted_item_id) REFERENCES discounted_items(id)
);

CREATE TABLE IF NOT EXISTS basket_services (
    basket_id VARCHAR(25),
    service_id INT,
    PRIMARY KEY (basket_id, service_id),
    FOREIGN KEY (basket_id) REFERENCES baskets(id),
    FOREIGN KEY (service_id) REFERENCES services(id)
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY,
    name VARCHAR(25),
    card_number BIGINT,
    basket_id VARCHAR(25),
    FOREIGN KEY (card_number) REFERENCES debit_cards(card_number),
    FOREIGN KEY (basket_id) REFERENCES baskets(id)
);