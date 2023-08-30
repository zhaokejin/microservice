/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 21/10/2018 05:52:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', 'aaa', '2018-10-21 05:50:03', '2018-10-21 05:50:11');
INSERT INTO `user` VALUES (2, 'lisi', 'bbb', '2018-10-21 05:50:14', '2018-10-21 05:50:48');
INSERT INTO `user` VALUES (3, 'wangwu', 'ccc', '2018-10-21 05:50:15', '2018-10-21 05:50:50');
INSERT INTO `user` VALUES (4, 'zhaoliu', 'ddd', '2018-10-21 05:50:17', '2018-10-21 05:50:54');
INSERT INTO `user` VALUES (5, 'tianqi', 'eee', '2018-10-21 05:50:18', '2018-10-21 05:50:56');
INSERT INTO `user` VALUES (6, 'xiaoming', 'fff', '2018-10-21 05:50:19', '2018-10-21 05:51:07');
INSERT INTO `user` VALUES (7, 'erming', 'ggg', '2018-10-21 05:50:21', '2018-10-21 05:51:14');
INSERT INTO `user` VALUES (8, 'xiaogang', 'hhh', '2018-10-21 05:50:25', '2018-10-21 05:51:18');
INSERT INTO `user` VALUES (9, 'lili', 'kkk', '2018-10-21 05:50:28', '2018-10-21 05:51:23');
INSERT INTO `user` VALUES (10, 'nalan', 'lll', '2018-10-21 05:50:37', '2018-10-21 05:51:29');
INSERT INTO `user` VALUES (11, 'xiaoya', 'mmm', '2018-10-21 05:50:39', '2018-10-21 05:51:47');
INSERT INTO `user` VALUES (12, 'yaer', 'nnn', '2018-10-21 05:50:41', '2018-10-21 05:51:50');

SET FOREIGN_KEY_CHECKS = 1;
