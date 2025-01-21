-- MySQL Script generated by MySQL Workbench
-- Tue Jan 21 13:06:28 2025
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Customers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Customers` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Customers` (
  `Id` INT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NULL DEFAULT NULL,
  `last_name` VARCHAR(50) NULL DEFAULT NULL,
  `date_of_birth` DATE NULL DEFAULT NULL,
  `address` TEXT NULL DEFAULT NULL,
  `phone_number` VARCHAR(15) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`));


-- -----------------------------------------------------
-- Table `mydb`.`Account_Types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Account_Types` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Account_Types` (
  `Id` INT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(50) NULL DEFAULT NULL,
  `minimum_balance` DECIMAL(10,2) NULL DEFAULT NULL,
  `interest_rate` DECIMAL(5,2) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`));


-- -----------------------------------------------------
-- Table `mydb`.`Accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Accounts` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Accounts` (
  `Id` INT NULL AUTO_INCREMENT,
  `customer_id` INT NULL DEFAULT NULL,
  `account_type_id` INT NULL DEFAULT NULL,
  `balance` DECIMAL(15,2) NULL DEFAULT NULL,
  `open_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `SECONDARY` (`customer_id` ASC) INVISIBLE,
  INDEX `SECONDARY` (`account_type_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`Customers` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT ``
    FOREIGN KEY (`account_type_id`)
    REFERENCES `mydb`.`Account_Types` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `mydb`.`Transactions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Transactions` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Transactions` (
  `Id` INT NULL AUTO_INCREMENT,
  `account_id` INT NULL DEFAULT NULL,
  `transaction_date` DATETIME NULL DEFAULT NULL,
  `transaction_type` ENUM('Deposit', 'Withdrawal', 'Transfer') NULL DEFAULT NULL,
  `amount` DECIMAL(15,2) NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `SECONDARY` (`account_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`account_id`)
    REFERENCES `mydb`.`Accounts` (`Id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `mydb`.`Branches_Office`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Branches_Office` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Branches_Office` (
  `Id` INT NULL AUTO_INCREMENT,
  `branch_name` VARCHAR(100) NULL DEFAULT NULL,
  `address` TEXT NULL DEFAULT NULL,
  `phone_number` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`));


-- -----------------------------------------------------
-- Table `mydb`.`Employees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Employees` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Employees` (
  `Id` INT NULL AUTO_INCREMENT,
  `branch_id` INT NULL DEFAULT NULL,
  `first_name` VARCHAR(50) NULL DEFAULT NULL,
  `last_name` VARCHAR(50) NULL DEFAULT NULL,
  `position` VARCHAR(50) NULL DEFAULT NULL,
  `hire_date` DATE NULL DEFAULT NULL,
  `salary` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `SECONDARY` (`branch_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`branch_id`)
    REFERENCES `mydb`.`Branches_Office` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `mydb`.`Loans`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Loans` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Loans` (
  `Id` INT NULL AUTO_INCREMENT,
  `customer_id` INT NULL DEFAULT NULL,
  `loan_type` VARCHAR(50) NULL DEFAULT NULL,
  `loan_amount` DECIMAL(15,2) NULL DEFAULT NULL,
  `interest_rate` DECIMAL(5,2) NULL DEFAULT NULL,
  `start_date` DATE NULL DEFAULT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  `status` ENUM('approved', 'pending', 'rejected') NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `SECONDARY` (`customer_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`Customers` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `mydb`.`Loan_Payments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Loan_Payments` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Loan_Payments` (
  `Id` INT NULL AUTO_INCREMENT,
  `loan_id` INT NULL DEFAULT NULL,
  `payment_date` DATE NULL DEFAULT NULL,
  `payment_amount` DECIMAL(15,2) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `SECONDARY` (`loan_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`loan_id`)
    REFERENCES `mydb`.`Loans` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `mydb`.`Credit_Cards`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Credit_Cards` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Credit_Cards` (
  `card_number` CHAR(16) NULL DEFAULT NULL,
  `customer_id` INT NULL DEFAULT NULL,
  `card_type` ENUM('Debit Visa', 'Debit MasterCard', 'Credit Visa', 'Credit MasterCard') NULL DEFAULT NULL,
  `credit_limit` DECIMAL(15,2) NULL DEFAULT NULL,
  `current_balance` DECIMAL(15,2) NULL DEFAULT NULL,
  `payment_due_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`card_number`),
  INDEX `SECONDARY` (`customer_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`Customers` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `mydb`.`Card_Transactions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Card_Transactions` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Card_Transactions` (
  `Id` INT NULL AUTO_INCREMENT,
  `card_number` CHAR(16) NULL DEFAULT NULL,
  `transaction_date` DATETIME NULL DEFAULT NULL,
  `merchant_name` VARCHAR(100) NULL DEFAULT NULL,
  `transaction_amount` DECIMAL(15,2) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `SECONDARY` (`card_number` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`card_number`)
    REFERENCES `mydb`.`Credit_Cards` (`card_number`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `mydb`.`ATMs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ATMs` ;

CREATE TABLE IF NOT EXISTS `mydb`.`ATMs` (
  `Id` INT NULL AUTO_INCREMENT,
  `branch_id` INT NULL DEFAULT NULL,
  `location` VARCHAR(255) NULL DEFAULT NULL,
  `availability_hours` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `SECONDARY` (`branch_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`branch_id`)
    REFERENCES `mydb`.`Branches_Office` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `mydb`.`Account_Holders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Account_Holders` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Account_Holders` (
  `Id` INT NULL AUTO_INCREMENT,
  `account_id` INT NULL DEFAULT NULL,
  `customer_id` INT NULL DEFAULT NULL,
  `relationship_type` ENUM('primary', 'joint') NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `SECONDARY` (`account_id` ASC) VISIBLE,
  INDEX `SECONDARY` (`customer_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`account_id`)
    REFERENCES `mydb`.`Accounts` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT ``
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`Customers` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `mydb`.`Transfers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Transfers` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Transfers` (
  `Id` INT NULL AUTO_INCREMENT,
  `from_account_id` INT NULL DEFAULT NULL,
  `to_account_id` INT NULL DEFAULT NULL,
  `transfer_date` DATETIME NULL DEFAULT NULL,
  `transfer_amount` DECIMAL(15,2) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `SECONDARY` (`from_account_id` ASC) INVISIBLE,
  INDEX `SECONDARY` (`to_account_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`from_account_id`)
    REFERENCES `mydb`.`Accounts` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT ``
    FOREIGN KEY (`to_account_id`)
    REFERENCES `mydb`.`Accounts` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;