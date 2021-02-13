/*
 Navicat MySQL Data Transfer

 Source Server         : 1
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : sign_in_system

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 13/02/2021 11:04:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for check1
-- ----------------------------
DROP TABLE IF EXISTS `check1`;
CREATE TABLE `check1` (
  `check_id` int(11) NOT NULL AUTO_INCREMENT,
  `check_grade_id` int(11) NOT NULL,
  `check_start_time` datetime DEFAULT NULL,
  `check_create_id` int(11) NOT NULL,
  `check_time` int(11) NOT NULL,
  `check_pwd` varchar(10) DEFAULT NULL,
  `check_status` int(11) DEFAULT NULL COMMENT '0可签到 1已过期',
  `check_end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`check_id`),
  KEY `fk_check_grade` (`check_grade_id`),
  CONSTRAINT `fk_check_grade` FOREIGN KEY (`check_grade_id`) REFERENCES `user` (`grade_id`),
  CONSTRAINT `fk_check_user` FOREIGN KEY (`check_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for check_item
-- ----------------------------
DROP TABLE IF EXISTS `check_item`;
CREATE TABLE `check_item` (
  `check_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `check_id` int(11) NOT NULL,
  `check_solve_id` int(11) NOT NULL,
  `check_end_time` datetime DEFAULT NULL,
  `check_item_status` int(11) DEFAULT NULL COMMENT '0 未签到 1 已签到',
  PRIMARY KEY (`check_item_id`),
  KEY `fk_item_check` (`check_id`),
  KEY `fk_item_user` (`check_solve_id`),
  CONSTRAINT `fk_item_check` FOREIGN KEY (`check_id`) REFERENCES `check1` (`check_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_item_user` FOREIGN KEY (`check_solve_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(20) NOT NULL,
  `grade_desc` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for things
-- ----------------------------
DROP TABLE IF EXISTS `things`;
CREATE TABLE `things` (
  `things_id` int(11) NOT NULL AUTO_INCREMENT,
  `things_name` varchar(20) DEFAULT NULL,
  `things_desc` varchar(50) DEFAULT NULL,
  `things_create_id` int(11) NOT NULL,
  `things_solve_id` int(11) DEFAULT NULL,
  `things_grade_id` int(11) DEFAULT NULL,
  `things_status` int(11) DEFAULT NULL COMMENT '0待处理 1已处理',
  PRIMARY KEY (`things_id`),
  KEY `fk_things_user` (`things_create_id`),
  KEY `fk_things_user_solve` (`things_solve_id`),
  KEY `fk_things_grade` (`things_grade_id`),
  CONSTRAINT `fk_things_grade` FOREIGN KEY (`things_grade_id`) REFERENCES `user` (`grade_id`),
  CONSTRAINT `fk_things_user` FOREIGN KEY (`things_create_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `fk_things_user_solve` FOREIGN KEY (`things_solve_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `user_desc` varchar(50) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `gender` int(11) DEFAULT '0' COMMENT '0:男 1:女',
  `identity` int(11) DEFAULT '0' COMMENT '身份 0:学生 1:老师',
  `grade_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_user_grade` (`grade_id`),
  CONSTRAINT `fk_user_grade` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
