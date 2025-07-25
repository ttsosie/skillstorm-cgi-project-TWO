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

ALTER TABLE `warehouse`.`products` 
DROP FOREIGN KEY `warehouse_id`;
ALTER TABLE `warehouse`.`products` 
CHANGE COLUMN `id` `id` INT NOT NULL ;
ALTER TABLE `warehouse`.`products` 
ADD CONSTRAINT `warehouse_id`
  FOREIGN KEY (`id`)
  REFERENCES `warehouse`.`warehouses` (`id`)
  ON UPDATE CASCADE;
    
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

insert into warehouse.products (product_name, price, description) values ('Ruppiaceae', '37', 'Etiam faucibus cursus urna.');
insert into warehouse.products (product_name, price, description) values ('Convolvulaceae', '39', 'Integer tincidunt ante vel ipsum.');
insert into warehouse.products (product_name, price, description) values ('Hydrangeaceae', '27', 'Aliquam sit amet diam in magna bibendum imperdiet.');
insert into warehouse.products (product_name, price, description) values ('Burmanniaceae', '42', 'Proin eu mi.');
insert into warehouse.products (product_name, price, description) values ('Polypodiaceae', '24', 'Donec ut mauris eget massa tempor convallis.');
insert into warehouse.products (product_name, price, description) values ('Oxalidaceae', '23', 'Duis aliquam convallis nunc.');
insert into warehouse.products (product_name, price, description) values ('Fabaceae', '25', 'Aliquam non mauris. Morbi non lectus.');
insert into warehouse.products (product_name, price, description) values ('Asteraceae', '43', 'Morbi non lectus.');
insert into warehouse.products (product_name, price, description) values ('Verbenaceae', '42', 'Donec ut mauris eget massa tempor convallis.');
insert into warehouse.products (product_name, price, description) values ('Boraginaceae', '38', 'In eleifend quam a odio.');
insert into warehouse.products (product_name, price, description) values ('Agavaceae', '40', 'Donec posuere metus vitae ipsum. Aliquam non mauris.');
insert into warehouse.products (product_name, price, description) values ('Oleaceae', '26', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
insert into warehouse.products (product_name, price, description) values ('Caryophyllaceae', '28', 'Vivamus tortor. Duis mattis egestas metus.');
insert into warehouse.products (product_name, price, description) values ('Fabaceae', '35', 'Phasellus id sapien in sapien iaculis congue.');
insert into warehouse.products (product_name, price, description) values ('Sarraceniaceae', '36', 'Vestibulum ante ipsum primis in faucibus or.');
insert into warehouse.products (product_name, price, description) values ('Magnoliaceae', '33', 'In tempor, turpis nec euismod scelerisque.');
insert into warehouse.products (product_name, price, description) values ('Asteraceae', '26', 'In blandit ultrices enim. Lorem ipsum dolor sit amet.');
insert into warehouse.products (product_name, price, description) values ('Asteraceae', '39', 'Morbi odio odio, elementum eu, interdum eu, tincidunto.');
insert into warehouse.products (product_name, price, description) values ('Poaceae', '33', 'Proin leo odio, porttitor id, consequat in, consequat ut.');
insert into warehouse.products (product_name, price, description) values ('Sphagnaceae', '36', 'Nam congue, risus semper porta volutpat, quam pede.');

insert into warehouse.customers (customer_name, email, location) values ('Moira Shepard', 'mshepard0@ehow.com', 'North Carolina');
insert into warehouse.customers (customer_name, email, location) values ('Emili Longmead', 'elongmead1@xing.com', 'Pennsylvania');
insert into warehouse.customers (customer_name, email, location) values ('Samson Haycraft', 'shaycraft2@moonfruit.com', 'North Carolina');
insert into warehouse.customers (customer_name, email, location) values ('Walton Abbes', 'wabbes3@moonfruit.com', 'West Virginia');
insert into warehouse.customers (customer_name, email, location) values ('Darnall Beecker', 'dbeecker4@cdc.gov', 'Missouri');
insert into warehouse.customers (customer_name, email, location) values ('Obidiah Antoniazzi', 'oantoniazzi5@webmd.com', 'New York');
insert into warehouse.customers (customer_name, email, location) values ('Edsel Rigard', 'erigard6@blogspot.com', 'Oklahoma');
insert into warehouse.customers (customer_name, email, location) values ('Dwayne Jickells', 'djickells7@yolasite.com', 'North Carolina');
insert into warehouse.customers (customer_name, email, location) values ('Prissie Claye', 'pclaye8@prnewswire.com', 'Indiana');
insert into warehouse.customers (customer_name, email, location) values ('Balduin Sleet', 'bsleet9@php.net', 'Georgia');
insert into warehouse.customers (customer_name, email, location) values ('Dorolisa Well', 'dwella@eepurl.com', 'New York');
insert into warehouse.customers (customer_name, email, location) values ('Ellary Lilly', 'elillyb@symantec.com', 'Arizona');
insert into warehouse.customers (customer_name, email, location) values ('Dan Bordone', 'dbordonec@parallels.com', 'Massachusetts');
insert into warehouse.customers (customer_name, email, location) values ('Janie Habbin', 'jhabbind@umich.edu', 'Colorado');
insert into warehouse.customers (customer_name, email, location) values ('Gretal Murrhaupt', 'gmurrhaupte@cbsnews.com', 'Wisconsin');
insert into warehouse.customers (customer_name, email, location) values ('Rosabelle Collumbine', 'rcollumbinef@illinois.edu', 'Arizona');
insert into warehouse.customers (customer_name, email, location) values ('Ermanno Ransome', 'eransomeg@forbes.com', 'Minnesota');
insert into warehouse.customers (customer_name, email, location) values ('Eddie Ackeroyd', 'eackeroydh@timesonline.co.uk', 'California');
insert into warehouse.customers (customer_name, email, location) values ('Rhianon Bridgett', 'rbridgetti@elegantthemes.com', 'Florida');
insert into warehouse.customers (customer_name, email, location) values ('Berkly Vlach', 'bvlachj@stumbleupon.com', 'New Jersey');