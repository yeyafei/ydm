/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50537
Source Host           : 127.0.0.1:3306
Source Database       : mybaits_crud

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-04-02 15:21:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(100) NOT NULL,
  `code` varchar(100) DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `active` varchar(10) DEFAULT NULL,
  `parentid` varchar(100) DEFAULT NULL,
  `level` varchar(5) DEFAULT NULL,
  `icon` varchar(10) DEFAULT NULL,
  `create_oper` varchar(100) DEFAULT NULL,
  `create_date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2376 DEFAULT CHARSET=latin1;
