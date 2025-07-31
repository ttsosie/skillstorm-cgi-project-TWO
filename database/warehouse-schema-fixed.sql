# Warehouse Database to store tea related inventory
-- warehouse -> products: ONE-TO-MANY because 1 warehouse can hold many products
-- products -> orders: ONE-TO-MANY because 1 product can be apart of many orders
-- customers -> orders: ONE-TO-MANY because 1 customer can have many orders
-- orders -> products: MANY-TO-ONE because each order record/row has 1 product

CREATE SCHEMA `warehouse` ;

# Table for Warehouses - id (AutoIncrement), name, state
CREATE TABLE `warehouse`.`warehouses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `warehouse_name` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

# Table for Products - id (AutoIncrement), name, price, detail, warehouse location, quantity
CREATE TABLE `warehouse`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  `details` VARCHAR(60) NOT NULL,
  `warehouse_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `warehouse_id_idx` (`warehouse_id` ASC) VISIBLE,
  CONSTRAINT `warehouse_id`
    FOREIGN KEY (`warehouse_id`)
    REFERENCES `warehouse`.`warehouses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);
    
# Table for Customers - id (AutoIncrement), first name, last name, email, state
CREATE TABLE `warehouse`.`customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
# Table for Orders - id (AutoIncrement), customer id, product id
CREATE TABLE `warehouse`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `customer_id_idx` (`customer_id` ASC) VISIBLE,
  INDEX `product_id_idx` (`product_id` ASC, `customer_id` ASC) VISIBLE,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `warehouse`.`customers` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `warehouse`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
# Insert 2x records into table warehouses with fields - name, state
INSERT INTO warehouse.warehouses (warehouse_name, state) VALUES ('Golden Leaf', 'CA');
INSERT INTO warehouse.warehouses (warehouse_name, state) VALUES ('Cloudberry Demure Ware', 'NY');

# Insert 20x records into products table for tea consumables with fields - name, price, detail, warehouse location, quantity

# Insert 20x records into products table for tea accessories with fields - name, price, detail, warehouse location, quantity

# Insert 20x records into customers table with fields - first name, last name, email, state

# Insert 20x records into orders table with fields - customer id, product id