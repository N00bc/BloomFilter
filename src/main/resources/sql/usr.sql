/*
 Navicat Premium Data Transfer

 Source Server         : myvm
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : myvm:3306
 Source Schema         : TestDB

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 03/11/2022 15:37:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for usr
-- ----------------------------
DROP TABLE IF EXISTS `usr`;
CREATE TABLE `usr`  (
  `id` int(11) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `money` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usr
-- ----------------------------
INSERT INTO `usr` VALUES (1, 'zhangsan', 88);
INSERT INTO `usr` VALUES (2, 'lisi', 2000);
INSERT INTO `usr` VALUES (3, 'wangwu', 3000);

SET FOREIGN_KEY_CHECKS = 1;
