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

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

CREATE TABLE SYS_USER(
user_id INT PRIMARY KEY,
login_name VARCHAR(64),-- 用户名
`password` VARCHAR(64),-- 密码
register_mode VARCHAR(2), -- 注册类型  1邮箱创建  2手机号创建  3QQ  4微信
register_time TIMESTAMP , -- 注册时间
update_time TIMESTAMP, -- 修改时间
dis VARCHAR(2), -- 是否禁用
dis_type VARCHAR(2), -- 禁用类型  1：冻结 2注销
register_ip VARCHAR(32) -- 注册ip
);

CREATE TABLE SYS_USER_BASE(
user_base_id INT PRIMARY KEY,
user_id INT,
sex INT(1), -- 性别 1男  0女
age INT(3), -- 年龄
remark VARCHAR(512), -- 备注
weixin VARCHAR(64), -- 微信
qq VARCHAR(64), -- qq
modbile_phone VARCHAR(32), -- 手机号
real_name VARCHAR(32), -- 昵称
create_time TIMESTAMP, -- 创建时间
update_time TIMESTAMP, -- 修改时间
is_del INT(1), -- 是否删除 0否 1是
address VARCHAR(64), -- 住址
company VARCHAR(128), -- 公司名
area_code VARCHAR(16), -- 住址县级编码
city_code VARCHAR(16), -- 住址市级编码
prov_code VARCHAR(16), -- 住址省级编码
id_card   VARCHAR(32) -- 身份证号（考虑实名认证）
);

DROP TABLE IF EXISTS `c_user`;
CREATE TABLE `c_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `add_date` datetime(3) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_username`(`username`) USING BTREE,
  UNIQUE INDEX `unique_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_user
-- ----------------------------
INSERT INTO `c_user` VALUES (1, 'admin', '$2a$10$f/kADD4iB7fotPTNcSzQPebfpgckxgCJaYeHMcF4a0aFavaBPdo82', '000000000@qq.com', '2019-01-22 18:59:18.162');
INSERT INTO `c_user` VALUES (2, 'system', '$2a$10$azEkFMR8gZ1hT5O4mGaJTOsWMU2Zgkt1AkxPauFr4qM9d3rulZb56', '000000000@qq.com', '2019-01-22 19:04:57.471');
INSERT INTO `c_user` VALUES (3, 'admin1', '$2a$10$M5v4RW15rj9m5oLkFhJCKuG1MJp6Ip7tIHi5sMiRGr3.l1qdOBzxm', '13800000000', '2019-01-30 13:17:04.000');


