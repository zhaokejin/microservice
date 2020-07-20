/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.15 : Database - demo_all_ds_1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`demo_all_ds_1` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `demo_all_ds_1`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

LOCK TABLES `user` WRITE;

insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (1,'baidu','李四1','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (3,'baidu','李四3','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (5,'baidu','李四5','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (7,'baidu','李四7','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (9,'baidu','李四9','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (11,'baidu','李四11','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (13,'baidu','李四13','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (15,'baidu','李四15','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (17,'baidu','李四17','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (19,'baidu','李四19','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (21,'baidu','李四21','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (23,'baidu','李四23','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (25,'baidu','李四25','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (27,'baidu','李四27','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (29,'baidu','李四29','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (31,'baidu','李四31','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (33,'baidu','李四33','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (35,'baidu','李四35','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (37,'baidu','李四37','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (39,'baidu','李四39','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (41,'baidu','李四41','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (43,'baidu','李四43','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (45,'baidu','李四45','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (47,'baidu','李四47','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (49,'baidu','李四49','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (51,'baidu','李四51','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (53,'baidu','李四53','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (55,'baidu','李四55','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (57,'baidu','李四57','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (59,'baidu','李四59','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (61,'baidu','李四61','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (63,'baidu','李四63','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (65,'baidu','李四65','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (67,'baidu','李四67','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (69,'baidu','李四69','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (71,'baidu','李四71','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (73,'baidu','李四73','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (75,'baidu','李四75','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (77,'baidu','李四77','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (79,'baidu','李四79','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (81,'baidu','李四81','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (83,'baidu','李四83','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (85,'baidu','李四85','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (87,'baidu','李四87','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (89,'baidu','李四89','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (91,'baidu','李四91','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (93,'baidu','李四93','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (95,'baidu','李四95','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (97,'baidu','李四97','2019-06-13 17:01:42','2019-06-13 17:01:42');
insert  into `user`(`id`,`company_id`,`name`,`create_time`,`update_time`) values (99,'baidu','李四99','2019-06-13 17:01:42','2019-06-13 17:01:42');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
