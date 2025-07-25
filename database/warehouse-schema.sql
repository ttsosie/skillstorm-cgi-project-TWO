# Warehouse Database to store tea inventory
-- warehouses: ONE-TO-MANY because 1 warehouse can hold many products
-- products: ONE-TO-MANY because 1 product can be apart of many orders
-- customers: ONE-TO-MANY because 1 customer can have many orders
-- orders: MANY-TO-ONE because each order record/row has 1 product

CREATE SCHEMA `warehouse` ;

CREATE TABLE `warehouse`.`warehouses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `warehouse_name` VARCHAR(45) NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  `capacity` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);
  
ALTER TABLE `warehouse`.`warehouses` 
CHANGE COLUMN `name` `warehouse_name` VARCHAR(45) NOT NULL ;

CREATE TABLE `warehouse`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(45) NOT NULL,
  `price` DECIMAL NOT NULL,
  `description` VARCHAR(100) NULL,
  `warehouse_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `warehouse`.`products` 
DROP COLUMN `warehouse_id`,
DROP COLUMN `quantity`;

ALTER TABLE `warehouse`.`products` 
CHANGE COLUMN `name` `product_name` VARCHAR(45) NOT NULL ;
    
CREATE TABLE `warehouse`.`customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NULL,
  `location` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `warehouse`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `order_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `customer_id_idx` (`customer_id` ASC) VISIBLE,
  INDEX `product_id_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `warehouse`.`customers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `warehouse`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
insert into warehouse.warehouses (warehouse_name, location, capacity) values ('Giant Knotweed', 'San Miguel', 414);
insert into warehouse.warehouses (warehouse_name, location, capacity) values ('Panamint Milkvetch', 'Godong', 295);
insert into warehouse.warehouses (warehouse_name, location, capacity) values ('Mexican Buckeye', 'Zolochiv', 353);
insert into warehouse.warehouses (warehouse_name, location, capacity) values ('Slender Ditch Paspalum', 'Osielsko', 499);
    