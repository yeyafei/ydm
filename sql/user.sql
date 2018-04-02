/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50537
Source Host           : 127.0.0.1:3306
Source Database       : mybaits_crud

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-04-02 15:21:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `code` varchar(10) NOT NULL,
  `pass_word` varchar(20) DEFAULT NULL,
  `level` varchar(10) DEFAULT NULL,
  `active` varchar(10) DEFAULT '0',
  `creat_date` varchar(20) DEFAULT NULL,
  `cerat_oper` varchar(20) DEFAULT NULL,
  `user_role` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
