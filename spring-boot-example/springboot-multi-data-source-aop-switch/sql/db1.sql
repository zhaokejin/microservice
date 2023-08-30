/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : db1

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 27/03/2019 08:43:47
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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'db1', '2019-01-06 21:03:24');
INSERT INTO `user` VALUES (2, 'db1', '2019-01-06 21:05:06');
INSERT INTO `user` VALUES (3, 'db1', '2019-01-06 21:05:06');
INSERT INTO `user` VALUES (4, 'db1', '2019-01-06 21:05:06');
INSERT INTO `user` VALUES (5, 'db1', '2019-01-06 21:05:07');
INSERT INTO `user` VALUES (6, 'db1', '2019-01-06 21:05:07');
INSERT INTO `user` VALUES (7, 'db1', '2019-01-06 21:05:07');
INSERT INTO `user` VALUES (8, 'db1', '2019-01-06 21:05:07');
INSERT INTO `user` VALUES (9, 'db1', '2019-01-06 21:05:07');
INSERT INTO `user` VALUES (10, 'db1', '2019-01-06 21:05:07');
INSERT INTO `user` VALUES (11, 'db1', '2019-01-06 21:05:07');
INSERT INTO `user` VALUES (12, 'db1', '2019-01-06 21:05:08');
INSERT INTO `user` VALUES (13, 'db1', '2019-01-06 21:05:08');
INSERT INTO `user` VALUES (14, 'db1', '2019-01-06 21:05:08');

SET FOREIGN_KEY_CHECKS = 1;
