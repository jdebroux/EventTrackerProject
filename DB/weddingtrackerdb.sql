-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema weddingtrackerdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `weddingtrackerdb` ;

-- -----------------------------------------------------
-- Schema weddingtrackerdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `weddingtrackerdb` DEFAULT CHARACTER SET utf8 ;
USE `weddingtrackerdb` ;

-- -----------------------------------------------------
-- Table `venue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `venue` ;

CREATE TABLE IF NOT EXISTS `venue` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `contact_first_name` VARCHAR(45) NOT NULL,
  `contact_last_name` VARCHAR(45) NOT NULL,
  `phone` BIGINT(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wedding`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wedding` ;

CREATE TABLE IF NOT EXISTS `wedding` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `venue_id` INT NULL,
  `booking_date` DATE NOT NULL,
  `celebration_date` DATE NOT NULL,
  `up_lighting` INT NOT NULL,
  `total_cost` DOUBLE NOT NULL,
  `notes` VARCHAR(5000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_wedding_venue_idx` (`venue_id` ASC),
  CONSTRAINT `fk_wedding_venue`
    FOREIGN KEY (`venue_id`)
    REFERENCES `venue` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `client` ;

CREATE TABLE IF NOT EXISTS `client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `wedding_id` INT NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` BIGINT(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_client_wedding1_idx` (`wedding_id` ASC),
  CONSTRAINT `fk_client_wedding1`
    FOREIGN KEY (`wedding_id`)
    REFERENCES `wedding` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dj`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dj` ;

CREATE TABLE IF NOT EXISTS `dj` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `pay_per_gig` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` BIGINT(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dj_wedding`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dj_wedding` ;

CREATE TABLE IF NOT EXISTS `dj_wedding` (
  `dj_id` INT NOT NULL,
  `wedding_id` INT NOT NULL,
  PRIMARY KEY (`dj_id`, `wedding_id`),
  INDEX `fk_dj_has_wedding_wedding1_idx` (`wedding_id` ASC),
  INDEX `fk_dj_has_wedding_dj1_idx` (`dj_id` ASC),
  CONSTRAINT `fk_dj_has_wedding_dj1`
    FOREIGN KEY (`dj_id`)
    REFERENCES `dj` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dj_has_wedding_wedding1`
    FOREIGN KEY (`wedding_id`)
    REFERENCES `wedding` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS weddinguser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'weddinguser'@'localhost' IDENTIFIED BY 'weddinguser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'weddinguser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `venue`
-- -----------------------------------------------------
START TRANSACTION;
USE `weddingtrackerdb`;
INSERT INTO `venue` (`id`, `name`, `address`, `contact_first_name`, `contact_last_name`, `phone`) VALUES (1, 'Pines At Genesee', '633 Park Point Dr, Golden, CO 80401', 'Vivian', 'Weinress', 3035267939);
INSERT INTO `venue` (`id`, `name`, `address`, `contact_first_name`, `contact_last_name`, `phone`) VALUES (2, 'Ellis Ranch', '2331 Ellis Ranch Ln, Loveland, CO 80538', 'Shawn', 'Ellis', 9705939570);

COMMIT;


-- -----------------------------------------------------
-- Data for table `wedding`
-- -----------------------------------------------------
START TRANSACTION;
USE `weddingtrackerdb`;
INSERT INTO `wedding` (`id`, `venue_id`, `booking_date`, `celebration_date`, `up_lighting`, `total_cost`, `notes`) VALUES (1, 1, '1990-03-23', '1990-03-23', 12, 1595.00, 'DUMMY WEDDING - PLEASE ASSIGN NEW WEDDING TO CLIENT');
INSERT INTO `wedding` (`id`, `venue_id`, `booking_date`, `celebration_date`, `up_lighting`, `total_cost`, `notes`) VALUES (2, 2, '2018-06-24', '2019-06-20', 4, 1195.00, 'Might upgrade to Summit Package');
INSERT INTO `wedding` (`id`, `venue_id`, `booking_date`, `celebration_date`, `up_lighting`, `total_cost`, `notes`) VALUES (3, 1, '2018-10-10', '2019-10-05', 12, 1695.00, 'Wedding Planner - Christine Dublie');
INSERT INTO `wedding` (`id`, `venue_id`, `booking_date`, `celebration_date`, `up_lighting`, `total_cost`, `notes`) VALUES (4, 1, '2018-03-12', '2020-05-14', 12, 1595.00, 'Needs lavalier mic for ceremony');

COMMIT;


-- -----------------------------------------------------
-- Data for table `client`
-- -----------------------------------------------------
START TRANSACTION;
USE `weddingtrackerdb`;
INSERT INTO `client` (`id`, `wedding_id`, `first_name`, `last_name`, `email`, `phone`) VALUES (1, 2, 'Ashley', 'Kiefer', 'ashley@gmail.com', 3032486679);
INSERT INTO `client` (`id`, `wedding_id`, `first_name`, `last_name`, `email`, `phone`) VALUES (2, 2, 'Andrew', 'Henderson', 'andrew@gmail.com', 3037742579);
INSERT INTO `client` (`id`, `wedding_id`, `first_name`, `last_name`, `email`, `phone`) VALUES (3, 3, 'Brittany', 'Hyatt', 'Brittany@gmail.com', 7208932216);
INSERT INTO `client` (`id`, `wedding_id`, `first_name`, `last_name`, `email`, `phone`) VALUES (4, 3, 'Kevin', 'Materdorne', 'Kevin@gmail.com', 7202178894);
INSERT INTO `client` (`id`, `wedding_id`, `first_name`, `last_name`, `email`, `phone`) VALUES (5, 4, 'Eileen', 'Christenson', 'eileen@gmail.com', 3034624577);
INSERT INTO `client` (`id`, `wedding_id`, `first_name`, `last_name`, `email`, `phone`) VALUES (6, 4, 'Peter', 'Belaigney', 'peter@gmail.com', 3036963131);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dj`
-- -----------------------------------------------------
START TRANSACTION;
USE `weddingtrackerdb`;
INSERT INTO `dj` (`id`, `first_name`, `last_name`, `pay_per_gig`, `email`, `phone`) VALUES (1, 'Joe', 'DeBroux', 300, 'joe@mountainmusicco.com', 4143807218);
INSERT INTO `dj` (`id`, `first_name`, `last_name`, `pay_per_gig`, `email`, `phone`) VALUES (2, 'Josh', 'Rovder', 200, 'josh@mountainmusicco.com', 7208675309);
INSERT INTO `dj` (`id`, `first_name`, `last_name`, `pay_per_gig`, `email`, `phone`) VALUES (3, 'Braiden', 'Miller', 100, 'braiden@mountainmusicco.com', 7206742243);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dj_wedding`
-- -----------------------------------------------------
START TRANSACTION;
USE `weddingtrackerdb`;
INSERT INTO `dj_wedding` (`dj_id`, `wedding_id`) VALUES (1, 2);
INSERT INTO `dj_wedding` (`dj_id`, `wedding_id`) VALUES (2, 2);
INSERT INTO `dj_wedding` (`dj_id`, `wedding_id`) VALUES (1, 3);
INSERT INTO `dj_wedding` (`dj_id`, `wedding_id`) VALUES (3, 3);
INSERT INTO `dj_wedding` (`dj_id`, `wedding_id`) VALUES (2, 4);
INSERT INTO `dj_wedding` (`dj_id`, `wedding_id`) VALUES (3, 4);

COMMIT;

