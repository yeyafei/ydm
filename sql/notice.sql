/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50537
Source Host           : 127.0.0.1:3306
Source Database       : mybaits_crud

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-04-02 15:21:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` text,
  `content` text,
  `create_oper` varchar(20) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
