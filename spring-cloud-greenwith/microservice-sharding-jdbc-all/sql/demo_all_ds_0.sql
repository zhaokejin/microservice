/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.15 : Database - demo_all_ds_0
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`demo_all_ds_0` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `demo_all_ds_0`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

LOCK TABLES `user` WRITE;

insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (2,'alibaba','李四2','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (4,'alibaba','李四4','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (6,'alibaba','李四6','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (8,'alibaba','李四8','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (10,'alibaba','李四10','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (12,'alibaba','李四12','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (14,'alibaba','李四14','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (16,'alibaba','李四16','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (18,'alibaba','李四18','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (20,'alibaba','李四20','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (22,'alibaba','李四22','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (24,'alibaba','李四24','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (26,'alibaba','李四26','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (28,'alibaba','李四28','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (30,'alibaba','李四30','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (32,'alibaba','李四32','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (34,'alibaba','李四34','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (36,'alibaba','李四36','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (38,'alibaba','李四38','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (40,'alibaba','李四40','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (42,'alibaba','李四42','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (44,'alibaba','李四44','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (46,'alibaba','李四46','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (48,'alibaba','李四48','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (50,'alibaba','李四50','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (52,'alibaba','李四52','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (54,'alibaba','李四54','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (56,'alibaba','李四56','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (58,'alibaba','李四58','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (60,'alibaba','李四60','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (62,'alibaba','李四62','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (64,'alibaba','李四64','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (66,'alibaba','李四66','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (68,'alibaba','李四68','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (70,'alibaba','李四70','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (72,'alibaba','李四72','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (74,'alibaba','李四74','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (76,'alibaba','李四76','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (78,'alibaba','李四78','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (80,'alibaba','李四80','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (82,'alibaba','李四82','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (84,'alibaba','李四84','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (86,'alibaba','李四86','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (88,'alibaba','李四88','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (90,'alibaba','李四90','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (92,'alibaba','李四92','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (94,'alibaba','李四94','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (96,'alibaba','李四96','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (98,'alibaba','李四98','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (99,'alibaba','李四0','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (100,'alibaba','李四0','2019-06-13 18:56:01','2019-06-13 18:56:01');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
