CREATE TABLE IF NOT EXISTS `review` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `score` TINYINT NOT NULL,
    `book_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_review_book1_idx` (`book_id` ASC),
    CONSTRAINT `fk_review_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    INDEX `fk_review_user_idx` (`user_id` ASC),
    CONSTRAINT `fk_review_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
