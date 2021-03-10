/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.15 : Database - demo_ds
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`demo_ds` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `demo_ds`;

/*Table structure for table `user_0` */

DROP TABLE IF EXISTS `user_0`;

CREATE TABLE `user_0` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_0` */

LOCK TABLES `user_0` WRITE;

UNLOCK TABLES;

/*Table structure for table `user_1` */

DROP TABLE IF EXISTS `user_1`;

CREATE TABLE `user_1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_1` */

LOCK TABLES `user_1` WRITE;

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
