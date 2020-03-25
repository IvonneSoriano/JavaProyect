/**
 * Author:  Rick
 * Created: Mar 10, 2020
 */

-- MySQL Script generated by MySQL Workbench
-- Tue Mar 10 02:29:28 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema gestion_tickets
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `gestion_tickets` ;
-- -----------------------------------------------------
-- Schema gestion_tickets
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestion_tickets`;
USE `gestion_tickets` ;
-- -----------------------------------------------------
-- Table `gestion_tickets`.`requesttypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestion_tickets`.`requesttypes` ;

CREATE TABLE IF NOT EXISTS `gestion_tickets`.`requesttypes` (
  `REQUESTTYPEID` SMALLINT NOT NULL AUTO_INCREMENT,
  `REQUESTTYPENAME` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`REQUESTTYPEID`))
ENGINE = InnoDB
AUTO_INCREMENT = 0;
-- -----------------------------------------------------
-- Table `gestion_tickets`.`requests`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestion_tickets`.`requests` ;

CREATE TABLE IF NOT EXISTS `gestion_tickets`.`requests` (
  `REQUESTID` SMALLINT NOT NULL AUTO_INCREMENT,
  `REQUESTTYPEID` SMALLINT NOT NULL,
  `REQUESTDATE` TIMESTAMP NOT NULL,
  `REQUESTDESCRIPTION` VARCHAR(2048) NOT NULL,
  `REQUESTSTATUS` VARCHAR(16) NOT NULL,
  `PROJECTID` smallint DEFAULT NULL,
  PRIMARY KEY (`REQUESTID`),
  CONSTRAINT `FK_REL_TYPE_REQUEST`
        FOREIGN KEY (`REQUESTTYPEID`)
        REFERENCES `gestion_tickets`.`requesttypes` (`REQUESTTYPEID`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 0;
-- -----------------------------------------------------
-- Table `gestion_tickets`.`departments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestion_tickets`.`departments` ;

CREATE TABLE IF NOT EXISTS `gestion_tickets`.`departments` (
  `DEPARMENTID` SMALLINT NOT NULL AUTO_INCREMENT,
  `DEPARMENTNAME` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`DEPARMENTID`))
ENGINE = InnoDB
AUTO_INCREMENT = 0;
-- -----------------------------------------------------
-- Table `gestion_tickets`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestion_tickets`.`roles` ;

CREATE TABLE IF NOT EXISTS `gestion_tickets`.`roles` (
  `ROLID` SMALLINT NOT NULL AUTO_INCREMENT,
  `ROLNAME` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`ROLID`))
ENGINE = InnoDB
AUTO_INCREMENT = 0;
-- -----------------------------------------------------
-- Table `gestion_tickets`.`employees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestion_tickets`.`employees` ;

CREATE TABLE IF NOT EXISTS `gestion_tickets`.`employees` (
  `EmployeeID` SMALLINT NOT NULL AUTO_INCREMENT,
  `ROLID` SMALLINT NOT NULL,
  `DEPARMENTID` SMALLINT NOT NULL,
  `EMPLOYEENAME` VARCHAR(64) NOT NULL,
  `EMPLOYEELASTNAME` VARCHAR(64) NOT NULL,
  `USERNAME` VARCHAR(16) NOT NULL,
  `PASSWORD` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`, `DEPARMENTID`),
  CONSTRAINT `FK_REL_DEPARMENT_EMPLOYEE`
        FOREIGN KEY (`DEPARMENTID`)
        REFERENCES `gestion_tickets`.`departments` (`DEPARMENTID`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
  CONSTRAINT `FK_REL_ROL_EMPLOYEE`
        FOREIGN KEY (`ROLID`)
        REFERENCES `gestion_tickets`.`roles` (`ROLID`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 0;
-- -----------------------------------------------------
-- Table `gestion_tickets`.`comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestion_tickets`.`comments` ;

CREATE TABLE IF NOT EXISTS `gestion_tickets`.`comments` (
  `COMMENTID` SMALLINT NOT NULL AUTO_INCREMENT,
  `EMPLOYEEID` SMALLINT NOT NULL,
  `DEPARMENTID` SMALLINT NOT NULL,
  `REQUESTID` SMALLINT NOT NULL,
  `COMMENTTEXT` VARCHAR(2048) NOT NULL,
  `COMMENTDATE` TIMESTAMP NOT NULL,
  PRIMARY KEY (`COMMENTID`, `DEPARMENTID`, `EMPLOYEEID`, `REQUESTID`),
  CONSTRAINT `FK_REL_EMPLOYEE_REQUEST`
        FOREIGN KEY (`REQUESTID`)
        REFERENCES `gestion_tickets`.`requests` (`REQUESTID`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT,
  CONSTRAINT `fk_comments_employees1`
        FOREIGN KEY (`EMPLOYEEID` , `DEPARMENTID`)
        REFERENCES `gestion_tickets`.`employees` (`EmployeeID` , `DEPARMENTID`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 0;
-- -----------------------------------------------------
-- Table `gestion_tickets`.`projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestion_tickets`.`projects` ;

CREATE TABLE IF NOT EXISTS `gestion_tickets`.`projects` (
  `PROJECTID` SMALLINT NOT NULL AUTO_INCREMENT,
  `DEPARMENTID` SMALLINT NOT NULL,
  `PROJECTNAME` VARCHAR(256) NOT NULL,
  `PROJECTDESCRIPTION` VARCHAR(1024) NOT NULL,
  `CREATIONDATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PROJECTID`),
  CONSTRAINT `fk_projects_departments1`
        FOREIGN KEY (`DEPARMENTID`)
        REFERENCES `gestion_tickets`.`departments` (`DEPARMENTID`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 0;
-- -----------------------------------------------------
-- Table `gestion_tickets`.`tickets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestion_tickets`.`tickets` ;

CREATE TABLE IF NOT EXISTS `gestion_tickets`.`tickets` (
  `TICKETID` SMALLINT NOT NULL AUTO_INCREMENT,
  `REQUESTID` SMALLINT NOT NULL,
  `PROJECTID` SMALLINT NOT NULL,
  `ID_PROGRAMADOR` SMALLINT NOT NULL,
  `ID_TESTER` SMALLINT NOT NULL,
  `TICKET_STATUS` VARCHAR(16) NOT NULL,
  `INTERNALCODE` VARCHAR(24) NOT NULL,
  `STARTDATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ENDDATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TICKETID`, `REQUESTID`),
  CONSTRAINT `fk_tickets_requests1`
        FOREIGN KEY (`REQUESTID`)
        REFERENCES `gestion_tickets`.`requests` (`REQUESTID`)
        ON DELETE CASCADE
        ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 0;
