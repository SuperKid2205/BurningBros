CREATE DATABASE  IF NOT EXISTS `BURNING_BROS_SCHEMA`;
USE `BURNING_BROS_SCHEMA`;

--
-- Create table
--

DROP TABLE IF EXISTS `MST_VEHICLE`;

CREATE TABLE `MST_VEHICLE` (
  `VEHICLE_ID` int NOT NULL AUTO_INCREMENT,
  `VEHICLE_NAME` varchar(50) NOT NULL,
  `USER_ID` int NOT NULL,
  `MODEL` varchar(10) DEFAULT NULL,
  `YEAR` varchar(4) DEFAULT NULL,
  `TYPE` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`VEHICLE_ID`),
  KEY `FK_VEHICLE_idx` (`USER_ID`),
  CONSTRAINT `FK_VEHICLE` FOREIGN KEY (`USER_ID`) 
  REFERENCES `MST_USER` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `BURNING_BROS_SCHEMA`.`MST_VEHICLE`
(
`VEHICLE_NAME`,
`USER_ID`,
`MODEL`,
`YEAR`,
`TYPE`)
VALUES
(
'car-01', 
'1', 
'01',
'2015',
'car' );

INSERT INTO `BURNING_BROS_SCHEMA`.`MST_VEHICLE`
(
`VEHICLE_NAME`,
`USER_ID`,
`MODEL`,
`YEAR`,
`TYPE`)
VALUES
(
'car-02', 
'2', 
'01',
'2015',
'car' );

INSERT INTO `BURNING_BROS_SCHEMA`.`MST_VEHICLE`
(
`VEHICLE_NAME`,
`USER_ID`,
`MODEL`,
`YEAR`,
`TYPE`)
VALUES
(
'car-03', 
'3', 
'01',
'2015',
'car' );