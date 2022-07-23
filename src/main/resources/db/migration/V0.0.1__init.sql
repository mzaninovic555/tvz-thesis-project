-- -----------------------------------------------------
-- Schema bookstore
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Table `publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `publisher` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);
-- -----------------------------------------------------
-- Table `language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `language` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`)
);
-- -----------------------------------------------------
-- Table `author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `author` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(100) NOT NULL,
    `last_name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
);
-- -----------------------------------------------------
-- Table `book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `format` VARCHAR(20) NOT NULL,
    `page_number` SMALLINT NOT NULL,
    `binding` VARCHAR(20) NOT NULL,
    `mass` DOUBLE NOT NULL,
    `barcode` CHAR(13) NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `price` DECIMAL(6, 2) NOT NULL,
    `description` TEXT NOT NULL,
    `publishing_year` CHAR(4) NOT NULL,
    `stock` TINYINT NOT NULL,
    `isbn` VARCHAR(13) NOT NULL,
    `image_path` TEXT NOT NULL,
    `date_added` DATE NOT NULL,
    `publisher_id` INT NOT NULL,
    `language_id` INT NOT NULL,
    `author_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_book_publisher1_idx` (`publisher_id` ASC),
    INDEX `fk_book_language1_idx` (`language_id` ASC),
    INDEX `fk_book_author1_idx` (`author_id` ASC),
    CONSTRAINT `fk_book_publisher1` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_book_language1` FOREIGN KEY (`language_id`) REFERENCES `language` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_book_author1` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `category` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);
-- -----------------------------------------------------
-- Table `book_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_category` (
    `book_id` INT NOT NULL,
    `category_id` INT NOT NULL,
    INDEX `fk_book_category_book_idx` (`book_id` ASC),
    INDEX `fk_book_category_category1_idx` (`category_id` ASC),
    CONSTRAINT `fk_book_category_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_book_category_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `country` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `country_code` CHAR(2) NOT NULL,
    PRIMARY KEY (`id`)
);
-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(20) NOT NULL,
    `password` TEXT NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `phone_number` VARCHAR(11) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `postal_code` CHAR(5) NOT NULL,
    `city` VARCHAR(255) NOT NULL,
    `country_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_user_country1_idx` (`country_id` ASC),
    CONSTRAINT `fk_user_country1` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `date_placed` DATETIME NOT NULL,
    `total_price` DECIMAL(7, 2) NOT NULL,
    `user_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_order_user1_idx` (`user_id` ASC),
    CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `order_book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_book` (
    `order_id` INT NOT NULL,
    `book_id` INT NOT NULL,
    `quantity` TINYINT NOT NULL,
    INDEX `fk_order_book_order1_idx` (`order_id` ASC),
    INDEX `fk_order_book_book1_idx` (`book_id` ASC),
    CONSTRAINT `fk_order_book_order1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_order_book_book1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`)
);
-- -----------------------------------------------------
-- Table `user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_role` (
    `role_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    INDEX `fk_user_role_role1_idx` (`role_id` ASC),
    INDEX `fk_user_role_user1_idx` (`user_id` ASC),
    CONSTRAINT `fk_user_role_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_role_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `discount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `discount` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `discount_price` DECIMAL(6, 2) NOT NULL,
    `started_at` DATE NOT NULL,
    `ends_at` DATE NULL,
    `book_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_discount_book1_idx` (`book_id` ASC),
    CONSTRAINT `fk_discount_book1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);