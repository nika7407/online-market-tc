
ALTER TABLE users
ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE food_products
ADD COLUMN quantity INTEGER DEFAULT 0;

ALTER TABLE users
ADD CONSTRAINT unique_user_email UNIQUE (email);

ALTER TABLE delivery_addresses
ALTER COLUMN delivery_address SET NOT NULL;

ALTER TABLE users
ADD COLUMN userDescription TEXT(50);