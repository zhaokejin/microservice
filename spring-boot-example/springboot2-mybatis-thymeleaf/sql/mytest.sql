/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.17-log : Database - mytest
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mytest` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mytest`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `address` */

insert  into `address`(`id`,`province`,`city`) values (1,'北京','北京');
insert  into `address`(`id`,`province`,`city`) values (2,'天津','天津');
insert  into `address`(`id`,`province`,`city`) values (3,'安徽','宿州');
insert  into `address`(`id`,`province`,`city`) values (4,'广东','广州');

/*Table structure for table `c_user` */

DROP TABLE IF EXISTS `c_user`;

CREATE TABLE `c_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL,
  `add_date` datetime(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`username`) USING BTREE,
  UNIQUE KEY `unique_email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `c_user` */

insert  into `c_user`(`id`,`username`,`password`,`email`,`add_date`) values (1,'admin','$2a$10$f/kADD4iB7fotPTNcSzQPebfpgckxgCJaYeHMcF4a0aFavaBPdo82','210006540@qq.com','2019-01-22 18:59:18.162');
insert  into `c_user`(`id`,`username`,`password`,`email`,`add_date`) values (2,'system','$2a$10$azEkFMR8gZ1hT5O4mGaJTOsWMU2Zgkt1AkxPauFr4qM9d3rulZb56','906163423@qq.com','2019-01-22 19:04:57.471');
insert  into `c_user`(`id`,`username`,`password`,`email`,`add_date`) values (3,'admin1','$2a$10$M5v4RW15rj9m5oLkFhJCKuG1MJp6Ip7tIHi5sMiRGr3.l1qdOBzxm','13906414017','2019-01-30 13:17:04.000');
insert  into `c_user`(`id`,`username`,`password`,`email`,`add_date`) values (4,'','$2a$10$eOl2LvPtKG8RzrHADFlb6O4Irv9x72kk3qdH6t5Fa4YtngdrN.f4.','','2019-02-01 15:54:39.000');

/*Table structure for table `car` */

DROP TABLE IF EXISTS `car`;

CREATE TABLE `car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `color` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `car` */

insert  into `car`(`id`,`color`,`name`,`user_id`) values (1,'green','路虎',1);
insert  into `car`(`id`,`color`,`name`,`user_id`) values (2,'white','奔驰',2);
insert  into `car`(`id`,`color`,`name`,`user_id`) values (3,'blue','玛莎拉蒂',4);
insert  into `car`(`id`,`color`,`name`,`user_id`) values (4,'yellow','兰博基尼',4);

/*Table structure for table `post` */

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `post_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(10) unsigned NOT NULL,
  `title` varchar(254) NOT NULL DEFAULT '',
  `content` text,
  `created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `post` */

insert  into `post`(`post_id`,`userid`,`title`,`content`,`created`) values (1,1,'MyBatis关联数据查询','在实际项目中，经常使用关联表的查询，比如：多对一，一对多等。这些查询是如何处理的呢，这一讲就讲这个问题。我们首先创建一个 post 表，并初始化数据.','2015-09-23 21:40:17');
insert  into `post`(`post_id`,`userid`,`title`,`content`,`created`) values (2,1,'MyBatis开发环境搭建','为了方便学习，这里直接建立java 工程，但一般都是开发 Web 项目。','2015-09-23 21:42:14');
insert  into `post`(`post_id`,`userid`,`title`,`content`,`created`) values (3,2,'这个是别人发的','content,内容...','0000-00-00 00:00:00');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`userId`,`userName`,`password`,`phone`) values (1,'张三','123456','13688882222');
insert  into `t_user`(`userId`,`userName`,`password`,`phone`) values (1000,'王六','123456','13599992222');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL DEFAULT '',
  `mobile` int(10) unsigned NOT NULL DEFAULT '0',
  `created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`mobile`,`created`) values (1,'yiibai',100,'2015-09-23 20:11:23');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
