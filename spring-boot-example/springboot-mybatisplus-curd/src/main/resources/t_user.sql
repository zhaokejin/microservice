/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3307
 Source Schema         : mytest

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 06/02/2023 15:21:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `userName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                           `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                           `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                           `createTime` datetime NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1028 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'zhangsan', '123456', '13688882222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (2, 'lisi', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1001, 'wangwu', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1002, 'zhaoliu', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1003, 'tianqi', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1004, 'xiaoming', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1005, 'erming', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1006, 'xiaogang', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1007, 'lili', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1008, 'nalan', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1009, 'xiaoya', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1010, 'yaer', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1011, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1012, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1013, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1014, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1015, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1016, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1017, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1018, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1019, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1020, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1021, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1022, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1023, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1024, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1025, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1026, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');
INSERT INTO `t_user` VALUES (1027, '王六2', '123456', '13599992222', '2023-02-06 15:20:06');

SET FOREIGN_KEY_CHECKS = 1;
