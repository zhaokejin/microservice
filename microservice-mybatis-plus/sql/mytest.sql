/*
 Navicat Premium Data Transfer

 Source Server         : 10.254.193.155
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 10.254.193.155:3306
 Source Schema         : pay_platform_account_system

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 29/10/2019 09:41:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1008 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '张三1', '123456', '13688882222');
INSERT INTO `t_user` VALUES (2, '王六', '123456', '13599992222');
INSERT INTO `t_user` VALUES (1001, '王五', '123456', '13988855552');
INSERT INTO `t_user` VALUES (1002, '王五222', '1234561', '132545564545');
INSERT INTO `t_user` VALUES (1003, '王五222', '1234561', '132545564545');
INSERT INTO `t_user` VALUES (1004, '王五222', '1234561', '132545564545');
INSERT INTO `t_user` VALUES (1005, '王五222', '1234561', '132545564545');
INSERT INTO `t_user` VALUES (1006, '王五222', '1234561', '132545564545');
INSERT INTO `t_user` VALUES (1007, '王五222', '1234561', '132545564545');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1007 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, '王六', '123456', '13599992222');
INSERT INTO `user` VALUES (3, '王五', '123456', '13988855552');
INSERT INTO `user` VALUES (4, '王五222', '1234561', '132545564545');
INSERT INTO `user` VALUES (1003, '王五222', '1234561', '132545564545');
INSERT INTO `user` VALUES (1004, '王五222', '1234561', '132545564545');
INSERT INTO `user` VALUES (1005, '王五222', '1234561', '132545564545');
INSERT INTO `user` VALUES (1006, '王五222', '1234561', '132545564545');

SET FOREIGN_KEY_CHECKS = 1;
