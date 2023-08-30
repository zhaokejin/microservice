/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : db2

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 27/03/2019 08:43:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'db2', '2019-01-06 21:03:24');
INSERT INTO `user` VALUES (2, 'db2', '2019-01-06 21:04:49');
INSERT INTO `user` VALUES (3, 'db2', '2019-01-06 21:04:50');
INSERT INTO `user` VALUES (4, 'db2', '2019-01-06 21:04:50');
INSERT INTO `user` VALUES (5, 'db2', '2019-01-06 21:04:50');
INSERT INTO `user` VALUES (6, 'db2', '2019-01-06 21:04:50');
INSERT INTO `user` VALUES (7, 'db2', '2019-01-06 21:04:50');
INSERT INTO `user` VALUES (8, 'db2', '2019-01-06 21:04:51');
INSERT INTO `user` VALUES (9, 'db2', '2019-01-06 21:04:51');
INSERT INTO `user` VALUES (10, 'db2', '2019-01-06 21:04:51');
INSERT INTO `user` VALUES (11, 'db2', '2019-01-06 21:04:51');
INSERT INTO `user` VALUES (12, 'db2', '2019-01-06 21:04:51');
INSERT INTO `user` VALUES (13, 'db2', '2019-01-06 21:04:51');
INSERT INTO `user` VALUES (14, 'db2', '2019-01-06 21:04:52');
INSERT INTO `user` VALUES (15, 'db2', '2019-01-06 21:04:52');
INSERT INTO `user` VALUES (16, 'db2', '2019-01-06 21:04:52');
INSERT INTO `user` VALUES (17, 'db2', '2019-01-06 21:04:52');
INSERT INTO `user` VALUES (18, 'db2', '2019-01-06 21:04:52');

SET FOREIGN_KEY_CHECKS = 1;
