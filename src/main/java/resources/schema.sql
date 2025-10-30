
CREATE TABLE IF NOT EXISTS FoodProduct (
    productId SERIAL PRIMARY KEY,
    name VARCHAR(25),
    cost DOUBLE PRECISION,
    expirationDate TIMESTAMP
);

CREATE TABLE IF NOT EXISTS NonPerishebleProduct (
    productId SERIAL PRIMARY KEY,
    name VARCHAR(25),
    cost DOUBLE PRECISION,
    description VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS DiscountedItem (
    discountedItemId SERIAL PRIMARY KEY,
    name VARCHAR(25),
    cost DOUBLE PRECISION,
    description VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS Service (
    serviceId SERIAL PRIMARY KEY,
    name VARCHAR(25),
    cost DOUBLE PRECISION,
    description VARCHAR(25),
    serviceProvider VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS DebitCard (
    cardNumber BIGINT PRIMARY KEY,
    active BOOLEAN,
    moneyAmount DOUBLE PRECISION
);

--mtm tables written in format name of the first "table + 2 + addres" ,  im not sure if this is right bit idk 
CREATE TABLE IF NOT EXISTS Delivery2Address (
    deliveryAddressId SERIAL PRIMARY KEY,
    deliveryAddress VARCHAR(50),
    userId BIGINT
);

CREATE TABLE IF NOT EXISTS Basket (
    basketId VARCHAR(25) PRIMARY KEY,
    sumCost DOUBLE PRECISION,
    date TIMESTAMP,
    deliveryAddressId INT,
    FOREIGN KEY (deliveryAddressId) REFERENCES DeliveryAddress(deliveryAddressId)
);

CREATE TABLE IF NOT EXISTS Basket2FoodProduct (
    basketId VARCHAR(25),
    productId INT,
    PRIMARY KEY (basketId, productId),
    FOREIGN KEY (basketId) REFERENCES Basket(basketId),
    FOREIGN KEY (productId) REFERENCES FoodProduct(productId)
);

CREATE TABLE IF NOT EXISTS Basket2NonPerishebleProduct (
    basketId VARCHAR(25),
    productId INT,
    PRIMARY KEY (basketId, productId),
    FOREIGN KEY (basketId) REFERENCES Basket(basketId),
    FOREIGN KEY (productId) REFERENCES NonPerishebleProduct(productId)
);

CREATE TABLE IF NOT EXISTS Basket2DiscountedItem (
    basketId VARCHAR(25),
    discountedItemId INT,
    PRIMARY KEY (basketId, discountedItemId),
    FOREIGN KEY (basketId) REFERENCES Basket(basketId),
    FOREIGN KEY (discountedItemId) REFERENCES DiscountedItem(discountedItemId)
);

CREATE TABLE IF NOT EXISTS Basket2Service (
    basketId VARCHAR(25),
    serviceId INT,
    PRIMARY KEY (basketId, serviceId),
    FOREIGN KEY (basketId) REFERENCES Basket(basketId),
    FOREIGN KEY (serviceId) REFERENCES Service(serviceId)
);

CREATE TABLE IF NOT EXISTS Users (
    userId BIGINT PRIMARY KEY,
    name VARCHAR(25),
    cardNumber BIGINT,
    basketId VARCHAR(25),
    FOREIGN KEY (cardNumber) REFERENCES DebitCard(cardNumber),
    FOREIGN KEY (basketId) REFERENCES Basket(basketId)
);
