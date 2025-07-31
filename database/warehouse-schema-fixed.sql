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

# Insert 20x records into products table for Golden Leaf warehouse_id=1 - name, price, details, warehouse location, quantity
INSERT INTO warehouse.products (product_name, price, details, warehouse_id, quantity) VALUES
('Moonlit Jasmine', 12.50, 'Delicate green tea scented with jasmine flowers', 1, 100),
('Crimson Chai', 11.25, 'Spiced black tea with hints of cinnamon and clove', 1, 85),
('Golden Sencha', 10.99, 'Premium Japanese green tea with grassy notes', 1, 90),
('Forest Mint', 9.50, 'Peppermint and spearmint herbal blend', 1, 120),
('Peach Ember Oolong', 13.20, 'Smooth oolong with sun-dried peaches', 1, 70),
('Vanilla Fog', 11.80, 'Earl Grey tea with vanilla bean and cream', 1, 60),
('Chamomile Lullaby', 9.99, 'Calming blend of chamomile and apple blossom', 1, 110),
('Berry Bramble', 10.40, 'Tart hibiscus with raspberry and elderberry', 1, 95),
('Lemon Zing', 10.75, 'Green tea with lemongrass and citrus peel', 1, 80),
('White Moon Bloom', 14.50, 'Tender white tea with floral undertones', 1, 50),
('Rose Gold Darjeeling', 12.60, 'Elegant Indian black tea with rose petals', 1, 75),
('Spiced Turmeric Glow', 10.20, 'Golden turmeric with ginger and cinnamon', 1, 65),
('Coconut Cream Green', 11.90, 'Tropical coconut over creamy green tea base', 1, 70),
('Maple Oolong Drift', 13.80, 'Aged oolong kissed with maple sweetness', 1, 45),
('Apple Orchard Blend', 9.70, 'Warm rooibos tea with dried apple and nutmeg', 1, 100),
('Lavender Earl', 10.60, 'Floral twist on a bergamot classic', 1, 85),
('Citrus Chamomile', 9.95, 'Zesty twist on a relaxing classic', 1, 90),
('Ginger Ember', 11.25, 'Zingy black tea with ginger and cardamom', 1, 75),
('Caramel Pu-erh', 13.40, 'Earthy pu-erh tea sweetened with caramel', 1, 40),
('Mint Cocoa Dream', 10.99, 'Peppermint tea with cacao husks and vanilla', 1, 60);

# Insert 20x records into products table for Cloudberry Demure Ware warehouse_id=2 - name, price, details, warehouse location, quantity
INSERT INTO warehouse.products (product_name, price, details, warehouse_id, quantity) VALUES
('Celadon Teacup Set', 24.99, 'Set of 4 pale green porcelain teacups', 2, 40),
('Moonstone Infuser Mug', 18.50, 'Glass mug with moon-etched stainless steel infuser', 2, 60),
('Bamboo Matcha Whisk', 11.95, '80-prong chasen for ceremonial matcha prep', 2, 50),
('Tea Leaf Spoon', 6.75, 'Vintage brass spoon shaped like a tea leaf', 2, 80),
('Cloud Steep Kettle', 39.99, 'White ceramic stovetop kettle with wood handle', 2, 25),
('Cherrywood Tea Tray', 22.40, 'Slotted draining tray for gongfu ceremonies', 2, 30),
('Blossom Tea Towels', 9.95, 'Floral-embroidered soft cotton cloths', 2, 70),
('Gongfu Gaiwan', 16.90, 'Lidded bowl for elegant Chinese steeping', 2, 45),
('Rose Quartz Timer', 5.50, 'Hourglass sand timer for 3/5/7 minute steeps', 2, 100),
('Butterfly Lid Rest', 3.25, 'Porcelain rest for teapot lids or strainers', 2, 90),
('Sakura Tea Jar', 12.80, 'Airtight ceramic jar for loose tea storage', 2, 60),
('Herbalist’s Scoop', 7.40, 'Hand-carved wooden tea scoop', 2, 85),
('Blooming Teapot', 27.99, 'Clear glass pot for blooming tea flowers', 2, 20),
('Mystic Warmer', 14.50, 'Candle-heated ceramic base to keep tea warm', 2, 35),
('Lotus Strainer Set', 10.60, 'Decorative flower-shaped infuser with dish', 2, 50),
('Cast Iron Serenity Pot', 44.00, 'Matte black teapot with lotus engraving', 2, 15),
('Steeping Wand', 8.25, 'Slender steel infuser wand for mugs or flasks', 2, 75),
('Traveler’s Flask', 20.95, 'Insulated bottle with built-in tea basket', 2, 40),
('Zen Kettle', 36.80, 'Electric kettle with variable temp presets', 2, 18),
('Whispering Coaster Set', 9.99, 'Soft cork coasters with tea quotes', 2, 90);

# Insert 20x records into customers table with fields - first name, last name, email, state

# Insert 20x records into orders table with fields - customer id, product id